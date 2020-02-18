package Actions.Etudiants;

import Global.Environnement;
import Modele.dto.ActeurDTO;
import Modele.dto.AuthentificationDTO;
import Modele.dto.Sujet;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListeDesSujetsValider extends Environnement {

    // ==================== Attributs ====================
    List<Sujet> listesujets;
    List<String> nomdessujets ;
    private List<String> listenomSujet1;
    private List<String> listenomSujet2;
    private List<String> listenomSujet3;
    private int nombreDeVoeux ;
    private String nomSujet;
    private int nbSujetSimilaire;
    private AuthentificationDTO utilisateur ;
    private String adressemail ;
    private String nom;
    private String prenom;
    private int numeroetudiant;
    private boolean realisationfaite;
    private boolean premiervoeuexiste;
    private boolean deuxiemevoeuexiste;
    private boolean troisiemevoeuexiste;
    private boolean inscriptionfaite;



// ==================== Getters/Setters ====================

    public List<String> getNomdessujets() {
        nomdessujets=new ArrayList<>();
        for (Sujet s : listesujets){
            String nom = s.getNom();
            nomdessujets.add(nom) ;
        }
        return nomdessujets;
    }

    public List<String> getListenomSujet1() {
        return listenomSujet1;
    }

    public void setListenomSujet1(List<String> listenomSujet1) {
        this.listenomSujet1 = listenomSujet1;
    }

    public List<String> getListenomSujet2() {
        return listenomSujet2;
    }

    public void setListenomSujet2(List<String> listenomSujet2) {
        this.listenomSujet2 = listenomSujet2;
    }

    public List<String> getListenomSujet3() {
        return listenomSujet3;
    }

    public void setListenomSujet3(List<String> listenomSujet3) {
        this.listenomSujet3 = listenomSujet3;
    }

    public int getNombreDeVoeux() {
        return nombreDeVoeux;
    }

    public void setNombreDeVoeux(int nombreDeVoeux) {
        this.nombreDeVoeux = nombreDeVoeux;
    }

    public void setNomdessujets(List<String> nomdessujets) {
        this.nomdessujets = nomdessujets;
    }

    public List<Sujet> getListesujets() {
        listesujets = this.getActeurDAO().ListeToutLesSujets();
        return listesujets; }

    public void setListesujets(List<Sujet> listesujets) { this.listesujets = listesujets; }
    public String getNomSujet() { return nomSujet; }

    public void setNomSujet(String nomSujet) { this.nomSujet = nomSujet; }

    public AuthentificationDTO getUtilisateur() {
        return utilisateur;
    }
    public void setSujet(String sujet) {
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

    public String getNom() {
        utilisateur=((AuthentificationDTO) getSession().get(SESSION_NAME));
        adressemail= utilisateur.getAdresseEmail();
        nom=getActeurDAO().getName(adressemail);
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        utilisateur=((AuthentificationDTO) getSession().get(SESSION_NAME));
        adressemail= utilisateur.getAdresseEmail();
        prenom=getActeurDAO().getPrenom(adressemail);
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getNumeroetudiant() {
        utilisateur=((AuthentificationDTO) getSession().get(SESSION_NAME));
        adressemail= utilisateur.getAdresseEmail();
        numeroetudiant=getActeurDAO().getNumEtu(adressemail);
        return numeroetudiant;
    }

    public void setNumeroetudiant(int numeroetudiant) {
        this.numeroetudiant = numeroetudiant;
    }

    public boolean isRealisationfaite() { return realisationfaite; }

    public void setRealisationfaite(boolean realisationfaite) { this.realisationfaite = realisationfaite; }

    public boolean isPremiervoeuexiste() { return premiervoeuexiste; }

    public void setPremiervoeuexiste(boolean premiervoeuexiste) { this.premiervoeuexiste = premiervoeuexiste; }

    public boolean isDeuxiemevoeuexiste() { return deuxiemevoeuexiste; }

    public void setDeuxiemevoeuexiste(boolean deuxiemevoeuexiste) {
        this.deuxiemevoeuexiste = deuxiemevoeuexiste;
    }

    public boolean isTroisiemevoeuexiste() {
        return troisiemevoeuexiste;
    }

    public void setTroisiemevoeuexiste(boolean troisiemevoeuexiste) {
        this.troisiemevoeuexiste = troisiemevoeuexiste;
    }

    public boolean isInscriptionfaite() { return inscriptionfaite; }

    public void setInscriptionfaite(boolean inscriptionfaite) { this.inscriptionfaite = inscriptionfaite; }
// ================== METHODES ====================

    public String doInscription(){
        Boolean exist1 = true;
        Boolean exist2 = true;
        Boolean exist3 = true;
        setPremiervoeuexiste(exist1);
        setDeuxiemevoeuexiste(exist2);
        setTroisiemevoeuexiste(exist3);
        utilisateur=((AuthentificationDTO) getSession().get(SESSION_NAME));
        adressemail= utilisateur.getAdresseEmail();
        for (String nomSujet : listenomSujet1) {
            exist1 = this.getActeurDAO().ajouterUnVoeux(nomSujet, adressemail);

            setPremiervoeuexiste(exist1);
         /*   if (exist1 == false ) {
                this.addFieldError("voeux_existant", "Le premier voeu n'est pas pris en compte car vous y êtes déjà inscrit.");
            }else{
                continue;
            }*/

        }
        for (String nomSujet : listenomSujet2){
            exist2 = this.getActeurDAO().ajouterUnVoeux(nomSujet, adressemail);
            setDeuxiemevoeuexiste(exist2);
         /*   if (exist2 == false) {
                this.addFieldError("voeux_existant", "Le deuxième voeu n'est pas pris en compte car vous y êtes déjà inscrit.");
            }else{
                continue;
            }
*/
        }
        for (String nomSujet : listenomSujet3){
            exist3 = this.getActeurDAO().ajouterUnVoeux(nomSujet, adressemail);
            setTroisiemevoeuexiste(exist3);
         /*   if (exist3 == false ) {
                this.addFieldError("voeux_existant", "Le troisième voeu n'est pas pris en compte car vous y êtes déjà inscrit.");
            }else{
                continue;
            }*/

        }
        if (exist1 || exist2 || exist3){
            setInscriptionfaite(true);
        }else{
            setInscriptionfaite(false);
        }
        return super.SUCCESS;
    }



}
