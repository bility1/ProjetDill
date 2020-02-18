package Modele.dto;


public class AuthentificationDTO implements DTO {
    //données obligatoire
    public enum Role{ETUDIANT,ENSEIGNANT,ENTREPRISE}
    private Role role;
    private String adresseEmail, password;
    private int isAdmin;

    public AuthentificationDTO(String adresseEmail, String password, Role role, int isAdmin) {
        this.adresseEmail = adresseEmail;
        this.password = password;
        this.role = role;
        this.isAdmin = isAdmin;
    }



    public AuthentificationDTO(String adresseEmail, String password){
        this.adresseEmail = adresseEmail;
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    public int getIsAdmin() {
        return isAdmin;
    }

    public Role getRole() {
        return role;
    }

    public int isAdmin() {
        return isAdmin;
    }

    public String getAdresseEmail() {
        return adresseEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
