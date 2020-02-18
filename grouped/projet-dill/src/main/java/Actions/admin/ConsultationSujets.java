package Actions.admin;

import Global.Environnement;
import Modele.dto.Sujet;

public class ConsultationSujets extends Environnement {
    String nom;


    public Sujet getConsultationSujets() {

        return getActeurDAO().ConsultationSujets(nom);

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
