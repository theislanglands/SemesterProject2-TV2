package data;

import domain.Credit;
import domain.CreditName;
import domain.Production;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DataFacade implements DataLayerInterface {

    // singeltion instance
    private static DataFacade instance;

    // database connection setup
    private String url = "hattie.db.elephantsql.com";
    private int port = 5432;
    private String databaseName = "hdzfvhcu";
    private String username = "hdzfvhcu";
    private String password = "5pfvgV5fp9kT6J2Z5mJ92CnEuXnofxVd";
    private String stringType = "unspecified";  // If stringtype is set to varchar (the default),
    // such parameters will be sent to the server as
    // varchar parameters. If stringtype is set to unspecified,
    // parameters will be sent to the server as untyped values,
    // and the server will attempt to infer an appropriate type.

    private Connection connection = null;

    private DataFacade() {
        initializePostgresqlDatabase();
    }

    public static DataFacade getInstance() {
        if (instance == null) {
            instance = new DataFacade();
        }
        return instance;
    }

    private void initializePostgresqlDatabase() {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://" + url + ":" +
                            port + "/" +
                            databaseName + "?" +
                            "user=" + username +
                            "&password=" + password +
                            "&stringtype=" + stringType
            );

        } catch (SQLException | IllegalArgumentException ex) {
            ex.printStackTrace(System.err);
        } finally {
            // TODO: Aksel: Lukker hele programmet hvis connection = null?
            if (connection == null) System.exit(-1);
        }
    }

    @Override
    public int createProduction(Production prod) {

        // returns key if succesful, -1 if not
        int productionId = -1;
        try {
            //Begin statement - Transaction
            connection.setAutoCommit(false);

            PreparedStatement stmt1 = connection.prepareStatement(
                    "INSERT INTO production (" +
                            "season, " +                // 1
                            "episode, " +               // 2
                            "release_date, " +          // 3
                            "length, " +                // 4
                            "subtitle, " +              // 5
                            "sign_Language, " +         // 6
                            "active, " +                // 7
                            "validated, " +             // 8
                            "production_reference, " +  // 9
                            "production_bio, " +        // 10
                            "production_company_id, " + // 11
                            "production_type_id, " +    // 12
                            "language_id, " +           // 13
                            "production_name_id) " +    // 14
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            stmt1.setInt(1, prod.getSeason());
            stmt1.setInt(2, prod.getEpisode());
            stmt1.setString(3, dateFormatter(prod.getReleaseDate()));
            stmt1.setInt(4, prod.getLength());
            stmt1.setBoolean(5, prod.hasSubtitle());
            stmt1.setBoolean(6, prod.hasSignLanguage());
            stmt1.setBoolean(7, prod.isActive());
            stmt1.setBoolean(8, prod.isValidated());
            stmt1.setString(9, prod.getProductionReference());
            stmt1.setString(10, prod.getProductionBio());

            // inserting foreign keys
            stmt1.setInt(11, getProdCompanyId(prod.getProductionCompanyName()));
            stmt1.setInt(12, getProdTypeId(prod.getProductionType()));
            stmt1.setInt(13, getLanguageId(prod.getLanguage()));

            int productionNameId = getProductionNameId(prod.getName()); // if production name exist retrieve ID!

            if (productionNameId == -1) { // if production name doesn't exist - create a new one, return id.
                productionNameId = createProductionName(prod.getName());
            }

            stmt1.setInt(14, productionNameId);

            // execute SQL queries
            stmt1.executeUpdate();

            // gets the generated production id
            ResultSet resultSet = stmt1.getGeneratedKeys();
            resultSet.next();
            productionId = resultSet.getInt(1);
            stmt1.close();


            // associate production with genres
            for (String genre : prod.getGenres()) {

                PreparedStatement stmt2 = connection.prepareStatement(
                        "INSERT INTO genres_production_association (" +
                                "production_id, " +     //1
                                "genre_id) " +          //2
                                "VALUES (?, ?);");

                stmt2.setInt(1, productionId);
                stmt2.setInt(2, getGenreId(genre));
                stmt2.execute();
                stmt2.close();
            }

            // commit changes to db.
            connection.commit();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Error, could not create production.");

            // TODO: Er det den rigtig måde at Rollback på i JAVA?
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Transaction is being rolled back");
                }
            }
        }

        return productionId;
    }

    @Override
    public List<Production> getProductions() {

        List<Production> productions = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT id FROM production");
            ResultSet sqlReturnValues = stmt.executeQuery();

            // Query Id's of all productions and add production to list.
            while (sqlReturnValues.next()) {
                productions.add(getProduction(sqlReturnValues.getInt(1)));
            }
            stmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

        return productions;
    }

    @Override
    public Production getProduction(int productionId) {

        Production returnProduction = new Production();

        // query for all parameters production
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT " +
                            "production.season, " +                 // 1
                            "production.episode, " +                // 2
                            "production.release_date, " +           // 3
                            "production.length, " +                 // 4
                            "production.subtitle, " +               // 5
                            "production.sign_language, " +          // 6
                            "production.active, " +                 // 7
                            "production.validated, " +              // 8
                            "production.production_reference, " +   // 9
                            "production_company.name, " +           // 10
                            "production_type.type, " +              // 11
                            "language.language, " +                 // 12
                            "production_name.name, " +              // 13
                            "production.id, " +                     // 14
                            "production.production_bio " +         // 15
                            "FROM production " +
                            "JOIN production_company ON production_company.id = production.production_company_id " +
                            "JOIN production_type ON production_type.id = production.production_type_id " +
                            "JOIN language ON language.id = production.language_id " +
                            "JOIN production_name ON production_name.id = production.production_name_id " +
                            "WHERE production.id = ?");

            stmt.setInt(1, productionId);

            ResultSet sqlReturnValues = stmt.executeQuery();

            while (sqlReturnValues.next()) {
                returnProduction.setSeason(sqlReturnValues.getInt(1));
                returnProduction.setEpisode(sqlReturnValues.getInt(2));
                returnProduction.setReleaseDate(sqlReturnValues.getDate(3));
                returnProduction.setLength(sqlReturnValues.getInt(4));
                returnProduction.setSubtitle(sqlReturnValues.getBoolean(5));
                returnProduction.setSignLanguage(sqlReturnValues.getBoolean(6));
                returnProduction.setActive(sqlReturnValues.getBoolean(7));
                returnProduction.setValidated(sqlReturnValues.getBoolean(8));
                returnProduction.setProductionReference(sqlReturnValues.getString(9));
                returnProduction.setCompanyProductionName(sqlReturnValues.getString(10));
                returnProduction.setProductionType(sqlReturnValues.getString(11));
                returnProduction.setLanguage(sqlReturnValues.getString(12));
                returnProduction.setName(sqlReturnValues.getString(13));
                returnProduction.setId(sqlReturnValues.getInt(14));
                returnProduction.setProductionBio(sqlReturnValues.getString(15));
            }

            stmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

        // setting arrays: genres & credits by calling helper methods.
        returnProduction.setGenres((ArrayList<String>) getGenres(productionId));
        returnProduction.setCredits((ArrayList<Credit>) getCredits(productionId));

        return returnProduction;
    }

    @Override
    public void deleteProduction(int productionId) {

        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "DELETE FROM production WHERE id = ?"
            );

            stmt.setInt(1, productionId);
            stmt.execute();
            stmt.close();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public boolean updateProduction(int sourceProductionId, Production replaceProduction) {

        // returns true if succesful

        try {
            connection.setAutoCommit(false);
            PreparedStatement stmt = connection.prepareStatement(
                    "UPDATE production " +
                            "SET " +
                            "season = ?, " +                // 1
                            "episode = ?, " +               // 2
                            "release_date = ?, " +          // 3
                            "length = ?, " +                // 4
                            "subtitle = ?, " +              // 5
                            "sign_language = ?, " +         // 6
                            "active = ?, " +                // 7
                            "validated = ?, " +             // 8
                            "production_reference = ?, " +  // 9
                            "production_bio = ?, " +        // 10
                            "production_company_id = ?, " + // 11
                            "production_type_id = ?, " +    // 12
                            "language_id = ?, " +           // 13
                            "production_name_id = ? " +     // 14
                            "WHERE id = ?");                // 15

            stmt.setInt(1, replaceProduction.getSeason());
            stmt.setInt(2, replaceProduction.getEpisode());
            stmt.setString(3, dateFormatter(replaceProduction.getReleaseDate()));
            stmt.setInt(4, replaceProduction.getLength());
            stmt.setBoolean(5, replaceProduction.hasSubtitle());
            stmt.setBoolean(6, replaceProduction.hasSignLanguage());
            stmt.setBoolean(7, replaceProduction.isActive());
            stmt.setBoolean(8, replaceProduction.isValidated());
            stmt.setString(9, replaceProduction.getProductionReference());
            stmt.setString(10, replaceProduction.getProductionBio());

            // Finding correct foreign key in associated tabkes
            stmt.setInt(11, getProdCompanyId(replaceProduction.getProductionCompanyName()));
            stmt.setInt(12, getProdTypeId(replaceProduction.getProductionType()));
            stmt.setInt(13, getLanguageId(replaceProduction.getLanguage()));
            stmt.setInt(14, getProductionNameId(replaceProduction.getName()));

            // Source ID
            stmt.setInt(15, sourceProductionId);

            stmt.executeUpdate();
            stmt.close();

            // TODO: Burde kunne løses mere enkelt med et "DELETE ON UPDATE CASCADE"-agtig ting i SQL-scriptet

            // Deleting existing genre associations
            PreparedStatement stmt2 = connection.prepareStatement(
                    "DELETE FROM genres_production_association WHERE production_id = ?"
            );

            stmt2.setInt(1, sourceProductionId);
            stmt2.execute();

            // associate production with genres
            for (String genre : replaceProduction.getGenres()) {

                PreparedStatement stmt3 = connection.prepareStatement(
                        "INSERT INTO genres_production_association " +
                                "(genre_id, " +
                                "production_id) " +
                                "VALUES (?, ?)");

                stmt3.setInt(1, getGenreId(genre));
                stmt3.setInt(2, sourceProductionId);

                stmt3.execute();
                stmt3.close();
            }

            connection.commit();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void createCredits(Credit cred, int productionId) {
        try {
            connection.setAutoCommit(false);

            // inserting credit in table
            PreparedStatement stmt1 = connection.prepareStatement(
                    "INSERT INTO credit(" +
                            "role, " +              //1
                            "validated, " +         //2
                            "production_id) " +     //3
                            "VALUES (?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS  // returns the id of the inserted credit
            );
            stmt1.setString(1, cred.getRole());
            stmt1.setBoolean(2, cred.isValidated());
            stmt1.setInt(3, productionId);
            stmt1.execute();

            // retrieves primary key from the new insertion
            ResultSet resultSet = stmt1.getGeneratedKeys();
            resultSet.next();
            int lastInsertedCreditID = resultSet.getInt(1);
            stmt1.close();

            // Checks if name exist in table - and return id if exist, -1 if not!
            int creditNameId = getCreditNameId(cred.getFirstName(), cred.getLastName());

            if (creditNameId == -1) {
                creditNameId = createCreditName(cred.getCreditName());  // if creditName doesn't exist, create a new one and return it's ID
            }

            // Insert in table: credit_name_credit_type_association
            PreparedStatement stmt2 = connection.prepareStatement(
                    "INSERT INTO credit_name_credit_type_association(" +
                            "credit_name_id, " +    // 1
                            "credit_type_id, " +    // 2
                            "credit_id)" +          // 3
                            "VALUES (?,?,?)"
            );

            stmt2.setInt(1, creditNameId);
            stmt2.setInt(2, getCreditTypeId(cred.getCreditType()));
            stmt2.setInt(3, lastInsertedCreditID);
            stmt2.execute();
            stmt2.close();

            connection.commit();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public List<Credit> getCredits(int productionId) {
        // returns a list of credits associated with production

        List<Credit> returnCredits = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT id FROM credit WHERE production_id = ?;");

            stmt.setInt(1, productionId);

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                int readID = resultSet.getInt(1);
                returnCredits.add(getCredit(readID));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return returnCredits;
    }

    @Override
    public Credit getCredit(int creditID) {

        Credit returnCredit = new Credit();
        CreditName associatedName = new CreditName();

        // retrieves data from tables
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT\n" +
                            "    credit_name.first_name,\n" +   //1
                            "    credit_name.last_name,\n" +    //2
                            "    credit_name.address,\n" +      //3
                            "    credit_name.phone,\n" +        //4
                            "    credit_name.email,\n" +        //5
                            "    credit_name.date_of_birth,\n" +//6
                            "    credit_name.country, \n" +     //7
                            "    credit_name.bio, \n" +         //8
                            "    credit_type.type,\n" +         //9
                            "    credit.role,\n" +              //10
                            "    credit.validated\n" +          //11
                            "FROM credit_name_credit_type_association\n" +
                            "JOIN credit_type ON credit_name_credit_type_association.credit_type_id = credit_type.id\n" +
                            "JOIN credit_name ON credit_name_credit_type_association.credit_name_id = credit_name.id\n" +
                            "JOIN credit ON credit_name_credit_type_association.credit_id = credit.id\n" +
                            "WHERE credit.id = ?;");

            stmt.setInt(1, creditID);

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                // retrieves parameters for CreditName and adds CreditName object associatedName to credit
                associatedName.setFirstName(resultSet.getString(1));
                associatedName.setLastName(resultSet.getString(2));
                associatedName.setAddress(resultSet.getString(3));
                associatedName.setPhone(resultSet.getInt(4));
                associatedName.setEmail(resultSet.getString(5));
                associatedName.setDateOfBirth(resultSet.getDate(6));
                associatedName.setCountry(resultSet.getString(7));
                associatedName.setBio(resultSet.getString(8));
                returnCredit.setCreditName(associatedName);

                // adding remaining parameters to Credit
                returnCredit.setCreditType(resultSet.getString(9));
                returnCredit.setRole(resultSet.getString(10));
                returnCredit.setValidated(resultSet.getBoolean(11));
            }

            stmt.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return returnCredit;
    }

    @Override
    public void deleteCredit(Credit credit) {

        int productionId = credit.getProductionId();

        // finding id of credit
        int creditId = getCreditId(productionId, credit);

        // deleting credit
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "DELETE FROM credit WHERE id = ?;");
            stmt.setInt(1, creditId);
            stmt.execute();
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public boolean updateCredit(int creditID, Credit replaceCredit) {
        // returns true if succesfull!

        try {
            connection.setAutoCommit(false);

            PreparedStatement stmt1 = connection.prepareStatement(
                    "UPDATE credit " +
                            "SET " +
                            "role = ?, " +              // 1
                            "validated = ?, " +         // 2
                            "production_id = ? " +      // 3
                            "WHERE id = ?");            // 4

            stmt1.setString(1, replaceCredit.getRole());
            stmt1.setBoolean(2, replaceCredit.isValidated());
            stmt1.setInt(3, replaceCredit.getProductionId());
            stmt1.setInt(4, creditID);
            stmt1.execute();
            stmt1.close();

            // Checks if name exist in table - and return id if exist, -1 if not!
            int creditNameId = getCreditNameId(replaceCredit.getFirstName(), replaceCredit.getLastName());

            if (creditNameId == -1) {
                creditNameId = createCreditName(replaceCredit.getCreditName());  // if creditName doesn't exist, create a new one and return it's ID
            }

            PreparedStatement stmt2 = connection.prepareStatement(
                    "UPDATE credit_name_credit_type_association " +
                            "SET " +
                            "credit_name_id = ?, " +    // 1
                            "credit_type_id = ? " +     // 2
                            "WHERE credit_id = ?;");    // 3

            //stmt2.setInt(1, getCreditNameId(replaceCredit.getCreditName().getFirstName(), replaceCredit.getCreditName().getLastName()));
            stmt2.setInt(1, creditNameId);
            stmt2.setInt(2, getCreditTypeId(replaceCredit.getCreditType()));
            stmt2.setInt(3, creditID);
            stmt2.execute();
            stmt2.close();

            connection.commit();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public int createCreditName(CreditName creditName) {
        // returns - 1 if CreditName exist
        int creditNameId = -1;

        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO credit_name(" +
                            "first_name, " +        //1
                            "last_name, " +         //2
                            "address, " +           //3
                            "phone, " +             //4
                            "email, " +             //5
                            "date_of_birth, " +     //6
                            "country, " +           //7
                            "bio)" +                //8
                            "VALUES (?,?,?,?,?,?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS
            );


            stmt.setString(1, creditName.getFirstName());
            stmt.setString(2, creditName.getLastName());
            stmt.setString(3, creditName.getAddress());
            stmt.setInt(4, creditName.getPhone());
            stmt.setString(5, creditName.getEmail());
            stmt.setDate(6, (java.sql.Date) creditName.getDateOfBirth());
            stmt.setString(7, creditName.getCountry());
            stmt.setString(8, creditName.getBio());

            stmt.execute();

            // retrieves id of inserted creditName
            ResultSet resultSet = stmt.getGeneratedKeys();
            resultSet.next();
            creditNameId = resultSet.getInt(1);

            stmt.close();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return creditNameId;
    }

    @Override
    public List<CreditName> getCreditNames() {

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM credit_name");
            ResultSet sqlReturnValues = stmt.executeQuery();

            List<CreditName> returnValue = new ArrayList<>();
            while (sqlReturnValues.next()) {
                CreditName creditName = new CreditName();
                creditName.setId(sqlReturnValues.getInt(1));
                creditName.setFirstName(sqlReturnValues.getString(2));
                creditName.setLastName(sqlReturnValues.getString(3));
                creditName.setAddress(sqlReturnValues.getString(4));
                creditName.setPhone(sqlReturnValues.getInt(5));
                creditName.setEmail(sqlReturnValues.getString(6));
                creditName.setDateOfBirth(sqlReturnValues.getDate(7));
                creditName.setCountry(sqlReturnValues.getString(8));
                creditName.setBio(sqlReturnValues.getString(9));
                returnValue.add(creditName);
            }
            return returnValue;

        } catch (SQLException exception) {
            exception.printStackTrace();
            return null;
        }
    }


    @Override
    public CreditName getCreditName(int creditNameID) {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * from credit_name WHERE id = ?");
            stmt.setInt(1, creditNameID);

            ResultSet resultSet = stmt.executeQuery();
            CreditName creditName = new CreditName();

            while (resultSet.next()) {
                creditName.setId(resultSet.getInt(1));
                creditName.setFirstName(resultSet.getString(2));
                creditName.setLastName(resultSet.getString(3));
                creditName.setAddress(resultSet.getString(4));
                creditName.setPhone(resultSet.getInt(5));
                creditName.setEmail(resultSet.getString(6));
                creditName.setDateOfBirth(resultSet.getDate(7));
                creditName.setCountry(resultSet.getString(8));
                creditName.setBio(resultSet.getString(9));
            }

            return creditName;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteCreditName(int creditNameID) {
        try {

            PreparedStatement stmt = connection.prepareStatement(
                    "DELETE FROM credit_name WHERE id = ?");
            stmt.setInt(1, creditNameID);
            stmt.execute();

            stmt.close();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public boolean updateCreditName(int creditNameId, CreditName replaceCreditName) {
        // returns true if successful!
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "UPDATE credit_name SET " +
                            "first_name = ?, " +        // 1
                            "last_name = ?, " +         // 2
                            "address = ?, " +           // 3
                            "phone = ?, " +             // 4
                            "email = ?, " +             // 5
                            "date_of_birth = ?," +      // 6
                            "country = ?," +            // 7
                            "bio = ?" +                 // 8
                            "WHERE id = ?");            // 9
            stmt.setString(1, replaceCreditName.getFirstName());
            stmt.setString(2, replaceCreditName.getLastName());
            stmt.setString(3, replaceCreditName.getAddress());
            stmt.setInt(4, replaceCreditName.getPhone());
            stmt.setString(5, replaceCreditName.getEmail());
            stmt.setDate(6, (java.sql.Date) replaceCreditName.getDateOfBirth());
            stmt.setString(7, replaceCreditName.getCountry());
            stmt.setString(8, replaceCreditName.getBio());
            stmt.setInt(9, creditNameId);
            stmt.execute();
            stmt.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    public int createProductionName(String productionName) {
        // returns - 1 if Production name exist
        int productionNameId = -1;

        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO production_name(" +
                            "name) " +             //1
                            "VALUES (?)",
                    PreparedStatement.RETURN_GENERATED_KEYS
            );
            stmt.setString(1, productionName);

            stmt.execute();

            // retrieves id of inserted production Name
            ResultSet resultSet = stmt.getGeneratedKeys();
            resultSet.next();
            productionNameId = resultSet.getInt(1);

            stmt.close();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return productionNameId;
    }

    public List<String> getGenres(int prod_id) {
        // returns all genres associated with an production

        List<String> returnList = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT genre " +
                            "FROM genre " +
                            "JOIN genres_production_association ON genre.id = genres_production_association.genre_id " +
                            "WHERE production_id = ?;");
            stmt.setInt(1, prod_id);

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                returnList.add(resultSet.getString(1));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return returnList;
    }

    @Override
    public void validateProduction(int productionID) {
        try {

            PreparedStatement stmt = connection.prepareStatement(
                    "UPDATE production SET validated = true WHERE id = ?");
            stmt.setInt(1, productionID);
            stmt.execute();

            stmt.close();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void invalidateProduction(int productionID) {
        try {

            PreparedStatement stmt = connection.prepareStatement(
                    "UPDATE production SET validated = false WHERE id = ?");
            stmt.setInt(1, productionID);
            stmt.execute();

            stmt.close();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void validateCredit(Credit credit) {
        int creditID = getCreditId(credit.getProductionId(), credit);

        try {

            PreparedStatement stmt = connection.prepareStatement(
                    "UPDATE credit SET validated = true WHERE id = ?");
            stmt.setInt(1, creditID);
            stmt.execute();

            stmt.close();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void invalidateCredit(Credit credit) {
        int creditID = getCreditId(credit.getProductionId(), credit);
        try {

            PreparedStatement stmt = connection.prepareStatement(
                    "UPDATE credit SET validated = false WHERE id = ?");
            stmt.setInt(1, creditID);
            stmt.execute();

            stmt.close();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }


    // Method which recieves a Date-object og converts to a dataformat which can be used as TIMESTAMP in database

    public String dateFormatter(Date date) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }


    //ERSTATTER ENUMS
    // CreditType
    @Override
    public List<String> getAllCreditTypes() {

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM credit_type");
            ResultSet sqlReturnValues = stmt.executeQuery();

            List<String> returnValue = new ArrayList<>();

            while (sqlReturnValues.next()) {
                returnValue.add(sqlReturnValues.getString(2));
            }
            return returnValue;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    // Production types
    @Override
    public List<String> getAllProductionTypes() {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM production_type");
            ResultSet sqlReturnValues = stmt.executeQuery();

            List<String> returnValue = new ArrayList<>();

            while (sqlReturnValues.next()) {
                returnValue.add(sqlReturnValues.getString(2));
            }
            return returnValue;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    // Language
    @Override
    public List<String> getAllLanguages() {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM language");
            ResultSet sqlReturnValues = stmt.executeQuery();

            List<String> returnValue = new ArrayList<>();

            while (sqlReturnValues.next()) {
                returnValue.add(sqlReturnValues.getString(2));
            }
            return returnValue;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    // Genres
    @Override
    public List<String> getAllGenres() {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM genre");
            ResultSet sqlReturnValues = stmt.executeQuery();

            List<String> returnValue = new ArrayList<>();

            while (sqlReturnValues.next()) {
                returnValue.add(sqlReturnValues.getString(2));
            }
            return returnValue;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }


    // private metoder til at finde ID på foreign keys i tabeller, -1 hvis ikke findes
    private int getProdCompanyId(String productionCompany) {
        try {
            PreparedStatement stmtGetCompanyId = connection.prepareStatement("SELECT id FROM production_company WHERE name = ?");
            stmtGetCompanyId.setString(1, productionCompany);
            ResultSet sqlReturnValues = stmtGetCompanyId.executeQuery();

            // checks if the ResultSet is empty and returns -1 if that's the case
            if (!sqlReturnValues.next()) {
                System.out.println("ResultSet is empty");
                return -1;
            } else {
                return sqlReturnValues.getInt(1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    private int getProdTypeId(String productionType) {
        try {
            PreparedStatement stmtGetTypeId = connection.prepareStatement("SELECT id FROM production_type WHERE type = ?");
            stmtGetTypeId.setString(1, productionType);
            ResultSet sqlReturnValues = stmtGetTypeId.executeQuery();

            // checks if the ResultSet is empty and returns -1 if that's the case
            if (!sqlReturnValues.next()) {
                System.out.println("ResultSet is empty");
                return -1;
            } else {
                return sqlReturnValues.getInt(1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    private int getProductionNameId(String productionName) {
        try {
            PreparedStatement stmtGetNameId = connection.prepareStatement("SELECT id FROM production_name WHERE name = ?");
            stmtGetNameId.setString(1, productionName);
            ResultSet sqlReturnValues = stmtGetNameId.executeQuery();

            // checks if the ResultSet is empty and returns -1 if that's the case
            if (!sqlReturnValues.next()) {
                System.out.println("ResultSet is empty");
                return -1;
            } else {
                return sqlReturnValues.getInt(1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    private int getLanguageId(String language) {
        try {
            PreparedStatement stmtGetNameId = connection.prepareStatement("SELECT id FROM language WHERE language = ?");
            stmtGetNameId.setString(1, language);
            ResultSet sqlReturnValues = stmtGetNameId.executeQuery();

            // checks if the ResultSet is empty and returns -1 if that's the case
            if (!sqlReturnValues.next()) {
                System.out.println("ResultSet is empty");
                return -1;
            } else {
                return sqlReturnValues.getInt(1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    // Returnerer id på creditType der matcher navnet
    private int getCreditTypeId(String creditType) {
        try {
            PreparedStatement stmtGetTypeId = connection.prepareStatement("SELECT id FROM credit_type WHERE type = ?");
            stmtGetTypeId.setString(1, creditType);
            ResultSet sqlReturnValues = stmtGetTypeId.executeQuery();

            // checks if the ResultSet is empty and returns -1 if that's the case
            if (!sqlReturnValues.next()) {
                System.out.println("ResultSet is empty");
                return -1;
            } else {
                return sqlReturnValues.getInt(1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    private int getCreditNameId(String creditFirstName, String creditLastName) {
        try {
            PreparedStatement stmtGetTypeId = connection.prepareStatement("SELECT id FROM credit_name WHERE first_name = ? AND last_name = ?");
            stmtGetTypeId.setString(1, creditFirstName);
            stmtGetTypeId.setString(2, creditLastName);
            ResultSet sqlReturnValues = stmtGetTypeId.executeQuery();

            // checks if the ResultSet is empty and returns -1 if that's the case
            if (!sqlReturnValues.next()) {
                System.out.println("ResultSet is empty");
                return -1;
            } else {
                return sqlReturnValues.getInt(1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("person eksisterer ikke");
            return -1;
        }
    }

    private int getGenreId(String genre) {
        try {
            PreparedStatement stmtGenreId = connection.prepareStatement("SELECT id FROM genre WHERE genre = ?");
            stmtGenreId.setString(1, genre);
            ResultSet sqlReturnValues = stmtGenreId.executeQuery();

            // checks if the ResultSet is empty and returns -1 if that's the case
            if (!sqlReturnValues.next()) {
                System.out.println("ResultSet is empty");
                return -1;
            } else {
                return sqlReturnValues.getInt(1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    private int getProductionId(Production production) {
        try {
            PreparedStatement stmtGenreId = connection.prepareStatement("SELECT id " +
                    "FROM production " +
                    "WHERE season = ? AND episode = ? AND production_name_id = ?");
            stmtGenreId.setInt(1, production.getSeason());
            stmtGenreId.setInt(2, production.getEpisode());
            stmtGenreId.setInt(3, getProductionNameId(production.getName()));

            ResultSet sqlReturnValues = stmtGenreId.executeQuery();

            // checks if the ResultSet is empty and returns -1 if that's the case
            if (!sqlReturnValues.next()) {
                System.out.println("ResultSet is empty");
                return -1;
            } else {
                return sqlReturnValues.getInt(1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    private int getCreditId(int productionId, Credit credit) {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT id FROM credit WHERE role = ? AND production_id = ?");
            stmt.setString(1, credit.getRole());
            stmt.setInt(2, productionId);
            ResultSet resultSet = stmt.executeQuery();

            // checks if the ResultSet is empty and returns -1 if that's the case
            if (!resultSet.next()) {
                System.out.println("ResultSet is empty");
                return -1;
            } else {
                return resultSet.getInt(1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }


    // method for adding production images to database
    public void insertImage(String imageUrl, String imageText, int productionId) {
        // TODO: Det er nok kun når vi tester den, at vi kan bruge en url til
        //  at finde filen. Ved ikke hvordan det fungerer med at uploade et billede
        //  i GUI'en (Simon)
        try {
            File file = new File(imageUrl);
            FileInputStream inputStream = new FileInputStream(file);
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO production_images (" +
                            "image_text, " +
                            "image, " +
                            "production_id) " +
                            "VALUES (?, ?, ?)"
            );
            stmt.setString(1, imageText);
            stmt.setBinaryStream(2, inputStream, (int) file.length());
            stmt.setInt(3, productionId);
            stmt.execute();
            stmt.close();

        } catch (FileNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

}

// TODO: ret danske kommentarer til engelsk