package data;

import Interfaces.DataLayerInterface;
import domain.Credit;
import domain.CreditName;
import domain.Production;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import static org.postgresql.core.SqlCommandType.SELECT;

public class DataFacade implements DataLayerInterface {

    // singeltion instance
    private static DataFacade instance;

    // database setup
    private String url = "hattie.db.elephantsql.com";
    private int port = 5432;
    private String databaseName = "hdzfvhcu";
    private String username = "hdzfvhcu";
    private String password = "5pfvgV5fp9kT6J2Z5mJ92CnEuXnofxVd";

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
        // TODO: Jeg var nødt til at skrive vores url om og tilføje stringtype=unspecified.
        //  ELlers kan man ikke fodre preparedStatements med String-variable når man skal
        //  sætte et TIMESTAMP i databasen. Det kan skrives mere smooth med attributter ligesom
        //  det var gjort før med noget som hedder Properties, men jeg kunne ikke få det til at virke,
        //  så jeg skrev bare det hele ind i URL-en i første omgang
        //  (Simon)
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            //connection = DriverManager.getConnection("jdbc:postgresql://" + url + ":" + port + "/" + databaseName, username, password);
            connection = DriverManager.getConnection("jdbc:postgresql://" + url + ":" + port + "/" + databaseName + "?" + "user=hdzfvhcu" + "&password=5pfvgV5fp9kT6J2Z5mJ92CnEuXnofxVd" + "&stringtype=unspecified");

        } catch (SQLException | IllegalArgumentException ex) {
            ex.printStackTrace(System.err);
        } finally {
            if (connection == null) System.exit(-1);
        }
    }

    // metoder fra interface!
    @Override
    public boolean createProduction(Production prod) {
        try {
            //Begin statement - Transaction
            //connection.setAutoCommit(false);

            //
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

            // hjælpemetoder til at finde ID på foreign keys
            stmt1.setInt(11, getProdCompanyId(prod.getProductionCompanyName()));
            stmt1.setInt(12, getProdTypeId(prod.getProductionType()));
            stmt1.setInt(13, getLanguageId(prod.getLanguage()));
            stmt1.setInt(14, getNameId(prod.getName()));

            // henter det generede production id
            int affectedRows = stmt1.executeUpdate();
            ResultSet resultSet = stmt1.getGeneratedKeys();
            resultSet.next();
            int prod_id = resultSet.getInt(1);

            // associerer med genrer
            for (String genre : prod.getGenres()) {

                PreparedStatement stmt2 = connection.prepareStatement(
                        "INSERT INTO genres_production_association (" +
                                "production_id, " +     //1
                                "genre_id) " +          //2
                                "VALUES (?, ?);");

                stmt2.setInt(1, prod_id);
                stmt2.setInt(2, getGenreId(genre));
                stmt2.execute();
            }
            // commit changes
            //connection.commit();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Error, could not create production.");
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Transaction is being rolled back");
                }
            }

            return false;
        }

        return true;
    }

    @Override
    public List<Production> getProductions() {

        // liste af produktioner der returneres
        List<Production> productions = new ArrayList<>();

        // Laver liste med alle id'er til alle produktioner
        List<Integer> production_ids = new ArrayList<>();

        try {
            //Eksiverer at vi vælger kun id fra production-table
            PreparedStatement stmt = connection.prepareStatement("SELECT id FROM production");
            ResultSet sqlReturnValues = stmt.executeQuery();

            //Tilføjer alle id'er til production_ids-listen
            while (sqlReturnValues.next()) {
                production_ids.add(sqlReturnValues.getInt(1));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

        //Tilføjer produktionsobjekter til listen
        for (int id : production_ids) {
            productions.add(getProduction(id));
        }
        return productions;
    }

    @Override
    public Production getProduction(int id) {
        // create new empty production
        Production returnProduction = new Production();

        try {
            // query for alle parametre i en production
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
                            "INNER JOIN production_company ON production_company.id = production.production_company_id " +
                            "INNER JOIN production_type ON production_type.id = production.production_type_id " +
                            "INNER JOIN language ON language.id = production.language_id " +
                            "INNER JOIN production_name ON production_name.id = production.production_name_id " +
                            "WHERE production.id = ?");

            stmt.setInt(1, id);

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
                returnProduction.setType(sqlReturnValues.getString(11));
                returnProduction.setLanguage(sqlReturnValues.getString(12));
                returnProduction.setName(sqlReturnValues.getString(13));
                returnProduction.setId(sqlReturnValues.getInt(14));
                returnProduction.setProductionBio(sqlReturnValues.getString(15));
            }
            stmt.close();

            PreparedStatement stmt2 = connection.prepareStatement(
                    "SELECT " +
                            "genre.genre " +    // 1
                            "FROM genre " +
                            "INNER JOIN genres_production_association ON genres_production_association.genre_id = genre.id " +
                            "WHERE genres_production_association.production_id = ?");

            stmt2.setInt(1, id);

//            ResultSet sqlReturnValues2 = stmt2.executeQuery();
//
//            while (sqlReturnValues2.next()) {
//                returnProduction.addGenre(sqlReturnValues2.getString(1));
//            }
//            stmt2.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        returnProduction.setGenres((ArrayList<String>) getGenres(id));
        returnProduction.setCredits((ArrayList<Credit>) getCredits(id));

        return returnProduction;
    }

    @Override
    public void deleteProduction(int id) {
        try {
            connection.setAutoCommit(false);

            PreparedStatement stmt1 = connection.prepareStatement(
                    "DELETE FROM genres_production_association WHERE production_id = ?");
            stmt1.setInt(1, id);
            stmt1.execute();

            PreparedStatement stmt2 = connection.prepareStatement(
                    "DELETE FROM production WHERE id = ?");
            stmt2.setInt(1, id);
            stmt2.execute();

            connection.commit();

            stmt1.close();
            stmt2.close();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

    }


    @Override
    public boolean updateProduction(int sourceProductionID, Production replaceProduction) {
        try {
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

            // måske det er her det går galt, hvis den nye indsætning ikke står i en tabel!
            // slår op i andre tabeller, og finder den korrekte foreign key
            stmt.setInt(11, getProdCompanyId(replaceProduction.getProductionCompanyName()));
            stmt.setInt(12, getProdTypeId(replaceProduction.getProductionType()));
            stmt.setInt(13, getLanguageId(replaceProduction.getLanguage()));
            stmt.setInt(14, getNameId(replaceProduction.getName()));
            // Source ID
            stmt.setInt(15, sourceProductionID);

            int rowAffected = stmt.executeUpdate();


        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }


    @Override
    public void createCredits(Credit cred, Production prod) {
        try {
            //Begin statement - Transaction
            connection.setAutoCommit(false);

            // indætter credit
            PreparedStatement stmtCredit = connection.prepareStatement(
                    "INSERT INTO credit(" +
                            "role, " +              //1
                            "validated, " +         //2
                            "production_id) " +     //3
                            "VALUES (?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS
            );
            stmtCredit.setString(1, cred.getRole());
            stmtCredit.setBoolean(2, cred.isValidated());
            stmtCredit.setInt(3, prod.getId());
            stmtCredit.execute();

            // henter primary key genereret fra indsat credit
            ResultSet resultSet = stmtCredit.getGeneratedKeys();
            resultSet.next();
            int lastInsertedCreditID = resultSet.getInt(1);


            // Indsætter navn TODO: (tjek om eksisterer???)
            PreparedStatement stmtCreditName = connection.prepareStatement(
                    "INSERT INTO credit_name(" +
                            "first_name, " +        //1
                            "last_name, " +         //2
                            "address, " +           //3
                            "phone, " +             //4
                            "email) " +             //5
                            "VALUES (?,?,?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS
            );
            stmtCreditName.setString(1, cred.getFirstName());
            stmtCreditName.setString(2, cred.getLastName());
            stmtCreditName.setString(3, cred.getAddress());
            stmtCreditName.setInt(4, cred.getPhone());
            stmtCreditName.setString(5, cred.getEmail());

            // henter id på indsatte CreditName
            resultSet = stmtCreditName.getGeneratedKeys();
            resultSet.next();
            int lastInsertedCreditNameID = resultSet.getInt(1);

            // INDSÆTTER I TABEL: credit_name_credit_type_association
            // Assosierer credit og creditname samt type
            PreparedStatement stmtCNT = connection.prepareStatement(
                    "INSERT INTO credit_name_credit_type_association(" +
                            "credit_name_id, " +        //1
                            "credit_type_id, " +         //2
                            "credit_id)" +           //3
                            "VALUES (?,?,?)"
            );

            stmtCNT.setInt(1, lastInsertedCreditNameID);
            stmtCNT.setInt(2, getCreditTypeId(cred.getCreditType()));
            stmtCNT.setInt(3, lastInsertedCreditID);

            connection.commit();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public List<Credit> getCredits(int prodID) {
        // returnerer en liste med credits der høre til produktion
        List<Credit> returnCredits = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT id FROM credit WHERE production_id = ?;");

            stmt.setInt(1, prodID);

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

        // opretter tom kreditering
        Credit returnCredit = new Credit();
        CreditName associatedName = new CreditName();

        // henter data fra tabeller
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT\n" +
                            "    credit_name.first_name,\n" +   //1
                            "    credit_name.last_name,\n" +    //2
                            "    credit_name.address,\n" +      //3
                            "    credit_name.phone,\n" +        //4
                            "    credit_name.email,\n" +        //5
                            "    credit_type.type,\n" +         //6
                            "    credit.role,\n" +              //7
                            "    credit.validated\n" +          //8
                            "FROM credit_name_credit_type_association\n" +
                            "JOIN credit_type ON credit_name_credit_type_association.credit_type_id = credit_type.id\n" +
                            "JOIN credit_name ON credit_name_credit_type_association.credit_name_id = credit_name.id\n" +
                            "JOIN credit ON credit_name_credit_type_association.credit_id = credit.id\n" +
                            "WHERE credit.id = ?;");

            stmt.setInt(1, creditID);

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                // henter parametre til CreditName og tilføjer et objekt til credit
                associatedName.setFirstName(resultSet.getString(1));
                associatedName.setLastName(resultSet.getString(2));
                associatedName.setAddress(resultSet.getString(3));
                associatedName.setPhone(resultSet.getInt(4));
                associatedName.setEmail(resultSet.getString(5));
                returnCredit.setCreditName(associatedName);

                // tilføjer resterende parametre
                returnCredit.setCreditType(resultSet.getString(6));
                returnCredit.setRole(resultSet.getString(7));
                returnCredit.setValidated(resultSet.getBoolean(8));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return returnCredit;
    }

    @Override
    public void deleteCredit(int creditID) {

    }

    @Override
    public boolean updateCredit(int creditID, Credit replaceCredit) {
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
            stmt1.setInt(3, getProductionId(getNameId(replaceCredit.getProductionName())));
            stmt1.setInt(4, creditID);


            PreparedStatement stmt2 = connection.prepareStatement(
                    "UPDATE credit_name_credit_type_association " +
                            "SET " +
                            "credit_name_id = ?, " +    // 1
                            "credit_type_id = ? " +     // 2
                            "WHERE credit_id = ?;");    // 3


            stmt2.setInt(1, getCreditNameId(replaceCredit.getCreditName().getFirstName(), replaceCredit.getCreditName().getLastName()));
            stmt2.setInt(3, getCreditTypeId(replaceCredit.getCreditType()));
            stmt2.setInt(3, creditID);

            connection.commit();


        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }


    @Override
    public void createCreditName(CreditName pers) {
        try {
            PreparedStatement stmtCreditName = connection.prepareStatement(
                    "INSERT INTO credit_name(" +
                            "first_name, " +        //1
                            "last_name, " +         //2
                            "address, " +           //3
                            "phone, " +             //4
                            "email) " +             //5
                            "VALUES (?,?,?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS
            );
            stmtCreditName.setString(1, pers.getFirstName());
            stmtCreditName.setString(2, pers.getLastName());
            stmtCreditName.setString(3, pers.getAddress());
            stmtCreditName.setInt(4, pers.getPhone());
            stmtCreditName.setString(5, pers.getEmail());

            stmtCreditName.execute();
            stmtCreditName.close();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public List<CreditName> getCreditNames() {

            try {
                PreparedStatement stmt = connection.prepareStatement("SELECT * FROM credit_name");
                ResultSet sqlReturnValues = stmt.executeQuery();

                List<CreditName> returnValue = new ArrayList<>();

                sqlReturnValues.next();
                while (sqlReturnValues.next()) {
                    CreditName creditName = new CreditName();
                    creditName.setId(sqlReturnValues.getInt(1));
                    creditName.setFirstName(sqlReturnValues.getString(2));
                    creditName.setLastName(sqlReturnValues.getString(3));
                    creditName.setAddress(sqlReturnValues.getString(4));
                    creditName.setPhone(sqlReturnValues.getInt(5));
                    creditName.setEmail(sqlReturnValues.getString(6));
                    returnValue.add(creditName);
                }
                return returnValue;

            } catch (SQLException exception) {
                exception.printStackTrace();
                return null;
            }

    }


    @Override
    public CreditName getCreditNames(int creditNameID) {
        return null;
    }

    @Override
    public void deleteCreditName(int creditNameID) {
        try {
            connection.setAutoCommit(false);

            PreparedStatement stmt2 = connection.prepareStatement(
                    "UPDATE credit_name_credit_type_association " +
                            "SET credit_name_id = ? " +
                            "WHERE credit_name_id = ?");
            stmt2.setInt(1, -1);
            stmt2.setInt(2, creditNameID);

            stmt2.execute();

            PreparedStatement stmt1 = connection.prepareStatement(
                    "DELETE FROM credit_name WHERE id = ?");
            stmt1.setInt(1, creditNameID);
            stmt1.execute();

            connection.commit();

            stmt1.close();
            stmt2.close();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }


    }

    @Override
    public boolean updateCreditName(int creditNameID, CreditName replaceCreditName) {
        return false;
    }

    public List<String> getGenres(int prod_id) {

        List<String> returnList = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement(
                        "SELECT genre " +
                             "FROM genre "+
                             "JOIN genres_production_association ON genre.id = genres_production_association.genre_id " +
                            "WHERE production_id = ?;");
            stmt.setInt(1, prod_id);

            ResultSet resultSet = stmt.executeQuery();

            while(resultSet.next()) {
                returnList.add(resultSet.getString(1));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return returnList;
    }


    // Metode som tager et Date-objekt og formaterer det til et dataformat som kan
    // bruges som TIMESTAMP i databasen
    public String dateFormatter(Date date) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }


    //ERSTATTER ENUMS
    // CreditType
    public List<String> getCreditTypes() {

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

    // Production types (erstatter enum)
    public List<String> getProductionTypes() {
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

    // Language Type (erstatter enum)
    public List<String> getLanguages() {
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

    // Genre Types (erstatter enum)
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

            sqlReturnValues.next();
            return sqlReturnValues.getInt(1);

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

            sqlReturnValues.next();
            return sqlReturnValues.getInt(1);

        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    private int getNameId(String productionName) {
        try {
            PreparedStatement stmtGetNameId = connection.prepareStatement("SELECT id FROM production_name WHERE name = ?");
            stmtGetNameId.setString(1, productionName);
            ResultSet sqlReturnValues = stmtGetNameId.executeQuery();

            sqlReturnValues.next();
            return sqlReturnValues.getInt(1);

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

            sqlReturnValues.next();
            return sqlReturnValues.getInt(1);

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

            sqlReturnValues.next();
            return sqlReturnValues.getInt(1);

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

            sqlReturnValues.next();
            return sqlReturnValues.getInt(1);

        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    private int getGenreId(String genre) {
        try {
            PreparedStatement stmtGenreId = connection.prepareStatement("SELECT id FROM genre WHERE genre = ?");
            stmtGenreId.setString(1, genre);
            ResultSet sqlReturnValues = stmtGenreId.executeQuery();

            sqlReturnValues.next();
            return sqlReturnValues.getInt(1);

        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    private int getProductionId(int productionNameId) {
        try {
            PreparedStatement stmtGenreId = connection.prepareStatement("SELECT id FROM production WHERE production_name_id = ?");
            stmtGenreId.setInt(1, productionNameId);
            ResultSet sqlReturnValues = stmtGenreId.executeQuery();

            sqlReturnValues.next();
            return sqlReturnValues.getInt(1);

        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }


    public static void main(String[] args) {
        // ** DATA FACADE TEST CENTER ** //

        // instants af datafacade
        DataFacade dbFacade = new DataFacade();

        // opretter forbindelse til DB
        dbFacade.initializePostgresqlDatabase();

        // 1 test af getProduction
        Production test = dbFacade.getProduction(1);
        //System.out.println(test);

        // 2 test af getProductions
        List<Production> productionTest = dbFacade.getProductions();
        //System.out.println(productionTest);

        // test af deleteProduction - VIRKER!!!!!!!!!!!
        /*
        dbFacade.deleteProduction(1);
        test = dbFacade.getProduction(1);
        System.out.println(test);
         */

        // test af update produktion - VIRKER!
        // Opretter produktion der skal ersttte den gamle
        Production badehotellet = new Production();
        badehotellet.setProductionReference("SF666");
        badehotellet.setName("Badehotellet");
        badehotellet.setSeason(2);
        badehotellet.setEpisode(5);
        badehotellet.setReleaseDate(new Date(100000));
        badehotellet.setLength(46);
        badehotellet.setSubtitle(true);
        badehotellet.setSignLanguage(false);
        badehotellet.setActive(true);
        badehotellet.setValidated(true);
        badehotellet.setLanguage("Dansk");
        badehotellet.setProductionBio("En ny spændende sæson af badehotellet");
        badehotellet.setType("Serie");
        badehotellet.setCompanyProductionName("SF Film Production ApS");
        badehotellet.setProductionType("Serie");

        System.out.println(badehotellet);
        dbFacade.updateProduction(1, badehotellet);
        test = dbFacade.getProduction(1);
        System.out.println(test);



        // test af createProduction()
//        Production badehotelletWrong = new Production();
//        badehotelletWrong.setProductionReference("WRONG123");
//        badehotelletWrong.setName("Badehotellet");
//        badehotelletWrong.setSeason(99);
//        badehotelletWrong.setEpisode(99);
//        badehotelletWrong.setReleaseDate(new Date(100000));
//        badehotelletWrong.setLength(99);
//        badehotelletWrong.setSubtitle(true);
//        badehotelletWrong.setSignLanguage(false);
//        badehotelletWrong.setActive(true);
//        badehotelletWrong.setValidated(true);
//        badehotelletWrong.setLanguage("Dansk");
//        badehotelletWrong.setProductionBio("En ny spændende sæson af badehotellet");
//        badehotelletWrong.setType("Serie");
//        badehotelletWrong.setCompanyProductionName("SF Film Production ApS");
//        badehotelletWrong.setProductionType("Serie");
//
//        ArrayList<String> genres = new ArrayList<>();
//        genres.add("Thriller");
//        genres.add("Animation");
//        badehotelletWrong.setGenres(genres);
//
//        System.out.println("\n\nTester createProduction()");
//        dbFacade.createProduction(badehotelletWrong);


        // test af deleteCreditName
        //dbFacade.deleteCreditName(1);

        // test af updateCredit()
        CreditName creditName = new CreditName();
        creditName.setFirstName("First Name (TEST)");
        creditName.setLastName("Last Name (TEST)");
        creditName.setPhone(99999999);
        creditName.setEmail("testperson@testperson.dk");

        dbFacade.createCreditName(creditName);



        // tester getCredit
        //Credit testGetCredit = dbFacade.getCredit(4);
        //System.out.println("\ntester hent af kreditering fra db");
        //System.out.println(testGetCredit);

        // tester getCredits
        //List<Credit> testList = dbFacade.getCredits(1);
        //System.out.println("\ntester liste af credits");
        //System.out.println(testList);

        //Tester getCreditName
        System.out.println("Tester getCreditNames");
        List<CreditName> testName = dbFacade.getCreditNames();
        for (CreditName i: testName) {
            System.out.println(i);
        }











        /* KOPIERET FRA GAMLE DATA MAIN

        public void createTestProductions() {
            // Opretter produktion 1: "Badehotellet"

            // Opretter personer
            CreditName creditName1 = new CreditName();
            creditName1.setId(1234);
            creditName1.setFirstName("Bodil");
            creditName1.setLastName("Jørgensen");
            creditName1.setAddress(null);
            creditName1.setPhone("65926104");
            creditName1.setEmail("BodilJoergensen@badehotellet.dk");

            CreditName creditName2 = new CreditName(1235, "Amalie", "Dollerup", "Bamsevej 4, 5600", "27201117", "AmalieDollerup@badehotellet.dk");

            // Opretter krediteringer
            Credit credit1 = new Credit();
            credit1.setRole("Far til Simon");
            credit1.setValidated(true);
            credit1.setPerson(creditName1);

            Credit credit2 = new Credit(creditName2, "Mor til Hans", "CreditType.Medvirkende");

            // Opretter liste med krediteringer
            ArrayList<Credit> badehotelletCredits = new ArrayList<>();
            badehotelletCredits.add(credit1);
            badehotelletCredits.add(credit2);


            // Opretter produktion
            Production badehotellet = new Production();
            badehotellet.setProductionReference("1");
            badehotellet.setName("Badehotellet");
            badehotellet.setReleaseDate(new Date(1000));
            badehotellet.setLength(42);
            badehotellet.setSubtitle(true);
            badehotellet.setSignLanguage(false);
            badehotellet.setCredits(badehotelletCredits);
            badehotellet.setActive(true);
            badehotellet.setValidated(true);

            //Opretter personer
            CreditName creditName3 = new CreditName();
            creditName3.setId(1236);
            creditName3.setFirstName("Jacob");
            creditName3.setLastName("Jacobsen");
            creditName3.setAddress("København");
            creditName3.setPhone("20568095");
            creditName3.setEmail("JacobJacobsen@DateMigNoegen.dk");

            CreditName creditName4 = new CreditName(1237, "Laura", "Laurasen", "Vejle", "52674582", "LauraLaurasen@DateMigNoegen.dk");

            // Opretter krediteringer
            Credit credit3 = new Credit();
            credit3.setRole("Medvirkende");
            credit3.setValidated(true);
            credit3.setPerson(creditName3);

            Credit credit4 = new Credit(creditName4, "En ny rolle", "CreditType.Medvirkende");

            // Oprette liste med krediteringer
            ArrayList<Credit> dateMigNoegenCredits = new ArrayList<>();
            dateMigNoegenCredits.add(credit3);
            dateMigNoegenCredits.add(credit4);

            // Oprette produktion 2
            ArrayList<Genre> genres = new ArrayList<>();
            genres.add(Genre.DRAMA);
            //Production dateMigNoegen = new Production("NF2", "Date mig nøgen", new Date(4000), genres,
            //ProductionType.SERIES, 20, Language.DANISH, true, true,
            //dateMigNoegenCredits, true, 18, true);

            // Tilføjer produktion produktionslisten
            //saveProduction(dateMigNoegen);
            */
    }
}