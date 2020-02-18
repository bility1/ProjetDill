package Actions.entreprise;

import Global.Environnement;
import Modele.AbstractDAOFactory;
import Modele.dao.DAO;
import Modele.dao.MongoDAOFactory;
import Modele.dto.ActeurDTO;
import Modele.dto.AuthentificationDTO;
import Modele.dto.Sujet;

import java.sql.SQLException;
import java.util.List;

public class MesPropositions extends Environnement {

    List<Sujet> mesSujets;


    @Override
    public String execute() throws Exception {


        AuthentificationDTO  acteur = (AuthentificationDTO) getSession().get(SESSION_NAME);

         mesSujets = getActeurDAO().findByEmail(acteur.getAdresseEmail()).getSujets();

        return SUCCESS;

    }

    public List<Sujet> getMesSujets() {
        return mesSujets;
    }

    public void setMesSujets(List<Sujet> mesSujets) {
        this.mesSujets = mesSujets;
    }
}
