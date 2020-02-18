package Actions.admin;

import Global.Environnement;
import Modele.dto.Sujet;

import java.util.List;

public class AffichageSujets extends Environnement {
    List<Sujet> sujets;

    public List<Sujet> getSujets() {

        return getActeurDAO().ListeDesSujets();
    }

    public void setSujets(List<Sujet> sujets) {
        this.sujets = sujets;
    }

}
