package Actions.admin;

import Global.Environnement;
import Modele.AbstractDAOFactory;
import Modele.dao.ActeurDAO;
import Modele.dao.DAO;
import Modele.dao.MongoDAOFactory;
import Modele.dto.ActeurDTO;
import Modele.dto.AuthentificationDTO;
import Modele.dto.Sujet;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class AffichagesVoeux extends Environnement {
    List<ActeurDTO> etudiants;


    public List<ActeurDTO> getEtudiants() {
        etudiants = getActeurDAO().affichageDesVoeux();

        return etudiants;
    }

    public void setEtudiants(List<ActeurDTO> etudiants) {
        this.etudiants = etudiants;
    }
}
