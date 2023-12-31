package es.uco.pw.data.common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
    public ConnectionDB(Properties configProperties) throws FileNotFoundException, IOException {
        this.configProperties = configProperties;

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
            System.out.println("DB Connected");
            return this.conn;
        } catch (Exception e) { throw new DataException("No se puede conectar a la base de datos."); }
    }

    /**
     * Disconnects from the database.
     * @throws Exception If an error occurs while closing the connection.
     */
    public void disconnect() throws SQLException {
        if (this.conn != null && !this.conn.isClosed()) {
            System.out.println("DB disconnected");
            this.conn.close();
        }
    }
}
