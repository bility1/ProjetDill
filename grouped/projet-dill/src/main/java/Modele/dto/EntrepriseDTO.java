package Modele.dto;

import Modele.dao.ActeurDAO;

import java.util.ArrayList;

public class EntrepriseDTO implements Entreprise{

    private AuthentificationDTO user ;

    public EntrepriseDTO(AuthentificationDTO user) {
        this.user = user;
    }
    //
//
//
//    "Nom": "MOAL",
//            "Prenom": "Frédéric",
//            "Mail": "frederic.moal@sopra.fr",
//            "NomEntreprise": "Sopra Steria",
//            "Role": "Tuteur Entreprise",
//            "Votes":[
//    {"NomProjet":'projet dill2',"Position":'4'},
//            ],
//            "Sujets" : [
//    {"sujet1":
//        { "nomSujet" : "sujet dill1","descriptionSujet" : "description sujet dill 1","logoEntreprise" : "logo.jpeg",
//                "fichierDetailSujet" : "fichier.txt",dateDepotProjet:'30/12/2020',statut:"En attente"}
//    },
//    {"sujet2":
//        { "nomSujet" : "sujet dill2","descriptionSujet" : "description sujet dill 1","logoEntreprise" : "logo.jpeg",
//                "fichierDetailSujet" : "fichier.txt","dateDepotSujet":'30/12/2020',statut:"Validé"}
//    },

//    new ActeurDTO.ActeurBuilder("insert2@test.com","insert2","insert2", ActeurDTO.Role.ENTREPRISE,false)
//            .nomEntreprise("entreprise1")
//                .sujets(sujets)
//                .build();


    ArrayList<Sujet> lesSujets = new ArrayList<>();

    @Override
    public void proposerSujet(String nom, String ville, Sujet.Statut statut) {

        Sujet.SujetBuilder sujetB = new Sujet.SujetBuilder(nom,ville,statut);

        lesSujets.add(sujetB.build());


    }


//    public

//    public ActeurDTO persist(ActeurDTO acteur){
//
//    }



    @Override
    public ArrayList<Sujet> voirPropositions() {
        return null;
    }

    @Override
    public Sujet voirStatut(int propositionId) {
        return null;
    }
}
