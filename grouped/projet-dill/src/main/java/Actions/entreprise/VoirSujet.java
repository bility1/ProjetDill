package Actions.entreprise;

import Global.Environnement;
import Modele.dto.ActeurDTO;
import Modele.dto.AuthentificationDTO;
import Modele.dto.Projet;

import java.util.ArrayList;
import java.util.List;

public class VoirSujet extends Environnement {

    private String nomSujet;
    private Projet findProjet;
    private ArrayList<ActeurDTO> etudiants = new ArrayList<>();

    @Override
    public String execute() throws Exception {


        List<Projet> listSujets = getActeurDAO().findByEmail(((AuthentificationDTO) getSession().get(SESSION_NAME)).getAdresseEmail()).getProjets();
//        List<Projet> listSujets = getActeurDAO().findByEmail("jean@corporation.fr").getProjets();

        for (Projet projet : listSujets) {
            if (projet.getNom().equals(nomSujet)) {

                findProjet = projet;

            }
        }

        List<ActeurDTO> lesEtudiants = getActeurDAO().getAllEtudiants();
        for (ActeurDTO etudiant : lesEtudiants) {
            for (Projet p : etudiant.getProjets()) {
                if (p.getNom().equals(findProjet.getNom())) {
                    etudiants.add(etudiant);
                }

            }
        }

        return SUCCESS;

    }

    public String getNomSujet() {
        return nomSujet;
    }

    public void setNomSujet(String nomSujet) {
        this.nomSujet = nomSujet;
    }

    public Projet getFindProjet() {
        return findProjet;
    }

    public void setFindProjet(Projet findProjet) {
        this.findProjet = findProjet;
    }

    public List<ActeurDTO> getEtudiants() {
        return this.etudiants;
    }
}
