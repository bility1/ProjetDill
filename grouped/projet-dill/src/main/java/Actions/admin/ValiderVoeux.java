package Actions.admin;

import Global.Environnement;
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

public class ValiderVoeux extends Environnement {
    String numEtu;
    String nomVoeu;

    @Override
    public String execute() throws Exception {

        getActeurDAO().validerVoeux(numEtu,nomVoeu);
        return SUCCESS;
    }

    public String getNumEtu() {
        return numEtu;
    }

    public void setNumEtu(String numEtu) {
        this.numEtu = numEtu;
    }

    public String getNomVoeu() {
        return nomVoeu;
    }

    public void setNomVoeu(String nomVoeu) {
        this.nomVoeu = nomVoeu;
    }
}
