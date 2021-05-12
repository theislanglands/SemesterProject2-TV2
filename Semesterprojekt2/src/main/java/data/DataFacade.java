package data;

import Interfaces.DataLayerInterface;
import domain.Credit;
import domain.CreditName;
import domain.Production;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            connection = DriverManager.getConnection("jdbc:postgresql://" + url + ":" + port + "/" + databaseName, username, password);
        } catch (SQLException | IllegalArgumentException ex) {
            ex.printStackTrace(System.err);
        } finally {
            if (connection == null) System.exit(-1);
        }
    }

    // Her implementeres metoder fra interface!

    @Override
    public boolean createProduction(Production prod) {
        try {
            //Begin statement - Transaction
            connection.setAutoCommit(false);

            //
            PreparedStatement stmt1 = connection.prepareStatement(
                    "INSERT INTO production (season, episode, release_date, length, subtitle, sign_Language, " +
                            "active, validated, production_reference, production_company_id, " +
                            "production_type_id, language_id, production_name_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt1.setInt(1, prod.getSeason());
            stmt1.setInt(2, prod.getEpisode());
            stmt1.setDate(3, (java.sql.Date) prod.getReleaseDate());
            stmt1.setInt(4, prod.getLength());
            stmt1.setBoolean(5, prod.isHasSubtitle());
            stmt1.setBoolean(6, prod.isHasSignLanguage());
            stmt1.setBoolean(7, prod.isActive());
            stmt1.setBoolean(8, prod.isValidated());
            stmt1.setString(9, prod.getProductionReference());


            // hjælpemetoder til at finde ID på foreign keys
            stmt1.setInt(10, getProdCompanyId(prod.getProductionCompanyName()));
            stmt1.setInt(11, getProdTypeId(prod.getProductionType()));
            stmt1.setInt(12, getLanguageId(prod.getLanguage()));
            stmt1.setInt(13, getNameId(prod.getName()));

            // commit changes
            connection.commit();

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

        List<Production> productions = new ArrayList<>();
        // for loop, løber igennem alle produktioner
        int count=0;

        try {
            // Find ud af hvor mange produktioner vi har
            PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) FROM production");
            ResultSet sqlReturnValues = stmt.executeQuery();

            while (sqlReturnValues.next()) {
                count = sqlReturnValues.getInt(1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

        System.out.println("antal produktioner "+ count);

        /*
        Production prodTest = new Production();
        prodTest.setLength(40);
        prodTest.setName("Test");
        productions.add(prodTest);
        */
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
                                "production.season, " +         // 1
                                "production.episode, " +        // 2
                                "production.release_date, " +   // 3
                                "production.length, " +         // 4
                                "production.subtitle, " +       // 5
                                "production.sign_language, " +  // 6
                                "production.active, " +         // 7
                                "production.validated, " +      // 8
                                "production.production_reference, " +   // 9
                                "production_company.name, " +   //10
                                "production_type.type, "  +     // 11
                                "language.language, " +         // 12
                                "production_name.name " +       // 13
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
                returnProduction.setHasSubtitle(sqlReturnValues.getBoolean(5));
                returnProduction.setHasSignLanguage(sqlReturnValues.getBoolean(6));
                returnProduction.setActive(sqlReturnValues.getBoolean(7));
                returnProduction.setValidated(sqlReturnValues.getBoolean(8));
                returnProduction.setId(sqlReturnValues.getString(9));
                returnProduction.setCompanyProductionName(sqlReturnValues.getString(10));
                returnProduction.setType(sqlReturnValues.getString(11));
                returnProduction.setLanguage(sqlReturnValues.getString(12));
                returnProduction.setName(sqlReturnValues.getString(13));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

        return returnProduction;
    }

    @Override
    public void deleteProduction(Production prod) {

    }

    @Override
    public boolean updateProduction(String productionID, Production replaceProduction) {
        return false;
    }

    @Override
    public void createCredits(Credit cred, Production prod) {
        try {
            //credit_name


            cred.getCreditType();

            PreparedStatement stmtCreditName = connection.prepareStatement(
                    "INSERT INTO credit_name (first_name,last_name,address,phone,email) VALUES (?,?,?,?,?)");
            stmtCreditName.setString(1, cred.getFirstName());
            stmtCreditName.setString(2, cred.getLastName());
            stmtCreditName.setString(3, cred.getAddress());
            stmtCreditName.setString(4, cred.getPhone());
            stmtCreditName.setString(5, cred.getEmail());

            PreparedStatement stmtCredit = connection.prepareStatement(
                    "INSERT INTO credit (role, validated, production_id) VALUES (?, ?, ?)");
            stmtCredit.setString(1, cred.getRole());
            stmtCredit.setBoolean(2, cred.isValidated());
            //stmtCredit.setInt(3, prod.get)





            //credit_name_credit_type_association
            // Vi laver denne, når der er fundet en løsning!

            //credit
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

    }

    @Override
    public List<Credit> getCredits() {
        return null;
    }

    @Override
    public Credit getCredit(int creditID) {
        return null;
    }

    @Override
    public void deleteCredit(int creditID) {

    }

    @Override
    public boolean updateCredit(int creditID, Credit replaceCredit) {
        return false;
    }

    @Override
    public void createPerson(CreditName pers) {

    }

    @Override
    public List<CreditName> getPerson() {
        return null;
    }

    @Override
    public CreditName getPerson(int personID) {
        return null;
    }

    @Override
    public void deletePerson(int personID) {

    }

    @Override
    public boolean updatePerson(int personID, CreditName replaceCreditName) {
        return false;
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
    public List<String> getLanguages(){
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
    public List<String> getGenres() {
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


    // Interne metoder til at finde ID på foreign keys i tabeller

    private int getProdCompanyId(String productionCompany) {
        try {
            PreparedStatement stmtGetCompanyId = connection.prepareStatement("SELECT * FROM production_company WHERE name = ?");
            stmtGetCompanyId.setString(1, productionCompany);
            ResultSet sqlReturnValues = stmtGetCompanyId.executeQuery();
            return sqlReturnValues.getInt(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    private int getProdTypeId(String productionType) {
        try {
            PreparedStatement stmtGetTypeId = connection.prepareStatement("SELECT * FROM production_type WHERE type = ?");
            stmtGetTypeId.setString(1, productionType);
            ResultSet sqlReturnValues = stmtGetTypeId.executeQuery();
            return sqlReturnValues.getInt(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    private int getNameId(String productionName) {
        try {
            PreparedStatement stmtGetNameId = connection.prepareStatement("SELECT * FROM production_name WHERE name = ?");
            stmtGetNameId.setString(1, productionName);
            ResultSet sqlReturnValues = stmtGetNameId.executeQuery();
            return sqlReturnValues.getInt(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    private int getLanguageId(String language) {
        try {
            PreparedStatement stmtGetNameId = connection.prepareStatement("SELECT * FROM language WHERE language = ?");
            stmtGetNameId.setString(1, language);
            ResultSet sqlReturnValues = stmtGetNameId.executeQuery();
            return sqlReturnValues.getInt(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }




    public static void main(String[] args) {
        DataFacade dbFacade = new DataFacade();
        dbFacade.initializePostgresqlDatabase();

        Production test = dbFacade.getProduction(1);
        System.out.println(test);

        dbFacade.getProductions();
    }
}

/*
  @Override



    @Override
    public Employee getEmployee(int id) {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM employees WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet sqlReturnValues = stmt.executeQuery();
            if (!sqlReturnValues.next()){
               return null;
            }
            return new Employee(sqlReturnValues.getInt(1), sqlReturnValues.getString(2), sqlReturnValues.getInt(3), sqlReturnValues.getInt(4), sqlReturnValues.getInt(5), sqlReturnValues.getInt(6));
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }


    @Override
    public boolean createEmployee(Employee employee) {
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO employees (name, phone, position_id, department_id, room_id) VALUES (?, ?, ?, ?, ?)");
            stmt.setString(1, employee.getName());
            stmt.setInt(2, employee.getPhone());
            stmt.setInt(3, employee.getPosition_id());
            stmt.setInt(4, employee.getDepartment_id());
            stmt.setInt(5, employee.getRoom_id());
            stmt.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }





 */