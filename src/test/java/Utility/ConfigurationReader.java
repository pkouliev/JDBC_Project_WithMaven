package Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {

    private static Properties configFile;

    private static FileInputStream input;

    static {
        try {
            String path = "configuration.properties";

            input = new FileInputStream(path);

            configFile = new Properties();

            configFile.load(input);

            input.close();

        } catch (IOException e) {
            System.out.println("Failed to load properties file!");
            e.printStackTrace();
        }
    }

    public static String getValue(String key) {
        return configFile.getProperty(key);
    }

    public static void main(String[] args) {
        String URL = getValue("JDBC_URL");
        String userName = getValue("JDBC_UserName");
        String password = getValue("JDBC_PassWord");

        System.out.println(URL);
        System.out.println(userName);
        System.out.println(password);
    }
}
