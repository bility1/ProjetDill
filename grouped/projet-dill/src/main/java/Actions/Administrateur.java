package Actions;

import Modele.dao.AuthentificationDAO;
import Modele.dao.DAO;
import Modele.dto.AuthentificationDTO;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ApplicationAware;

import java.util.Map;

public class Administrateur extends ActionSupport implements ApplicationAware {
    private String email;
    private String mdp;
    private AuthentificationDTO authentificationDTO ;
    private AuthentificationDAO dao;

    public String getEmail() {


        return email;
    }

    public String getMdp() {
        String mail = "cecile@hotmail.fr" ;
        authentificationDTO = new AuthentificationDTO(mail, "");
        mdp=dao.getmdp(mail);
        return mdp;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMdp(String mdp) {


        this.mdp = mdp;
    }

    @Override
    public void setApplication(Map<String, Object> map) {

    }
}
