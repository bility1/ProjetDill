package Actions;

import Global.Environnement;
import Modele.dto.AuthentificationDTO;
import Global.SendEmail;
import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

import java.util.ArrayList;
import java.util.Collection;

public class Inscription extends Environnement {
    //private static final long serialVersionUID = 8676674317393590961L;
    // getter and setters...
    Collection<String> roles=new ArrayList<>();
    {
        roles.add(AuthentificationDTO.Role.ETUDIANT.toString());
        roles.add(AuthentificationDTO.Role.ENSEIGNANT.toString());
        roles.add(AuthentificationDTO.Role.ENTREPRISE.toString());
    }

    String messageErreur="";
    private String  email;
    private String password;
    private String role;
    SendEmail obj = null;
    int resp = 0;


    public void setMessageErreur(String messageErreur) {
        this.messageErreur = messageErreur;
    }

    public String getMessageErreur() {
        return messageErreur;
    }
    @EmailValidator(type = ValidatorType.SIMPLE, fieldName = "emailaddress", message = "Mail obligatoire")
    public void setEmail(String email) {
        this.email = email;
    }
    @RequiredStringValidator(type= ValidatorType.FIELD, message = "Password obligatoire.")
    public void setPassword(String password) {
        this.password = password;
    }
    @RequiredStringValidator(type= ValidatorType.FIELD, message = "Role obligatoire.")
    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    @EmailValidator(type = ValidatorType.SIMPLE,
            message = "Entrez une adresse mail valide",
            fieldName = "email")
    @Override
    public String execute() throws Exception {
        int isAdmin=0;
        AuthentificationDTO adto=new AuthentificationDTO(email,password, AuthentificationDTO.Role.valueOf(role),isAdmin);
        AuthentificationDTO adto2= getAuthentificationDAO().get(adto);
        obj = new SendEmail();
        String mail="aminatou.barry97@gmail.com";
       // String pass="";
        String subject="validation";
        String message="";
        if(adto2==null){
                //resp = obj.sendMail(mail, pass, email, subject, message);
                // if (resp == 1) {
                getAuthentificationDAO().set(adto);
                return SUCCESS;
          //  }

        }
        else{
            messageErreur+="il existe déjà un compte avec cette addresse mail";
            return INPUT;

        }
    }
}
