package es.uco.pw.data.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Manages the database connection and provides methods for obtaining a connection.
 */
public class ConnectionDB {
    private Properties configProperties;
    private Connection conn;
    private String host;
    private String dbName;
    private String username;
    private String password;
    private String url;
    
    /**
     * Constructor for ConnectionDB. Loads configuration properties and sets up the connection URL.
     * @throws FileNotFoundException If the configuration file is not found.
     * @throws IOException If an I/O error occurs.
     */
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

    /**
     * Gets a connection to the database.
     * @return A Connection object representing the database connection.
     * @throws Exception If an error occurs while attempting to establish the connection.
     */
    public Connection getConnection() throws Exception{
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.conn = DriverManager.getConnection(this.url, this.username, this.password);
            return this.conn;
        } catch (Exception e) { throw new DataException("No se puede conectar a la base de datos."); }
    }
}
