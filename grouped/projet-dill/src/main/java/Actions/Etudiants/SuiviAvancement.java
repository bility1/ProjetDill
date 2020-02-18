package Actions.Etudiants;

import Global.Environnement;
import Modele.dto.ActeurDTO;
import Modele.dto.AuthentificationDTO;
import Modele.dto.Voeux;
import kafka.KafkaConsumerExample;

import java.util.ArrayList;
import java.util.List;

;

public class SuiviAvancement extends Environnement {
    // ajouter le module javax.servlet:jstl:1.2

    public List<Voeux> getVoeux() {
        try {
            return getActeurDAO().getEtudiant(((AuthentificationDTO) getSession().get(SESSION_NAME)).getAdresseEmail()).getVoeux();
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public String execute() throws Exception {
        return SUCCESS;

    }

}