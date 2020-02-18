package Modele.connections;

import java.sql.SQLException;

/**
 * Pattern factory
 * Fournit une connection unique en fonction du système de données
 * auquel l'on souhaite se connecter
 */
public class ConnectionFactory {
    public enum CONNECTION_TYPE{MONGO,MARIADB,KAFKA}
    public static Connection getConnection(CONNECTION_TYPE type) throws SQLException {
        switch (type){
            case MONGO:
                return MongoConnection.getInstance();
            case MARIADB:
                return MariadbConnection.getInstance();
            case KAFKA:
                //todo ajouter une connection pour kafka
                return null;
            default:
                return null;
        }
    }
}
