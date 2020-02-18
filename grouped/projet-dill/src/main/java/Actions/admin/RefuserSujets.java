package Actions.admin;

import Modele.AbstractDAOFactory;
import Modele.dao.ActeurDAO;
import Modele.dao.MongoDAOFactory;
import Modele.dto.ActeurDTO;
import Modele.dto.AuthentificationDTO;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class RefuserSujets extends ActionSupport implements SessionAware {


    AuthentificationDTO utilisateurDTO;
    Map<String,Object> varSession;
    List<ActeurDTO> acteursDTO;
    ActeurDAO acteurDAO;
    String nom;

    Modele.dao.DAO DAO;
    {
        try {
            DAO = ((MongoDAOFactory) AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_TYPE.MONGO)).getActeurDAO();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String execute() throws Exception {
        System.out.println(nom);
        ((ActeurDAO) DAO).refuserSujets(nom);
        return SUCCESS;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.varSession = map;

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
