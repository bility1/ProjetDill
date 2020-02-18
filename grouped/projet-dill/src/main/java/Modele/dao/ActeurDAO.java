package Modele.dao;

import Modele.dto.*;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import Modele.dto.*;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.*;
import com.mongodb.client.*;
import Modele.dto.*;
import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.print.Doc;
import javax.print.Doc;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.*;

import static Modele.dto.AuthentificationDTO.Role.ENTREPRISE;
import static Modele.dto.AuthentificationDTO.Role.ETUDIANT;
import static Modele.dto.Voeux.EtatVoeux.REFUSER;
import static Modele.dto.Voeux.EtatVoeux.VALIDER;
import static com.mongodb.client.model.Filters.and;
import static Modele.dto.ActeurDTO.Role.ENSEIGNANT;


import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

public class ActeurDAO implements DAO<ActeurDTO> {
    private static final String COLLECTION="Acteur";
    public static int position = 0;

    MongoDatabase database;
    MongoCollection<Document> collection;

    /**
     * Initialisation de la connexion a la collection
     * @param database
     */
    ActeurDAO(MongoDatabase database) {
        this.database = database;
        collection = database.getCollection(COLLECTION);
    }

    /**
     * Permet de créer un document à partir d'un DTO
     * @param dto
     * @return Document
     */
    private Document createDocument(ActeurDTO dto){


            //Liste de voeux
            List<Document> voeux = new ArrayList<>();
            for(Voeux v:((ActeurDTO) dto).getVoeux()){
                voeux.add(new Document("etat",v.getEtat().toString())
                        .append("position",v.getPosition())
                        .append("nomProjet",v.getNomProjet()));
            }

            //Liste des votes
            List<Document> votes = new ArrayList<>();
            for(Vote v:((ActeurDTO) dto).getVotes()){
                votes.add(new Document("nomProjet",v.getNomProjet()).append("position",v.getPosition()));
            }

            //Liste des sujets
            List<Document> sujets = new ArrayList<>();
            for(Sujet s:((ActeurDTO) dto).getSujets()){
                sujets.add(new Document("nom",s.getNom())
                        .append("ville",s.getVille())
                        .append("description",s.getDescription())
                        .append("logo",s.getLogo()).append("fichier",s.getFichier())
                        .append("statut",s.getStatut().toString()));
            }

            //Liste des projets
            List<Document> projets = new ArrayList<>();
            for(Projet p:((ActeurDTO) dto).getProjets()){
                //list des livrables
                List<Document> livrables = new ArrayList<>();
                for(Livrable l:p.getLivrables()){
                    livrables.add(new Document("date",l.getDate())
                            .append("dateDepot",l.getDateDepot())
                            .append("archive",l.getArchive()));
                }
                projets.add(new Document("id", p.getId())
                        .append("nom",p.getNom())
                        .append("ville",p.getVille())
                        .append("description",p.getDescription())
                        .append("logo",p.getLogo())
                        .append("fichier",p.getFichier())
                        .append("livrables",livrables));
            }

            //retour du document avec toute les données
            return new Document("nom", dto.getNom())
                    .append("prenom",dto.getPrenom())
                    .append("role",dto.getRole().toString())
                    .append("adresseEmail",dto.getAdresseEmail())
                    .append("numeroEtudiant",dto.getNumeroEtudiant())
                    .append("universite",dto.getUniversite())
                    .append("annee",dto.getAnnee())
                    .append("numeroGroupe",dto.getNumeroGroupe())
                    .append("nomEntreprise",dto.getNomEntreprise())
                    .append("voeux",voeux)
                    .append("votes",votes)
                    .append("sujets",sujets)
                    .append("projets",projets);
    }

