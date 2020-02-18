package Modele.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Pattern Singleton
 * Fournit une connection unique à la base de données mariadb
 */
class MariadbConnection implements Modele.connections.Connection<Connection> {
    private static final String PROPERTIES_FILE="mariadb";
    private static final String PROPERTIE_URL="url";
    private static final String PROPERTIE_USER="user";
    private static final String PROPERTIE_PASSWORD="password";

    private static Modele.connections.Connection connection;
    private Connection connect;

    private MariadbConnection() throws SQLException {
        ResourceBundle resource = ResourceBundle.getBundle(PROPERTIES_FILE);
        connect = DriverManager.getConnection(resource.getString(PROPERTIE_URL), resource.getString(PROPERTIE_USER), resource.getString(PROPERTIE_PASSWORD));
    }


    public Connection getConnection(){
        return connect;
    }

    protected static Modele.connections.Connection<Connection> getInstance() throws SQLException {
        if(connection == null){
            connection = new MariadbConnection();
        }
        return connection;
    }
}
