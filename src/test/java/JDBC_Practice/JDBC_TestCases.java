package JDBC_Practice;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.*;

public class JDBC_TestCases {

    Connection connection;
    Statement statement;

    @BeforeMethod
    public void setUp() {

        String url = "jdbc:oracle:thin:@3.84.218.226:1521:xe";
        String username = "hr";
        String password = "hr";

        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
        } catch (SQLException e) {

        }

    }

    @Test(description = "Verify that Seyfo salary is greater than Hakan' salary")
    public void test1() throws SQLException {

        int seyfoSalary = 0,
                hakanSalary = 0;

        String query = "Select * from testers";

        ResultSet result = statement.executeQuery(query);

        while (result.next()) {
            String name = result.getString(2);
            int salary = result.getInt(3);

            if (name.equals("Seyfo")) {
                seyfoSalary = salary;
            }
            if (name.equals("Hakan")) {
                hakanSalary = salary;
            }
            //System.out.println(name + " " + salary);
        }

        System.out.println("Seyfo salary: " + seyfoSalary);
        System.out.println("Hakan salary: " + hakanSalary);

        Assert.assertTrue(seyfoSalary > hakanSalary);

        System.out.println("Test passed. Seyfo salary is more than Hakan salary.");
    }

    @AfterMethod
    public void teardown() throws SQLException {
        connection.close();
        System.out.println("Connection disconnected");
    }
}
