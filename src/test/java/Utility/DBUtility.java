package Utility;

import java.sql.*;

public class DBUtility {

    private final static String userName = ConfigurationReader.getValue("JDBC_UserName"),
            password = ConfigurationReader.getValue("JDBC_PassWord"),
            url = ConfigurationReader.getValue("JDBC_URL");

    private static Connection connection;
    private static Statement statement;

    public static DatabaseMetaData metaData;

    static {
        try {
            connection = DriverManager.getConnection(url, userName, password);
            statement = connection.createStatement();
            metaData = connection.getMetaData();
        } catch (SQLException e) { }
    }

    // creates result
    public static ResultSet getResult(String sql) {
        ResultSet result = null;
        try {
            result = statement.executeQuery(sql);
        } catch (SQLException e) {
        }
        return result;
    }

    // closes the connection at the last step
    public static void tearDown() {
        try {
            connection.close();
        } catch (Exception e) {
        }
    }

    public static void UpdateQuery(String sql) {
        // Insert, Update, Delete, Alter, Truncate, Drop
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {

        }
    }
}
