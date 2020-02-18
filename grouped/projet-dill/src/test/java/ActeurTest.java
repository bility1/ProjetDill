import Modele.AbstractDAOFactory;
import Modele.dao.DAO;
import Modele.dao.MongoDAOFactory;
import Modele.dto.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActeurTest {
    /*
    DAO dao;
    DTO acteur1, acteur2, acteur3, acteur4, acteur5;*/


  /*  @Before
    public void testConnection() throws SQLException{
      /*  dao = ((MongoDAOFactory) AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_TYPE.MONGO)).getActeurDAO();

        List<Livrable> livrables = new ArrayList<>();
        livrables.add(new Livrable("10/01/2020","20/01/2020","chemin/archive"));

        List<Projet> projets = new ArrayList<>();
        projets.add(new Projet.ProjetBuilder("projet1","orleans", 1).description("description1").fichier("fichier1").logo("logo1").livrables(livrables).build());

        acteur1 = new ActeurDTO.ActeurBuilder("insert1@test.com","insert1","insert1", ActeurDTO.Role.ENSEIGNANT,true).projets(projets).build();

        List<Sujet> sujets = new ArrayList<>();
        sujets.add(new Sujet.SujetBuilder("sujet1","orleans", Sujet.Statut.EN_ATTENTE).build());
        sujets.add(new Sujet.SujetBuilder("sujet2","tour", Sujet.Statut.VALIDER).description("description2").logo("logo2").fichier("chemin/fichier2").build());

        acteur2 = new ActeurDTO.ActeurBuilder("insert2@test.com","insert2","insert2", ActeurDTO.Role.ENTREPRISE,false)
                .nomEntreprise("entreprise1")
                .sujets(sujets)
                .build();


        List<Voeux> voeux = new ArrayList<>();
        voeux.add(new Voeux("projet1",1));

        acteur3 = new ActeurDTO.ActeurBuilder("insert3@test.com","insert3","insert3", ActeurDTO.Role.ETUDIANT,false)
                .annee(2020)
                .numeroEtudiant(2152843)
                .numeroGroupe(1)
                .universite("orleans")
                .voeux(voeux)
                .build();

        acteur4 = new ActeurDTO.ActeurBuilder("modif1@test.com","modif1","modif2", ActeurDTO.Role.ENSEIGNANT,false).build();*/

 /*   }

    @Test
    public void testInsert(){


        /*dao.set(acteur1);
        dao.set(acteur2);
        dao.set(acteur3);*/

      /*  //suppression
        dao.delete(acteur1);
        dao.delete(acteur2);
        dao.delete(acteur3);
        //dao.delete(acteur1);
        //dao.delete(acteur2);
        //dao.delete(acteur3);
    }

    @Test
    public void testUpdate(){
      //todo faire test de l'update
    }

  */

   /* @Test
    public void testGet(){
        /*dao.set(acteur2);

        ActeurDTO result = (ActeurDTO) dao.get(acteur2);

        Assert.assertEquals(result.getAdresseEmail(),"insert2@test.com");


    }*/


}
