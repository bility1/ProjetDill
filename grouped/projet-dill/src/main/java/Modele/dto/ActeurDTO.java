package Modele.dto;

import kafka.KafkaConsumerExample;
import kafka.KafkaProducerExample;
import kafka.ObservableKafka;
import kafka.ObservateurKafka;

import java.util.ArrayList;
import java.util.List;

/**
 * Pattern builder pour le Data Transfert Object (ici équivalent d'un Entity ou d'un Bean)
 */
public class ActeurDTO implements DTO {
    public enum Role{ETUDIANT,ENSEIGNANT,ENTREPRISE}

    //Données obligatoire
    private String adresseEmail, nom, prenom;
    private Role role;

    //Données non obligatoire
    private String universite, nomEntreprise, numeroEtudiant;
    private int annee, numeroGroupe;
    private List<Voeux> voeux;
    private List<Vote> votes;
    private List<Projet> projets;
    private List<Sujet> sujets;


    private ActeurDTO(ActeurBuilder builder){
        adresseEmail = builder.adresseEmail;
        nom = builder.nom;
        prenom = builder.prenom;
        universite = builder.universite;
        nomEntreprise = builder.nomEntreprise;
        numeroEtudiant = builder.numeroEtudiant;
        annee = builder.annee;
        numeroGroupe = builder.numeroGroupe;
        voeux = builder.voeux;
        votes = builder.votes;
        projets = builder.projets;
        role = builder.role;
        sujets = builder.sujets;
    }

    //-----------------GETTER-----------//
    public String getAdresseEmail() {
        return adresseEmail;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Role getRole() {
        return role;
    }

    public String getUniversite() {
        return universite;
    }


    public String getNumeroEtudiant() {
        return numeroEtudiant;
    }

    public int getAnnee() {
        return annee;
    }

    public String getNomEntreprise() {
        return nomEntreprise;
    }

    public int getNumeroGroupe() {
        return numeroGroupe;
    }

    public List<Voeux> getVoeux() {
        return voeux;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public List<Projet> getProjets() {
        return projets;
    }

    public List<Sujet> getSujets() {
        return sujets;
    }

    //------------SETTER-----------//
    public void setUniversite(String universite){
        this.universite = universite;
    }

    public void setNumeroEtudiant(String numeroEtudiant){
        this.numeroEtudiant = numeroEtudiant;
    }

    public  void setNumeroGroupe(int numeroGroupe){
        this.numeroGroupe = numeroGroupe;
    }

    public void setAnnee(int annee){
        this.annee = annee;
    }



    //---------BUILDER---------//
    public static class ActeurBuilder{
        //données obligatoire
        private final String adresseEmail, nom, prenom;
        private Role role;
        private String universite, nomEntreprise, numeroEtudiant;
        private int annee, numeroGroupe;
        private List<Voeux> voeux;
        private List<Vote> votes;
        private List<Projet> projets;
        private List<Sujet> sujets;

        public ActeurBuilder(String adresseEmail, String nom, String prenom, Role role){
            this.adresseEmail = adresseEmail;
            this.nom = nom;
            this.prenom = prenom;
            this.role = role;
            voeux = new ArrayList<>();
            votes = new ArrayList<>();
            projets = new ArrayList<>();
            sujets = new ArrayList<>();
        }

        public ActeurBuilder universite(String universite){
            this.universite = universite;
            return this;
        }

        public ActeurBuilder nomEntreprise(String nomEntreprise){
            this.nomEntreprise = nomEntreprise;
            return this;
        }

        public ActeurBuilder numeroEtudiant(String numeroEtudiant){
            this.numeroEtudiant = numeroEtudiant;
            return this;
        }

        public ActeurBuilder annee(int annee){
            this.annee = annee;
            return this;
        }

        public ActeurBuilder numeroGroupe(int numeroGroupe){
            this.numeroGroupe = numeroGroupe;
            return this;
        }

        public ActeurBuilder voeux(List<Voeux> voeux){
            this.voeux = voeux;
            return this;
        }

        public ActeurBuilder votes(List<Vote> votes){
            this.votes = votes;
            return this;
        }

        public ActeurBuilder projets(List<Projet> projets){
            this.projets = projets;
            return this;
        }

        public ActeurBuilder sujets(List<Sujet> sujets){
            this.sujets = sujets;
            return this;
        }

        public ActeurDTO build(){
            return new ActeurDTO(this);
        }
    }



}
