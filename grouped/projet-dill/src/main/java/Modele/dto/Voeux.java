package Modele.dto;

/**
 * Représentation objet d'un voeux
 */
public class Voeux {
    public enum EtatVoeux{VALIDER,EN_ATTENTE,REFUSER}

    //données obligatoire
    private String nomProjet;
    private int position;
    private EtatVoeux etat;

    public Voeux(String nomProjet, int position){
        this.nomProjet = nomProjet;
        this.position = position;
        etat = EtatVoeux.EN_ATTENTE;
    }

    //----------GETTER---------------//
    public String getNomProjet(){
        return nomProjet;
    }

    public  int getPosition(){
        return position;
    }

    public EtatVoeux getEtat() {
        return etat;
    }
    //---------------SETTER--------------//

    public void setNomProjet(String nomProjet) {
        this.nomProjet = nomProjet;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Voeux{" +
                "nomProjet='" + nomProjet + '\'' +
                ", position=" + position +
                ", etat=" + etat +
                '}';
    }

    public void setEtat(EtatVoeux etat) {
        this.etat = etat;
    }
}