    private ActeurDTO createDTO(Document document){
        List<Sujet> sujets = new ArrayList<>();
        for(Document d : document.getList("sujets",Document.class)){
            sujets.add(new Sujet.SujetBuilder(d.getString("nom"),d.getString("ville"), Sujet.Statut.valueOf(d.getString("statut"))).build());
        }

        List<Voeux> voeux = new ArrayList<>();
        if (document.getList("voeux", Document.class) != null) {
            for (Document d : document.getList("voeux", Document.class)) {
                Voeux v = new Voeux(d.getString("nomProjet"), Integer.parseInt(d.getString("position")));
                v.setEtat(Voeux.EtatVoeux.valueOf(d.getString("etat")));
                voeux.add(v);
            }
        }

        List<Vote> votes = new ArrayList<>();
        if (document.getList("votes", Document.class) != null) {
            for (Document d : document.getList("votes", Document.class)) {
                votes.add(new Vote(d.getString("nomProjet"), d.getInteger("position")));
            }
        }

        List<Livrable> livrables = new ArrayList<>();
        if (document.getList("livrables", Document.class) != null) {
            for (Document d : document.getList("livrables", Document.class)) {
                livrables.add(new Livrable(d.getString("date"), d.getString("dateDepot"), d.getString("archive")));
            }
        }

        List<Projet> projets = new ArrayList<>();
        String adresseEmail;
        if (document.getList("projets", Document.class) != null) {

            for (Document d : document.getList("projets", Document.class)) {

                List<Document> lesLivrables = d.getList("livrables", Document.class);
                List<Livrable> listLivrables = new ArrayList<>();

                System.out.println("listLivrables = " + lesLivrables.getClass());

                for (Document livrable : lesLivrables) {

                    System.out.println("livrable.getClass() = " + livrable.getClass());
                    String date = livrable.getString("date");
                    String dateDepot = livrable.getString("dateDepot");
                    String archive = livrable.getString("archive");

                    listLivrables.add(new Livrable(date, dateDepot, archive));

                }

                projets.add(new Projet.ProjetBuilder(d.getString("nom"), d.getString("ville"), Integer.parseInt(d.getString("id")))
                        .logo(d.getString("logo"))
                        .fichier(d.getString("fichier"))
                        .description(d.getString("description"))
                        .livrables(listLivrables)
                        .build());
            }
        }

        adresseEmail = document.getString("adresseEmail");
        String prenom = document.getString("prenom");
        String nom = document.getString("nom");
        String role = document.getString("role").toUpperCase();
        Integer annee = document.getInteger("annee");
        String nomEntreprise = document.getString("nomEntreprise");
        String numeroEtudiant = document.getString("numeroEtudiant");
        Integer numeroGroupe = document.getInteger("numeroGroupe");
        String universite = document.getString("universite");

        ActeurDTO.ActeurBuilder returnValue = new ActeurDTO.ActeurBuilder(adresseEmail, nom, prenom, ActeurDTO.Role.valueOf(role));


        if (annee != null) {
            returnValue.annee(annee);

        }
        if (nomEntreprise != null) {
            returnValue.nomEntreprise(nomEntreprise);
        }
        if (numeroEtudiant != null) {
            returnValue.numeroEtudiant(numeroEtudiant);
        }
        if (numeroGroupe != null) {
            returnValue.numeroGroupe(numeroGroupe);
        }
        if (universite != null) {
            returnValue.universite(universite);
        }


        return returnValue.voeux(voeux)
                .votes(votes)
                .sujets(sujets)
                .projets(projets).build();
    }

    /**
     * Crée une liste de documents à partir d'une liste de DTO
     * @param dtos
     * @return
     */
    private List<Document> getListDocuments(List<ActeurDTO> dtos){
        List<Document> documents = new ArrayList<>();
        for(ActeurDTO dto: dtos){
            documents.add(createDocument(dto));
        }
        return documents;
    }


    /**
     * Récupère un seul document a partir de l'adresse email
     * @param dto
     * @return
     */
    @Override
    public ActeurDTO get(ActeurDTO dto) {
            Document query = new Document("adresseEmail", ((ActeurDTO) dto).getAdresseEmail());
            FindIterable<Document> cursor = collection.find(eq("adresseEmail",dto.getAdresseEmail()));
            return createDTO(cursor.iterator().next());
        }

        public ActeurDTO findByEmail (String email){
            Document query = new Document("adresseEmail", email);
            FindIterable<Document> cursor = collection.find(eq("adresseEmail", email));
            return createDTO(cursor.iterator().next());

        }

//    public ArrayList<Document> getGroupe(String nomEntreprise)
//    {
//
//        ArrayList<Document> list = new ArrayList<>();
//        return  list;
//    }     Document query = new Document("nom",nomEntreprise);
////        FindIterable<Document> cursor = collection.


