package Modele.dto;

/**
 * Représentation objet d'un luvrable
 */
public class Livrable {

    //données obligatoire
    private String date, dateDepot, archive;

    public Livrable(String date, String dateDepot, String archive){
        this.date = date;
        this.dateDepot = dateDepot;
        this.archive = archive;
    }

    //--------------GETTER--------------//
    public String getDate(){
        return date;
    }

    public String getDateDepot(){
        return dateDepot;
    }

    public String getArchive(){
        return archive;
    }

    //------------SETTER------------//
    public void setDate(String date) {
        this.date = date;
    }

    public void setArchive(String archive) {
        this.archive = archive;
    }
}
