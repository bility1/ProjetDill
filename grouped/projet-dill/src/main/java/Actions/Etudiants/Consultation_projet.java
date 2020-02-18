package Actions.Etudiants;

import Global.Environnement;
import Modele.dto.ActeurDTO;
import Modele.dto.AuthentificationDTO;
import Modele.dto.Livrable;
import Modele.dto.Projet;

import java.util.List;

public class Consultation_projet extends Environnement {
    // ==================== Attributs ====================
    private Projet projet;
    private List<Projet> listeprojet;
    private List<Livrable> livrables;
    private List<String> tuteurs;
    private AuthentificationDTO utilisateur;
    private String adressemail;
    private boolean okprojet;

    // ==================== Getters/Setters ====================

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    public List<Projet> getListeprojet() {
        return listeprojet;
    }

    public void setListeprojet(List<Projet> listeprojet) {
        this.listeprojet = listeprojet;
    }

    public List<Livrable> getLivrables() {
        return livrables;
    }

    public List<String> getTuteurs() {
        return tuteurs;
    }

    public AuthentificationDTO getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(AuthentificationDTO utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getAdressemail() {
        return adressemail;
    }

    public void setAdressemail(String adressemail) {
        this.adressemail = adressemail;
    }

    public void setTuteurs(List<String> tuteurs) {
        this.tuteurs = tuteurs;
    }

    public boolean isOkprojet() {
        return okprojet;
    }

    public void setOkprojet(boolean okprojet) {
        this.okprojet = okprojet;
    }


    public void setLivrables(List<Livrable> livrables) {
        this.livrables = livrables;
    }

    // ================== METHODES ====================

    @Override
    public String execute() throws Exception {
        utilisateur = ((AuthentificationDTO) getSession().get(SESSION_NAME));
        adressemail = utilisateur.getAdresseEmail();
        listeprojet = getActeurDAO().getProjet(adressemail);
        if (listeprojet == null || listeprojet.size() == 0) {
            setOkprojet(false);
        } else {
            for (Projet projet : listeprojet) {
                livrables = projet.getLivrables();
                String nom = projet.getNom();
                tuteurs = getActeurDAO().getTuteur(nom);
                setOkprojet(true);

            }

        }
        return super.execute();


    }
}
