package jm.task.core.jdbc.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static String dbURL;
    private static String dbUsername;
    private static String dbPassword;


    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Properties properties = new Properties();

        try (FileInputStream fis = new FileInputStream("src/main/resources/config.properties")) {
            properties.load(fis);
            dbURL = properties.getProperty("db.host");
            dbUsername = properties.getProperty("db.userName");
            dbPassword = properties.getProperty("db.password");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

        return DriverManager.getConnection(dbURL, dbUsername, dbPassword);
    }

    public static Connection getConnection(String dbHost, String dbName, String dbUsername, String dbPassword)
            throws SQLException {

        String dbURL = "jdbc:mysql//" + dbHost + ":3306/" + dbName;
        return DriverManager.getConnection(dbURL, dbUsername, dbPassword);
    }
}