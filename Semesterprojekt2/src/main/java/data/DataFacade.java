package data;

import Interfaces.DataLayerInterface;
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

public class DataFacade  { // add implements DataLayerInterface

    private static DataFacade instance;
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


}