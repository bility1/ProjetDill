package Actions;

import Global.Environnement;
import Modele.AbstractDAOFactory;
import Modele.dao.DAO;
import Modele.dao.SQLDAOFactory;
import Modele.dto.AuthentificationDTO;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.ValidationParameter;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import org.apache.struts2.interceptor.SessionAware;

import java.sql.SQLException;
import java.util.Map;

public class Authentification extends Environnement {
    String messageErreur="";
    private String  email, password;

    public String getMessageErreur() {
        return messageErreur;
    }

    @RequiredStringValidator(type= ValidatorType.FIELD, message = "Mail obligatoire.")
    public void setEmail(String email) {
        this.email = email;
    }
    @RequiredStringValidator(type= ValidatorType.FIELD, message = "Password obligatoire.")
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String execute() throws Exception {
        AuthentificationDTO adto=new AuthentificationDTO(email,password);
        AuthentificationDTO user = getAuthentificationDAO().get(adto);
        if(user!=null && user.getPassword().compareTo(password)==0){
            if(getSession().containsKey(SESSION_NAME)){
                getSession().replace(SESSION_NAME,user);
            }else {
                getSession().put(SESSION_NAME, user);
            }
            return SUCCESS;
        }else{
            messageErreur+="Votre mail ou votre mot de passe est incorect";
            return INPUT;
        }

    }

    /**
     * Deconnexion de l'utilisateur
     * @return
     */
    public String logout(){
        System.out.println("logout");
        try {
            getSession().remove(SESSION_NAME);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("is deconnect ? " + getSession().containsKey(SESSION_NAME));
        return INPUT;
    }

}