        public void addSujet (String adresseEmail, Sujet sujet){

            BasicDBObject findUser = new BasicDBObject("adresseEmail", adresseEmail);
            BasicDBObject insert = new BasicDBObject("nom", sujet.getNom())
                    .append("ville", sujet.getVille())
                    .append("description", sujet.getDescription())
                    .append("tuteurEntreprise", sujet.getTuteurEntreprise())
                    .append("statut", sujet.getStatut().toString())
                    .append("logo", sujet.getLogo())
                    .append("fichier", sujet.getFichier());

            BasicDBObject update = new BasicDBObject("$push", new BasicDBObject("sujets", insert));


            collection.updateOne(findUser, update);


        }

    /**
     * Insert un nouveau document dans la base, dans la collection courrante
     * @param dto
     */
    @Override
    public void set(ActeurDTO dto) {
        collection.insertOne(createDocument(dto));
    }

    /**
     * Met à jour les données à partir d'un dto
     * @param dto
     */
    @Override
    public void update(ActeurDTO dto) {
       //todo implementer un update
    }

    /**
     * Supprime les données à partir d'un dto
     * @param dto
     */
    @Override
    public void delete(ActeurDTO dto) {
        collection.deleteOne(createDocument(dto));
    }

    /**
     * récupère plusieurs documents
     * @return
     */
    @Override
    public List<ActeurDTO> getAll() {
        Iterator<Document> iterator = collection.find().iterator();
        List<ActeurDTO> dtos = new ArrayList<>();
        while(iterator.hasNext()){
            dtos.add(createDTO(iterator.next()));
        }
        return dtos;
    }

    /**
     * ajoute plusieurs documents
     * @param dtos
     */
    @Override
    public void setAll(List<ActeurDTO> dtos) {
        collection.insertMany(getListDocuments(dtos));
    }

    /**
     * Mise à jour de plusieur documents
     * @param dtos
     */
    @Override
    public void updateAll(List<ActeurDTO> dtos) {
        //todo implementer un update many
    }

    @Override
    public void deleteAll(List<ActeurDTO> dtos) {
        //todo implementer cette fonction
    }

        public ActeurDTO getEtudiant (String adresseEmail){
            System.out.println("getEtudiant");
            System.out.println("adresseEmail: " + adresseEmail);
            //par Aminatou pour la branche suivilivrable

            BasicDBObject searchQuery = new BasicDBObject();

// les projet non livré n'ont pas de date de depot
            List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
            obj.add(new BasicDBObject("adresseEmail", adresseEmail));
            obj.add(new BasicDBObject("role", ActeurDTO.Role.ETUDIANT.toString()));
            searchQuery.put("$and", obj);
            MongoCursor<Document> iterator = collection.find(searchQuery).iterator();
            System.out.println("hasNext ? " + iterator.hasNext());
            Document doc = iterator.next();
            List<Voeux> voeux = new ArrayList<>();
            for (Document d : doc.getList("voeux", Document.class)) {
                Voeux v = new Voeux(d.getString("nomProjet"), Integer.parseInt(d.getString("position")));
                v.setEtat(Voeux.EtatVoeux.valueOf(d.getString("etat")));
                voeux.add(v);
            }
            return new ActeurDTO.ActeurBuilder(doc.getString("adresseEmail"), doc.getString("nom"), doc.getString("prenom"), ActeurDTO.Role.valueOf(doc.getString("role")))
                    .voeux(voeux)
                    .build();
            //DBObject query = new BasicDBObject("projets.livrables.dateDepot", new BasicDBObject(
            //       "$exists", true));

    }

        /**
         * récupère les étudiants
         *
         * @return
         */
        public List<ActeurDTO> getAllEtudiantsGroupeByNumeroGroupe () {
            List<ActeurDTO> etudiants = new ArrayList<>();

            BasicDBObject where = new BasicDBObject();
            where.put("role", ActeurDTO.Role.ETUDIANT.toString());


            MongoCursor<Document> cursor = collection.find(where).cursor();

            while (cursor.hasNext()) {//pour chaque étudiant trié par groupe
                Document doc = cursor.next();

                //creation de la liste de ces projets
                List<Projet> projets = new ArrayList<>();
                for (Document p : doc.getList("projets", Document.class)) {
                    projets.add(new Projet.ProjetBuilder(p.getString("nom"), p.getString("ville"), 1)
                            .build());
                }

                etudiants.add(new ActeurDTO.ActeurBuilder(doc.getString("adresseEmail"), doc.getString("nom"), doc.getString("prenom"), ActeurDTO.Role.valueOf(doc.getString("role")))
                        .projets(projets)
                        .build());
            }

            return etudiants;
        }

