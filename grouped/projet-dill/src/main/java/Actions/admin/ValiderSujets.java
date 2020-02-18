package Actions.admin;

import Global.Environnement;

public class ValiderSujets extends Environnement {
    String nom;

@Override
    public String execute() throws Exception {
        getActeurDAO().validerSujets(nom);
        return SUCCESS;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

}
