package Actions.enseignant;

import Global.DateFormat;
import Global.Environnement;
import Global.SendEmail;
import Modele.dto.ActeurDTO;

import java.util.*;

public class SuiviLivrable extends Environnement {

    /**
     * Donne la liste de tous les étudiants trié par groupe
     *
     * @return
     */
    public Collection<ActeurDTO> getEtudiants() {
        return getActeurDAO().getAllEtudiantsGroupeByNumeroGroupe();
    }


    /**
     * Donne la liste des dates limites trié par ordre croissant
     *
     * @return
     */
    public String[] getDateLimites() {
        return ResourceBundle.getBundle("app").getString("dateDepots").split(",");
    }

    /**
     * Donne la daté limite la plus récemment dépassé
     * @return
     */
    public String getDateLimiteActuel(){
        return getDateLimites()[getNumeroDateLimite()-1];
    }

    /**
     * Donne le numero de la date limite la plus récente (derniere avant la date d'auhourd'hui)
     * et -1 si non
     *
     * @return
     */
    private int getNumeroDateLimite() {
        int i = 1;//on ne regarde pas la première date
        String[] dateLimites = getDateLimites();
        String aujourdhui = DateFormat.toString(new Date());
        int param = 0;//si il y a 0 date
        //verifier si la première date est inférieur à la date du jour ou egal
        if (dateLimites[0].compareTo(aujourdhui) == -1 ||
                dateLimites[0].compareTo(aujourdhui) == 0) {
            param = 1;//initialisation a la position de la première date de la liste
        } else {
            return param; //si non toutes les dates limites sont après la date du jour donc pas de retard
        }
        //la première date est <= aujourdhui il faut donc regarder la suite pour trouver la date la plus proche
        //d'aujourd'hui qui as été dépassé
        while (i < dateLimites.length) {
            if (dateLimites[i].compareTo(aujourdhui) == -1 || dateLimites[i].compareTo(aujourdhui) == 0) {
                param = i + 1;//si la date est inférieur ou egal à la date d'aujourdhui on la garde
            }
            i++;
        }
        return param;
    }

    /**
     * Retourne la liste des emails des étudiants qui ont des livrables en retard
     *
     * @return
     */
    private List<String> getRetardLivrable() {
        if (getNumeroDateLimite() > 0) {
            return getActeurDAO().getEmailEtuRetard(getNumeroDateLimite());//on appelle la methode qui retourne les étudiants en retard pour cette date limite
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * Retourne le nombre d'étudiant en retard
     *
     * @return
     */
    public int getNbEtuRetard() {
        return getActeurDAO().NbEtudiantEnRetard(getNumeroDateLimite());
    }

    @Override
    public String execute() throws Exception {
        return SUCCESS;

    }

    /**
     * Envoi un Email aux étudiant en retard c'est-à-dire
     * Ceux qui n'ont pas rendu de livrables à la dernière date de livraison inférieur aux jours d'aujourd'hui
     *
     * @return
     */
    public String validerenvoieMail() {
        SendEmail obj = new SendEmail();
        String mail = "aminatou.barry97@gmail.com";
        String pass = "Kadiatou93";
        String subject = "Rappel";
        String message = "la date de depôt des est dépasé";
        for (String adresseEmail : getRetardLivrable()) {
            int resp = obj.sendMail(mail, pass, adresseEmail, subject, message);
        }
        return SUCCESS;
    }

}
