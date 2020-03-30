package daos;

import java.sql.Connection;;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {
    static String databaseUrl = "jdbc:mysql://localhost:3306/JDBC-DAO";
    static String user = "zcuser";
    static String password = "zcpass";
    static Logger LOGGER = Logger.getLogger("jdbc");

    /**
     * Get a connection to database
     *
     * @return Connection object
     */
    public static void main(String[] args) {
        LOGGER.log(Level.INFO, "status");
        System.out.println("MySQL JDBC Connection");
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC Driver not found, problems dawg!!!");
            return;
        }
        System.out.println("JDBC Driver is registered, Oh what a relief!");


        Connection connection = null;

        try {
            connection = DriverManager.getConnection(databaseUrl, user, password);
            System.out.println("SQL connection to database!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error connecting to the database");
            return;
        } finally {
            try {
                if (connection != null)
                    connection.close();
                System.out.println("Connection closed !!");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}




