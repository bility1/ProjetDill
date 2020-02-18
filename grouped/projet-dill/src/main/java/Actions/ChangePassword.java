package Actions;

import Global.Environnement;
import Modele.AbstractDAOFactory;
import Modele.dao.DAO;
import Modele.dao.SQLDAOFactory;
import Modele.dto.AuthentificationDTO;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

import java.sql.SQLException;

public class ChangePassword extends Environnement {
    private String messageErreur;
    private String email;
    private String newPassword;

    public String getMessageErreur() {
        return messageErreur;
    }

    public void setMessageErreur(String messageErreur) {
        this.messageErreur = messageErreur;
    }

    public String getEmail() {
        return email;
    }

    public String getNewPassword() {
        return newPassword;
    }

    @RequiredStringValidator(type = ValidatorType.FIELD, message = "Mail obligatoire.")
    public void setEmail(String email) {
        this.email = email;
    }

    @RequiredStringValidator(type = ValidatorType.FIELD, message = "New Password obligatoire.")
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public String execute() throws Exception {
        int isAdmin = 0;
        AuthentificationDTO adto = new AuthentificationDTO(this.email, this.newPassword);
        AuthentificationDTO adto2 = getAuthentificationDAO().get(adto);
        System.out.println("je suis dans la fonction");

        if (adto2 != null && adto2.getAdresseEmail().compareTo(this.email) == 0) {
            System.out.println("je passe dans le if");
            //resp = obj.sendMail(mail, pass, email, subject, message);
            // if (resp == 1) {
            getAuthentificationDAO().update(adto);
            return SUCCESS;
        } else {
            System.out.println("je passe dans le else");
            messageErreur = " Adresse mail inconnue";
            return INPUT;

        }
    }
}
