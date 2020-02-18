package Modele;

import Modele.dao.MongoDAOFactory;
import Modele.dao.SQLDAOFactory;

/**
 * Pattern Factory
 * Retourne la bonne factory en fonction du système de données
 * avec lequel on souhaite communiquer
 */
public abstract class AbstractDAOFactory {
    public enum DAO_TYPE{MONGO,SQL,KAFKA}

    public static AbstractDAOFactory getFactory(DAO_TYPE type) {
        switch (type){
            case MONGO:
                return MongoDAOFactory.getInstance();
            case SQL:
                    return SQLDAOFactory.getInstance();
            case KAFKA:
                //todo ajouter une Factory pour kafka et ces DAO
                return null;
            default:
                return null;
        }
    }
}
