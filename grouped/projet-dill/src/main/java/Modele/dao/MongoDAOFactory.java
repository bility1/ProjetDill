package Modele.dao;

import com.mongodb.client.MongoDatabase;
import Modele.AbstractDAOFactory;
import Modele.connections.ConnectionFactory;

import java.sql.SQLException;

/**
 * Pattern Factory et singleton
 * Fournis le DAO souhaité pour la base de données Mongo
 */
public class MongoDAOFactory extends AbstractDAOFactory {
    private static MongoDAOFactory instance;

    private MongoDAOFactory(){
    }

    public static MongoDAOFactory getInstance(){
        if(instance == null){
            instance = new MongoDAOFactory();
        }
        return instance;
    }
    /**
     * Donne le DAO qui permet de communiquer avec la collection
     * "Acteur" de la base Mongo
     * @return
     */
    public DAO getActeurDAO() throws SQLException {
        return new ActeurDAO(((MongoDatabase)ConnectionFactory.getConnection(ConnectionFactory.CONNECTION_TYPE.MONGO).getConnection()));
    }

    //todo ajouter les méthodes pour d'autres collections ici

}
