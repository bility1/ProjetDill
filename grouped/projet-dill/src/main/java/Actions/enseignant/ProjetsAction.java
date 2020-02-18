package Actions.enseignant;

import Global.Environnement;
import Modele.AbstractDAOFactory;
import Modele.dao.ActeurDAO;
import Modele.dao.MongoDAOFactory;
import Modele.dto.ActeurDTO;
import Modele.dto.AuthentificationDTO;
import Modele.dto.Projet;
import Modele.dto.Sujet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Class lié a la page "projets"
 */
public class ProjetsAction extends Environnement {
    private Sujet sujet; //sujet a ajouter

    /**
     * Donne la liste de tous les projets qui n'ont pas déjà un tuteur universitaire
     *
     * @return
     */
    public Collection<Sujet> getSujets() {
        Collection<Sujet> sujets = new ArrayList<>();
            for (Sujet sujet : ((ActeurDAO) getActeurDAO()).getAllSujets()) {
                if (!estTutore(sujet.getNom())) {
                    sujets.add(sujet);
                }
            }
        return sujets;
    }

    /**
     * Indique si un projet est déjà tutore par un enseignant
     *
     * @return boolean
     */
    public boolean estTutore(String nomProjet) {
       return ((ActeurDAO) getActeurDAO()).estTutore(nomProjet);
    }

    /**
     * Donne les projet tutores de l'enseignant si il en as
     * @return
     */
    public List<Projet> getProjetTutore(){
        return getActeurDAO().hasProjectTutore(((AuthentificationDTO)getSession().get(SESSION_NAME)).getAdresseEmail());
    }

    public void setSujet(String nomSujet) {
        this.sujet = getActeurDAO().getSujetByNom(nomSujet);
    }

    @Override
    public String execute() {
        //si un sujet à été selectionné pour ajout et la page rechargé alors on ajoute le projet
        if (this.sujet != null) {
            getActeurDAO().tutorerProjet(this.sujet, ((AuthentificationDTO) getSession().get(SESSION_NAME)).getAdresseEmail());
            return SUCCESS;
        }
        return INPUT;
    }
}
