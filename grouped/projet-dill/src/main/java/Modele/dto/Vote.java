package Modele.dto;

/**
 * Représentation objet d'un vote
 */
public class Vote {

    //données obligatoire
    private String nomProjet;
    private int position;

    public Vote(String nomProjet, int position){
        this.nomProjet = nomProjet;
        this.position = position;
    }

    //------------------GETTER--------------------//
    public String getNomProjet(){
        return nomProjet;
    }


    public int getPosition(){
        return position;
    }

    //----------------SETTER------------------------//
    public void setNomProjet(String nomProjet) {
        this.nomProjet = nomProjet;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
