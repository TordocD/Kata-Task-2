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


    public static Connection getConnection() throws SQLException {
        Properties properties = new Properties();

        try (FileInputStream fis = new FileInputStream("src/main/resources/config.properties")) {
            properties.load(fis);
            dbURL = properties.getProperty("db.host");
            dbUsername = properties.getProperty("db.userName");
            dbPassword = properties.getProperty("db.password");
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (FileNotFoundException e) {
            System.err.println("File config.properties not found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException  e) {
            System.err.println("SQL driver not found");
        }
        return DriverManager.getConnection(dbURL, dbUsername, dbPassword);
    }

    public static Connection getConnection(String dbHost, String dbName, String dbUsername, String dbPassword)
            throws SQLException {

        String dbURL = "jdbc:mysql//" + dbHost + ":3306/" + dbName;
        return DriverManager.getConnection(dbURL, dbUsername, dbPassword);
    }
}