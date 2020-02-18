package Actions;

import Modele.dto.AuthentificationDTO;
import Modele.dto.AuthentificationDTO.Role.*;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.net.httpserver.Authenticator;

import java.util.ArrayList;
import java.util.Collection;

public class Role extends ActionSupport {
    Collection<String> roles=new ArrayList<>();

    public Collection<String> getRoles() {
        roles.add(AuthentificationDTO.Role.ETUDIANT.toString());
        roles.add(AuthentificationDTO.Role.ENSEIGNANT.toString());
        roles.add(AuthentificationDTO.Role.ENTREPRISE.toString());
        return roles;
    }

}
