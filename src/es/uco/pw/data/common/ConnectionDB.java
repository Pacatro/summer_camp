package es.uco.pw.data.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionDB {
    private Properties configProperties;
    private Connection conn;
    private String host;
    private String dbName;
    private String username;
    private String password;
    private String url;
    
    public ConnectionDB() throws FileNotFoundException, IOException {
        this.configProperties = new Properties();
        this.configProperties.load(new FileInputStream("config.properties"));

        this.conn = null;
        this.host = this.configProperties.getProperty("DB_HOST");
        this.dbName = this.configProperties.getProperty("DB_NAME");
        this.username = this.configProperties.getProperty("DB_USER");
        this.password = this.configProperties.getProperty("DB_PASSWORD");
    
        this.url = "jdbc:mysql://" + host + ":3306/" + dbName;
    }

    public Connection getConnection() throws Exception{
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.conn = DriverManager.getConnection(this.url, this.username, this.password);
        } catch (Exception e) {
            throw e;
        }

        return this.conn;
    }
}
