package Actions.Etudiants;
import Global.Environnement;
import Modele.dto.AuthentificationDTO;
import Modele.dto.Voeux;
import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;
import java.util.List;

public class ListeDesVoeux extends Environnement {

    // ==================== Attributs ====================
    public List<Voeux> listevoeux;
    private AuthentificationDTO utilisateur ;
    private String adressemail ;

    // ==================== Getters/Setters ====================

    public List<Voeux> getListevoeux() {
        return listevoeux;
    }

    public void setListevoeux(List<Voeux> listevoeux) { this.listevoeux = listevoeux; }



public List<Voeux> getListVoeux(){
        utilisateur=((AuthentificationDTO) getSession().get(SESSION_NAME));
    adressemail= utilisateur.getAdresseEmail();

    listevoeux = getActeurDAO().listeDesVoeux(adressemail);
    System.out.println("LISTE DES VOEUX"+listevoeux.toString());
        return listevoeux;

}
    // ================== METHODES ====================

   /* public String doListeVoeux(){
        String adresse = "cecile.manivannin@etu.univ-orleans.fr" ;
        listevoeux=getActeurDAO().listeDesVoeux(adresse);
        if (listevoeux == null){
            return ActionSupport.INPUT;
        }else
        for (Voeux v : listevoeux){
            System.out.println("ACTION"+v.toString());
        }
        return ActionSupport.SUCCESS ;
    }

*/

}
