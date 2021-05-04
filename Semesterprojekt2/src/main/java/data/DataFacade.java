package data;

import Interfaces.DataLayerInterface;
import domain.Credit;
import domain.Person;
import domain.Production;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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

    private DataFacade(){
        initializePostgresqlDatabase();
    }

    public static DataFacade getInstance(){
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
    public void createProduction(Production prod) {
        
    }

    @Override
    public List<Production> getProductions() {
        return null;
    }

    @Override
    public Production getProduction(int id) {
        return null;
    }

    @Override
    public void deleteProduction(Production prod) {

    }

    @Override
    public boolean updateProduction(String productionID, Production replaceProduction) {
        return false;
    }

    @Override
    public void createCredits(Credit cred) {

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
    public void createPerson(Person pers) {

    }

    @Override
    public List<Person> getPerson() {
        return null;
    }

    @Override
    public Person getPerson(int personID) {
        return null;
    }

    @Override
    public void deletePerson(int personID) {

    }

    @Override
    public boolean updatePerson(int personID, Person replacePerson) {
        return false;
    }

    public static void main(String[] args) {
        DataFacade dbFacade = new DataFacade();
        dbFacade.initializePostgresqlDatabase();
    }
}

/*
  @Override
    public List<Employee> getEmployees() {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM employees");
            ResultSet sqlReturnValues = stmt.executeQuery();
            int rowcount = 0;
            List<Employee> returnValue = new ArrayList<>();
            while (sqlReturnValues.next()){
                returnValue.add(new Employee(sqlReturnValues.getInt(1), sqlReturnValues.getString(2), sqlReturnValues.getInt(3), sqlReturnValues.getInt(4), sqlReturnValues.getInt(5), sqlReturnValues.getInt(6)));
            }
            return returnValue;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

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