        /**
         * Retourne les adressesEmail des étudiants pas en retard pour une date limite donnée
         *
         * @param numeroDate
         * @return
         */
        public List<String> getEtudiantsPasEnRetard ( int numeroDate){
            List<String> etudiantsRetard = new ArrayList<>();

            try {
                //Les étudiants ne sont affecté cas 1 seul projet à la foi
                //Ils n'ont pas plus de livrables qu'il n'y a de date limite pour les dépots
                MongoCursor<Document> documents = collection.aggregate(Arrays.asList(
                        Aggregates.unwind("$projets"),
                        Aggregates.match(Filters.and(
                                Filters.eq("role", ActeurDTO.Role.ETUDIANT.toString()),
                                Filters.size("projets.livrables", numeroDate)
                        ))

                )).iterator();

                while (documents.hasNext()) {
                    etudiantsRetard.add(documents.next().getString("adresseEmail"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return etudiantsRetard;
        }

        /**
         * Retourne les adresses emails de tous les étudiants
         *
         * @return
         */
        public List<String> getAllEmailEtudiant () {
            List<String> emails = new ArrayList<>();

            for (ActeurDTO etu : getAllEtudiants()) {
                emails.add(etu.getAdresseEmail());
            }
            return emails;
        }

        /**
         * Retourne les élèves en retard sur une date limite en faisant la diff entre
         * les emails de tous les étudiants et les emails de ceux pas en retard
         *
         * @param numeroDate
         * @return
         */
        public List<String> getEmailEtuRetard ( int numeroDate){
            Set<String> all = new HashSet<>(getAllEmailEtudiant());
            Set<String> notAll = new HashSet<>(getEtudiantsPasEnRetard(numeroDate));

            all.removeAll(notAll);
            return new ArrayList<>(all);
        }

        /**
         * Donne le nombre d'étudiant en retard
         *
         * @param numeroDate
         * @return
         */
        public int NbEtudiantPasEnRetard ( int numeroDate){
            return getEtudiantsPasEnRetard(numeroDate).size();
        }

        /**
         * Donne le nombre d'étudiant en retard
         *
         * @return
         */
        public int NbEtudiantEnRetard ( int numeroDate){
            return getNbEtudiants() - NbEtudiantPasEnRetard(numeroDate);
        }

        public int getNbEtudiants () {
            return getAllEtudiantsGroupeByNumeroGroupe().size();
        }

        public List<ActeurDTO> getEtudiantsGroupe () {
      /*  BasicDBObject andQuery = new BasicDBObject();
        List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
        obj.add(new BasicDBObject("number", 2));
        obj.add(new BasicDBObject("name", "mkyong-2"));
        andQuery.put("$and", obj);

        System.out.println(andQuery.toString());

        FindIterable<Document> cursor = collection.find(andQuery);
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }*/

            return null;
        }

        /**
         * Permet à un enseignant de tutorer un projet en donnant son adresseEmail et le sujet à tutorer
         *
         * @param sujet
         * @param adresseEmail
         */
        public void tutorerProjet (Sujet sujet, String adresseEmail){
            BasicDBObject docToInsert = new BasicDBObject("nom", sujet.getNom())
                    .append("ville", sujet.getVille())
                    .append("description", sujet.getDescription())
                    .append("logo", sujet.getLogo())
                    .append("fichier", sujet.getFichier())
                    .append("livrables", new ArrayList<>());

            BasicDBObject searchQuery = new BasicDBObject("adresseEmail", adresseEmail);
            BasicDBObject updateCommand = new BasicDBObject("$push", new BasicDBObject("projets", docToInsert));

            collection.updateOne(searchQuery, updateCommand);
        }

        /**
         * Si l'enseignant à un projet alors on retourne le projet et null si non
         *
         * @param adresseEmail
         * @return String
         */
        public List<Projet> hasProjectTutore (String adresseEmail){
            List<Projet> projets = new ArrayList<>();
            try {
                MongoCursor<Document> documents = collection.aggregate(
                        Arrays.asList(
                                Aggregates.unwind("$projets"),
                                Aggregates.match(
                                        Filters.and(
                                                Filters.eq("adresseEmail", adresseEmail),
                                                Filters.eq("role", ActeurDTO.Role.ENSEIGNANT.toString())
                                        )

                                )
                        )
                ).iterator();
                while (documents.hasNext()) {
                    Document doc = documents.next();
                    projets.add(new Projet.ProjetBuilder(((Document) doc.get("projets")).getString("nom"), ((Document) doc.get("projets")).getString("ville"), 1).description(((Document) doc.get("projets")).getString("description")).build());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return projets;
        }

        /**
         * Retourne true si le projet est tutoré pas un enseignant et false si non
         *
         * @param nomProjet
         * @return
         */
        public boolean estTutore (String nomProjet){
            boolean estTutore = false; //par défaut on retourne que le projet n'est pas tutore
            try {
                MongoCursor<Document> documents = collection.aggregate(
                        Arrays.asList(
                                Aggregates.unwind("$projets"),
                                Aggregates.match(Filters.and(
                                        Filters.eq("role", AuthentificationDTO.Role.ENSEIGNANT.toString()),
                                        Filters.eq("projets.nom", nomProjet)
                                        )
                                )
                        )
                ).iterator();

                estTutore = documents.hasNext(); //si il y a un suivant alors le projet est tutore

            } catch (Exception e) {
                e.printStackTrace();
            }
            return estTutore;
        }

        /**
         * Retourne tous les sujets VALIDER
         *
         * @return
         */
        public List<Sujet> getAllSujets () {
            List<Sujet> sujets = new ArrayList<>();
            try {
                MongoCursor<Document> documents = collection.aggregate(
                        Arrays.asList(
                                Aggregates.unwind("$sujets"),
                                Aggregates.match(Filters.and(Arrays.asList(
                                        eq("role", AuthentificationDTO.Role.ENTREPRISE.toString()),
                                        eq("sujets.statut", Sujet.Statut.VALIDER.toString())
                                ))),
                                Aggregates.group("$sujets.nom",
                                        Accumulators.first("ville", "$sujets.ville"),
                                        Accumulators.first("statut", "$sujets.statut"),
                                        Accumulators.first("description", "$sujets.description"))
                        )
                ).iterator();

                while (documents.hasNext()) {
                    Document doc = documents.next();
                    sujets.add(new Sujet.SujetBuilder(doc.getString("_id"), doc.getString("ville"), Sujet.Statut.valueOf(doc.getString("statut")))
                            .description(doc.getString("description"))
                            .build());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return sujets;
        }

        /**
         * Donne un sujet en cherchant son nom
         *
         * @param nomSujet
         * @return
         */
        public Sujet getSujetByNom (String nomSujet){

            try {
                MongoCursor<Document> cursor = collection.aggregate(
                        Arrays.asList(
                                Aggregates.unwind("$sujets"),
                                Aggregates.match(
                                        Filters.and(
                                                Filters.eq("role", AuthentificationDTO.Role.ENTREPRISE.toString()),
                                                Filters.eq("sujets.nom", nomSujet)
                                        )

                                )
                        )
                ).iterator();

                while (cursor.hasNext()) {
                    Document doc = (Document) cursor.next().get("sujets");
                    return new Sujet.SujetBuilder(doc.getString("nom"), doc.getString("ville"), Sujet.Statut.valueOf(doc.getString("statut")))
                            .description(doc.getString("description"))
                            .fichier(doc.getString("fichier"))
                            .logo(doc.getString("logo"))
                            .build();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }

        /**
         * Donne la liste des étudiants en se basant uniquement sur leurs role
         * Et les retourne avec adresseEmail, nom, prenom, role et la liste de projets sans leurs livrables
         *
         * @return
         */
        public List<ActeurDTO> getAllEtudiants () {
            List<ActeurDTO> etudiants = new ArrayList<>();

            BasicDBObject where = new BasicDBObject();
            where.put("role", ActeurDTO.Role.ETUDIANT.toString());
            System.out.println("getAllEtudiant");
            try {
                MongoCursor<Document> cursor = collection.find(where).cursor();

                while (cursor.hasNext()) {//pour chaque étudiant trié par groupe

                    System.out.println("cursor.hasNext");
                    Document doc = cursor.next();

                    //creation de la liste de ces projets
                    List<Projet> projets = new ArrayList<>();
                    for (Document p : doc.getList("projets", Document.class)) {
                        projets.add(new Projet.ProjetBuilder(p.getString("nom"), p.getString("ville"), 1)
                                .build());
                    }

                    etudiants.add(new ActeurDTO.ActeurBuilder(doc.getString("adresseEmail"), doc.getString("nom"), doc.getString("prenom"), ActeurDTO.Role.valueOf(doc.getString("role")))
                            .projets(projets)
                            .build());
                }
            }catch (Exception e){
                e.printStackTrace();
            }

            return etudiants;
        }

        /**
         * Donne la liste de tous les sujets
         *
         * @return
         */
        public List<Sujet> ListeDesSujets () {
            List<Sujet> sujets = new ArrayList<>();
            FindIterable<Document> docs = collection.find(eq("role", ENTREPRISE.toString()));

            for (Document doc : docs) {
                for (Document d : doc.getList("sujets", Document.class)) {

                    sujets.add(new Sujet.SujetBuilder(d.getString("nom"), d.getString("ville"), Sujet.Statut.valueOf(d.getString("statut"))).build());

                }
            }

            return sujets;
        }


        /**
         * Donne le sujets en fonction de son nom (unique)
         *
         * @param nomSujet
         * @return
         */
        public Sujet ConsultationSujets (String nomSujet){
            Sujet sujet = null;
            AggregateIterable<Document> docs = collection.aggregate(
                    Arrays.asList(
                            Aggregates.unwind("$sujets"),
                            Aggregates.match(Filters.eq("sujets.nom", nomSujet))
                    )
            );

            for (Document document : docs) {
                Document d = (Document) document.get("sujets");
                sujet = new Sujet.SujetBuilder(d.getString("nom"), d.getString("ville"), Sujet.Statut.valueOf(d.getString("statut")))
                        .description(d.getString("description"))
                        .fichier(d.getString("fichier"))
                        .logo(d.getString("logo"))
                        .build();
            }
            return sujet;

        }


        /**
         * Valide un sujet
         *
         * @param nomSujet
         */
        public void validerSujets (String nomSujet){

            Projet sujet = null;

            try {
                MongoCursor<Document> cursor = collection.aggregate(
                        Arrays.asList(
                                Aggregates.unwind("$sujets"),
                                Aggregates.match(
                                        and(
                                                eq("sujets.nom", nomSujet),
                                                eq("role", ENTREPRISE.toString())
                                        )
                                )

                        )
                ).iterator();

                BasicDBObject docToInsert = null;
                while (cursor.hasNext()) {
                    Document doc = (Document) cursor.next().get("sujets");
                    docToInsert = new BasicDBObject("nom", doc.getString("nom"))
                            .append("ville", doc.getString("ville"))
                            .append("description", doc.getString("description"))
                            .append("logo", doc.getString("logo"))
                            .append("fichier", doc.getString("fichier"))
                            .append("livrables", new ArrayList<>());
                }
                collection.updateOne(eq("sujets.nom", nomSujet), new Document("$set", new Document("sujets.$.statut", Sujet.Statut.VALIDER.toString())));


                BasicDBObject searchQuery = new BasicDBObject("sujets.nom", nomSujet);
                BasicDBObject updateCommand = new BasicDBObject("$push", new BasicDBObject("projets", docToInsert));

                collection.updateOne(searchQuery, updateCommand);
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

        /**
         * Refuse un sujet
         *
         * @param nomSujet
         */
        public void refuserSujets (String nomSujet){
            collection.updateOne(eq("sujets.nom", nomSujet), new Document("$set", new Document("sujets.$.statut", Sujet.Statut.REFUSER.toString())));
        }

        public List<ActeurDTO> affichageDesVoeux () {
            List<ActeurDTO> listeActeurDTO = new ArrayList<>();
            try {
                FindIterable<Document> docs = collection.find(eq("role", ETUDIANT.toString()));

                for (Document doc : docs) {

                    List<Voeux> voeux = new ArrayList<>();
                    for (Document d : doc.getList("voeux", Document.class)) {
                        Voeux v = new Voeux(d.getString("nomProjet"), Integer.parseInt(d.getString("position")));
                        v.setEtat(Voeux.EtatVoeux.valueOf(d.getString("etat")));
                        voeux.add(v);
                    }

                    listeActeurDTO.add(new ActeurDTO.ActeurBuilder(doc.getString("adresseEmail"), doc.getString("nom"), doc.getString("prenom"), ActeurDTO.Role.valueOf(doc.getString("role")))
                            .numeroEtudiant(doc.getString("numeroEtudiant"))
                            .voeux(voeux)
                            .build());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return listeActeurDTO;
        }


        public void validerVoeux (String numEtu, String nomVoeu) {
            List<String> voeurefuser = new ArrayList<>();
            String nom = null;
            String ville = null;
            String description = null;
            String logo = null;
            String fichier = null;
            String date = null;
            String dateDepot = null;
            String archive = null;

            AggregateIterable<Document> docs = collection.aggregate(Arrays.asList(Aggregates.unwind("$voeux"), Aggregates
                    .match(Filters.eq("numeroEtudiant", numEtu))));

            for (Document etudiants : docs) {

                Document voeu = (Document) etudiants.get("voeux");

                if (!(voeu.get("nomProjet").equals(nomVoeu))) {
                    voeurefuser.add(String.valueOf(voeu.get("nomProjet")));


                }
            }

            collection.updateOne(Filters.and(eq("numeroEtudiant", numEtu), (eq("voeux.nomProjet", nomVoeu))), new Document("$set", new Document("voeux.$.etat", VALIDER.toString())));

            for (String voeuxRefus : voeurefuser) {
                collection.updateOne(Filters.and(eq("numeroEtudiant", numEtu), (eq("voeux.nomProjet", voeuxRefus))), new Document("$set", new Document("voeux.$.etat", REFUSER.toString())));

            }



            AggregateIterable<Document> d = collection.aggregate(Arrays.asList(Aggregates.unwind("$sujets"), Aggregates
                    .match(Filters.and(eq("sujets.nom", nomVoeu), eq("role", ENTREPRISE.toString())))));

            ArrayList<Livrable> livr = new ArrayList<>();
            Integer.parseInt("0001");
            for (Document docu : d) {

                Document projets = (Document) docu.get("sujets");


                nom = projets.getString("nom");
                ville = projets.getString("ville");
                description = projets.getString("description");
                logo = projets.getString("logo");
                fichier = projets.getString("fichier");

            }

//Projet p=(new Projet.ProjetBuilder(nom,ville, id).logo(logo).fichier(fichier).description(description).livrables(livr.build());
            BasicDBObject sujet = new BasicDBObject();
            sujet.put("nom", nom);
            sujet.put("id", 1);
            sujet.put("ville", ville);
            sujet.put("description", description);
            sujet.put("logo", logo);
            sujet.put("fichier", fichier);

            List<BasicDBObject> list = new ArrayList<>();
            list.add(sujet);


            collection.updateOne(eq("numeroEtudiant", numEtu), Updates.pushEach("projets", list));
        }

    /**
     * Donne la liste des voeux d'un étudiant
     * @param adressemail
     * @return
     */
    public List<Voeux> listeDesVoeux(String adressemail) {
        List<Voeux> voeux = new ArrayList<>();
        FindIterable<Document> docs = collection.find(eq("adresseEmail", adressemail));

        for( Document dabo : docs) {
            for (Document d : dabo.getList("voeux", Document.class)) {
                Voeux v = new Voeux(d.getString("nomProjet"), Integer.parseInt(d.getString("position")));

                //v.setEtat(Voeux.EtatVoeux.valueOf(d.getString("etat")));
                voeux.add(v);

            }
        }
        System.out.println("LISTE DES VOEUX" +voeux.toString());
        return voeux;

    }

    /**
     * Donne le nom de l'étudiant
     * @param adressemail
     * @return
     */
    public String getName(String adressemail){
        String nom="";
        FindIterable<Document> docs = collection.find(eq("adresseEmail", adressemail));
        for( Document dabo : docs) {
            nom=dabo.getString("nom");
        }
        return nom;
    }

    /**
     * Donne le prenom de l'étudiant
     * @param adressemail
     * @return
     */
    public String getPrenom(String adressemail){
        String nom="";
        FindIterable<Document> docs = collection.find(eq("adresseEmail", adressemail));
        for( Document dabo : docs) {
            nom=dabo.getString("prenom");
        }
        return nom;
    }

    /**
     * Donne le numero etudiant
     * @param adressemail
     * @return
     */
    public int getNumEtu(String adressemail){
        int num=0;
        FindIterable<Document> docs = collection.find(eq("adresseEmail", adressemail));
        for( Document dabo : docs) {
            num=Integer.parseInt(dabo.getString("numeroEtudiant"));
        }
        return num;
    }


    /**
     * Donne la liste de tout les sujets validés
     * @return
     */
    public List<Sujet> ListeToutLesSujets() {
        List<Sujet> sujets = new ArrayList<>();
        try {
            AggregateIterable<Document> documents
                    = collection.aggregate(
                    Arrays.asList(
                            Aggregates.match(Filters.eq("role", "ENTREPRISE")),
                            Aggregates.unwind("$sujets"),
                            Aggregates.match(eq("sujets.statut", "VALIDER")
                            )

                    )) ;

            for (Document doc : documents){
                Document d = (Document) doc.get("sujets") ;
                sujets.add(new Sujet.SujetBuilder(d.getString("nomSujet"), d.getString("ville"), Sujet.Statut.VALIDER)
                        .description(d.getString("description")).build());

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return sujets;
    }

    /**
     * Donne le sujet selon son nom
     * @param nom
     * @return
     */
    public Sujet getSujet(String nom){
        Sujet sujet = null;
        AggregateIterable<Document> documents
                = collection.aggregate(
                Arrays.asList(
                        Aggregates.match(Filters.eq("role", "ENTREPRISE")),
                        Aggregates.unwind("$sujets"),
                        Aggregates.match(Filters.eq("sujets.nomSujet", nom))

                )

        ) ;
        for (Document doc : documents){
            Document d = (Document) doc.get("sujets") ;
            sujet = new Sujet.SujetBuilder(
                    d.getString("nomSujet"), d.getString("ville"), Sujet.Statut.VALIDER)
                    .description(d.getString("description"))
                    .build();
        }
        return sujet;

    }

    /**
     * Ajoute un voeu
     * @param nom
     * @param adresseEmailEtudiant
     * @return
     */
    public boolean ajouterUnVoeux (String nom, String adresseEmailEtudiant){
        position = position + 1 ;
        boolean existe = this.existVoeux(nom, adresseEmailEtudiant);
        if (existe){
            return false;

        }else{
            if (position > 3 ){
                position = 0 ;
            }
            BasicDBObject voeu1 = new BasicDBObject();
            voeu1.put("nomProjet", nom);
            voeu1.put("position", String.valueOf(position));
            voeu1.put("etat", "EN_ATTENTE");
            List<BasicDBObject> list = new ArrayList<>();
            list.add(voeu1);
            UpdateResult updateOne = collection.updateOne(Filters.eq("adresseEmail", adresseEmailEtudiant), Updates.pushEach("voeux", list));


            return true;

        }

    }

    /**
     * Retourne vrai si le voeu existe déjà
     * @param nomSujet
     * @param adresseEmail
     * @return
     */
    public boolean existVoeux(String nomSujet, String adresseEmail){
        int nomBreDevoeuxSimilaire = 0;
        Iterator<Document> iterator = collection.find(Filters.and(Filters.eq("adresseEmail", adresseEmail),
                Filters.eq("voeux.nomProjet", nomSujet))).iterator();
        while ( iterator.hasNext() ){
            nomBreDevoeuxSimilaire++;
            iterator.next();
        }
        if (nomBreDevoeuxSimilaire != 0){
            return true ;

        }else{
            return false;
        }


    }

    /**
     * Retourne le projet de  l'étudiant
     * @param adressemail
     * @return
     */
    public List<Projet> getProjet(String adressemail){
        List<Livrable> livrables = new ArrayList<>();
        List<Projet> projets = new ArrayList<>();
        FindIterable<Document> docs = collection.find(eq("adresseEmail", adressemail));

        for( Document dabo : docs) {
            for(Document d: dabo.getList("projets",Document.class)){

                for(Document da: d.getList("livrables",Document.class)) {
                    livrables.add(new Livrable(d.getString("date"), d.getString("dateDepot"), d.getString("archive")));
                }
                projets.add(new Projet.ProjetBuilder(d.getString("nom"),d.getString("ville"), Integer.parseInt(d.getString("id")))
                        .logo(d.getString("logo"))
                        .fichier(d.getString("fichier"))
                        .description(d.getString("description"))
                        .livrables(livrables)
                        .build());

            }
        }
        return projets;
    }

    /**
     * Retourne la liste les tuteurs d'un projet
     * @param nomProjet
     * @return
     */

    public List<String> getTuteur(String nomProjet){
        List<String> listedestuteurs = new ArrayList<>();
        FindIterable<Document> docs = collection.find(Filters.and(Filters.eq("role", AuthentificationDTO.Role.ENSEIGNANT.toString()),
                Filters.eq("projets.nom", nomProjet)
        ));
        for (Document d : docs){
            String tuteur=d.getString("nom");
            listedestuteurs.add(tuteur) ;
        }
        return listedestuteurs;

    }

}

