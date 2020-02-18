package Modele.dao;

import Modele.AbstractDAOFactory;
import Modele.connections.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Pattern Factory et singleton
 * donne les DAO pour la base SQL
 */
public class SQLDAOFactory extends AbstractDAOFactory {
    public static SQLDAOFactory instance;

    private SQLDAOFactory(){
    }

    public static SQLDAOFactory getInstance(){
        if(instance == null){
            instance = new SQLDAOFactory();
        }
        return instance;
    }
    /**
     * Donne le DAO qui permet de communiquer avec la table
     * "authentification" de la base SQL
     * @return
     */
    public DAO getAuthentificationDAO() throws SQLException {
            return new AuthentificationDAO(((Connection) ConnectionFactory.getConnection(ConnectionFactory.CONNECTION_TYPE.MARIADB).getConnection()));
    }

    //todo ajouter les methodes pour d'autres tables ici
}
