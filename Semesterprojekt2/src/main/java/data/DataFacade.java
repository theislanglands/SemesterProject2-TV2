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
    private String url = "localhost";
    private int port = 5432;
    private String databaseName = "";
    private String username = "";
    private String password = "";

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
}