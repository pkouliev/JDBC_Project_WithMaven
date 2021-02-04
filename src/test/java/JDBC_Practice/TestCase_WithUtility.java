package JDBC_Practice;

import Utility.DBUtility;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestCase_WithUtility {

    @Test
    public void test1() throws SQLException {
        ResultSet result = DBUtility.getResult("select * from employees");

        while (result.next()) {
            String fullName = result.getString(2) + " " + result.getString(3);
            String  salary = result.getString("salary");
            System.out.println(fullName + " " + salary );
        }
    }

    @Test(description = "Verify that Steven King has the highest salary")
    public void test2() throws SQLException {
        ResultSet result = DBUtility.getResult("select * from employees");
        int maxSalary = 0;

        List<Integer> salaries = new ArrayList<>();
        for (int n = 0; result.next();) {
           salaries.add(result.getInt("salary"));
        }

        System.out.println(salaries);
        Collections.sort(salaries);
        System.out.println(salaries);
        maxSalary = salaries.get(salaries.size()-1);
        System.out.println("Maximum salary is " + maxSalary);

        String richestGuy = "";
        result = DBUtility.getResult("select * from employees");
        while (result.next()) {
            if(result.getInt("salary") == maxSalary) {
                richestGuy = result.getString(2) + " " + result.getString(3);
            }
        }
        System.out.println(richestGuy);

        Assert.assertEquals(richestGuy, "Steven King");
        System.out.println("Test passed. Steven King is the richest guy");

        result = DBUtility.getResult("select * from locations");

        ResultSetMetaData rsm = result.getMetaData();
        System.out.println("Total Number of Columns in Locations table: " + rsm.getColumnCount());
        System.out.println("Third column name: " + rsm.getColumnName(3));

        String[] columnNames = new String[rsm.getColumnCount()];
        for (int n = 0; n < columnNames.length; n++) {
            columnNames[n] = rsm.getColumnName(n+1);
        }

        System.out.println(Arrays.toString(columnNames));


    }
}
