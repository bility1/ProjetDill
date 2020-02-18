package Modele.dto;

/**
 * Pattern builder pour l'objet qui représente un sujet
 */
public class Sujet {
    public enum Statut{VALIDER,REFUSER,EN_ATTENTE}


    //données obligatoire
    private String nom, ville;
    private Statut statut;

    //données non obligatoire
    private String description,logo,fichier,tuteurEntreprise;


    private Sujet(SujetBuilder builder){
        this.nom = builder.nom;
        this.ville = builder.ville;
        this.statut = builder.statut;
        this.description = builder.description;
        this.logo = builder.logo;
        this.fichier = builder.fichier;
        this.tuteurEntreprise = builder.tuteurEntreprise;
    }

    //------------------GETTER--------------//
    public String getNom() {
        return nom;
    }

    public Statut getStatut() {
        return statut;
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

    public String getVille(){return ville;}

    public String getTuteurEntreprise() {
        return tuteurEntreprise;
    }



    //---------------SETTER-------------------//
    public void setStatut(Statut statut){
        this.statut = statut;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setLogo(){
        this.logo = logo;
    }

    public void setFichier(String fichier){
        this.fichier = fichier;
    }
    public void setTuteurEntreprise(String tuteurEntreprise) {
        this.tuteurEntreprise = tuteurEntreprise;
    }

    //--------------BUILDER---------------//
    public static class SujetBuilder{
        private final String nom, ville;
        private Statut statut;
        private String description,logo,fichier, tuteurEntreprise;

        public SujetBuilder(String nom, String ville, Statut statut){
            this.nom = nom;
            this.ville = ville;
            this.statut = statut;
        }

        public SujetBuilder description(String description){
            this.description = description;
            return this;
        }

        public SujetBuilder logo(String logo){
            this.logo = logo;
            return this;
        }

        public SujetBuilder fichier(String fichier){
            this.fichier = fichier;
            return this;
        }

        public SujetBuilder tuteurEntreprise(String tuteur)
        {
            this.tuteurEntreprise = tuteur;
            return this;

        }
        public Sujet build(){return new Sujet(this);}
    }
}
