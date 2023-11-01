package data.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionDB {
    private Properties sqlProperties;
    private Connection conn;
    private String host;
    private String dbName;
    private String username;
    private String password;
    private String url;
    
    public ConnectionDB(Properties sqlProperties) {
        this.sqlProperties = sqlProperties;

        this.conn = null;
        this.host = this.sqlProperties.getProperty("DB_HOST");
        this.dbName = this.sqlProperties.getProperty("DB_NAME");
        this.username = this.sqlProperties.getProperty("DB_USER");
        this.password = this.sqlProperties.getProperty("DB_PASSWORD");
    
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