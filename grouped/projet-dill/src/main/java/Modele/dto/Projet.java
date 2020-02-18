package Modele.dto;
import java.util.ArrayList;
import java.util.List;

/**
 * Pattern Builder pour la représentation objet d'un projet
 */
public class Projet {

    //données obligatoire
    private String nom, ville;
    private int id;

    //données non obligatoire
    private String description, logo,fichier;
    private List<Livrable> livrables;

    private Projet(ProjetBuilder builder){
        nom = builder.nom;
        ville = builder.ville;
        id=builder.id;
        description = builder.description;
        logo = builder.logo;
        fichier = builder.fichier;
        livrables = builder.livrables;
    }

    //--------------GETTER-----------//
    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public String getLogo() {
        return logo;
    }

    public String getFichier() {
        return fichier;
    }

    public List<Livrable> getLivrables() {
        return livrables;
    }

    public String getVille(){return ville;}

    public int getId() { return id; }



    //----------------SETTER-----------//
    public void setDescription(String description) {
        this.description = description;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setFichier(String fichier) {
        this.fichier = fichier;
    }
    public void setId(int id) { this.id = id; }

    //-------------BUILDER---------//
    public static class ProjetBuilder{
        //données obligatoire
        private final String nom, ville;
        private final int id;

        //données non obligatoire
        private String description, logo,fichier;
        private List<Livrable> livrables;

        public ProjetBuilder(String nom, String ville, int id){
            this.nom = nom;
            this.ville = ville;
            this.id=id;
            livrables = new ArrayList<>();
        }

        public ProjetBuilder description(String description){
            this.description = description;
            return this;
        }

        public ProjetBuilder logo(String logo){
            this.logo = logo;
            return this;
        }

        public  ProjetBuilder fichier(String fichier){
            this.fichier = fichier;
            return this;
        }

        public ProjetBuilder livrables(List<Livrable> livrables){
            this.livrables = livrables;
            return this;
        }

        public Projet build(){
            return new Projet(this);
        }
    }
}
