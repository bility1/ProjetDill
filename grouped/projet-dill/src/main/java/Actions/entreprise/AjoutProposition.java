package Actions.entreprise;

import Global.Environnement;
import Modele.dto.ActeurDTO;
import Modele.dto.AuthentificationDTO;
import Modele.dto.Sujet;
import org.apache.struts2.ServletActionContext;

import java.util.ArrayList;

public class AjoutProposition extends Environnement {


    private String nom;
    private String ville;
    private String description;
    private String tuteurEntreprise;
    private String logo;
    private String fichier;



    public String getTuteurEntreprise() {
        return tuteurEntreprise;
    }

    public void setTuteurEntreprise(String tuteurEntreprise) {
        this.tuteurEntreprise = tuteurEntreprise;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getFichier() {
        return fichier;
    }

    public void setFichier(String fichier) {
        this.fichier = fichier;
    }


    @Override
    public String execute()  throws Exception  {

        /* formulaire ajout de nom et prenom a integerer */
        String nomEntr = "entreprise";


      AuthentificationDTO acteur = (AuthentificationDTO)getSession().get(SESSION_NAME);

       ActeurDTO acteurEntr = getActeurDAO().findByEmail(acteur.getAdresseEmail());


        Sujet sujet = new Sujet.SujetBuilder(nom,ville, Sujet.Statut.EN_ATTENTE)
                .description(description)
                .tuteurEntreprise(tuteurEntreprise)
                .logo(logo)
                .fichier(fichier)
                .build();

        if(acteurEntr == null ){

            ArrayList<Sujet> lesSujets = new ArrayList<>();

            lesSujets.add(sujet);

            getActeurDAO().set(new ActeurDTO.ActeurBuilder(acteur.getAdresseEmail(),nomEntr,"", ActeurDTO.Role.ENTREPRISE).sujets(lesSujets).build());

        }
        else {
            getActeurDAO().addSujet(acteurEntr.getAdresseEmail(),sujet);

        }

        return SUCCESS;

    }


}
