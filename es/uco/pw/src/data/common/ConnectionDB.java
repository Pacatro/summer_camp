package data.common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionDB {

    private Properties SQLproperties;
    private String host;
    private String dbName;
    private String userName;
    private String password;
    private String url;
    
    public ConnectionDB(String propertiesPath) {
        // this.SQLproperties = new Properties();
        // this.SQLproperties.load(new FileInputStream(propertiesPath));
    }

    public void setSQLproperties(Properties SQLproperties) {
        this.SQLproperties = SQLproperties;
    }

    public Connection getConnection(){
        Connection conn = null;
        
        this.host = this.SQLproperties.getProperty("DB_HOST");
        this.dbName = this.SQLproperties.getProperty("DB_NAME");
        this.userName = this.SQLproperties.getProperty("DB_USER");
        this.password = this.SQLproperties.getProperty("DB_PASSWORD");

        this.url = "jdbc:mysql://" + host + "/" + dbName;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, userName, password);
        } catch (Exception e) {
            System.out.println(e);
        }

        return conn;
    }
}
