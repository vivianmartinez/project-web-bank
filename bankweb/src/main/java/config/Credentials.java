package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Credentials {
    Properties credentials = new Properties();
    protected String userName;
    protected String driver;
    protected String password;
    protected String url;

    public Credentials() {
        getCredentialsProperties();
    }

    public void getCredentialsProperties() {
        String filePath = System.getProperty("user.dir") + "\\web-bank\\config\\credentials.properties";

        // String filePath =
        // "C:\\Users\\Usuario\\workspace\\project-web-bank\\config\\credentials.properties";
        try {
            FileInputStream file = new FileInputStream(filePath);
            try {
                this.credentials.load(file);
                file.close();
                this.userName = credentials.getProperty("USER_NAME");
                this.driver = credentials.getProperty("DRIVER");
                this.password = credentials.getProperty("PASSWORD");
                this.url = credentials.getProperty("URL");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
