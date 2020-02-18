package Global;

import Modele.AbstractDAOFactory;
import Modele.dao.*;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

import java.sql.SQLException;
import java.util.Map;

/**
 * Class qui expose à toute l'application les données de session d'autres données qui doivent être accessible
 * à toute l'application des le lancement de cette dernière
 */
public class Environnement extends ActionSupport implements ApplicationAware, SessionAware {
    //On peut les appeler de n'importe où
    public static final String DAO_ACTEUR="acteurDAO";
    public static final String DAO_AUTHENTIFICATION="authentificationDAO";
    public static final String SESSION_NAME="user";

    private Map<String, Object> session;
    private ActeurDAO acteurDAO;
    private AuthentificationDAO authentificationDAO;

    public Map<String, Object> getSession() {
        return session;
    }

    public ActeurDAO getActeurDAO() {
        return acteurDAO;
    }

    public AuthentificationDAO getAuthentificationDAO() {
        return authentificationDAO;
    }

    @Override
    public void setApplication(Map<String, Object> map) {
        acteurDAO = (ActeurDAO) map.get(DAO_ACTEUR);
        authentificationDAO = (AuthentificationDAO) map.get(DAO_AUTHENTIFICATION);

        if(acteurDAO == null){
            try {
                acteurDAO = (ActeurDAO) ((MongoDAOFactory)AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_TYPE.MONGO)).getActeurDAO();
                map.put(DAO_ACTEUR,acteurDAO);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(authentificationDAO == null){
            try {
                authentificationDAO = (AuthentificationDAO) ((SQLDAOFactory)AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_TYPE.SQL)).getAuthentificationDAO();
                map.put(DAO_AUTHENTIFICATION,authentificationDAO);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
}
