package Global;

import Modele.dto.AuthentificationDTO;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

import java.util.Map;

public class AllowAcccessInterceptorEnseignant implements Interceptor {
    @Override
    public void destroy() {

    }

    @Override
    public void init() {

    }

    /**
     * Si l'utilisateur n'existe pas en session et n'est pas un ENSEIGNANT il est redirigé vers la page de connexion
     * Si non il peut accéder à la page demandé
     * @param actionInvocation
     * @return
     * @throws Exception
     */
    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        Map<String, Object> session = actionInvocation.getInvocationContext().getSession();
        if (session.get(Environnement.SESSION_NAME) == null) {
            return "notAllowAccess";
        } else {
            if (!session.isEmpty() && ((AuthentificationDTO)session.get(Environnement.SESSION_NAME)).getRole()== AuthentificationDTO.Role.ENSEIGNANT) {
                return actionInvocation.invoke();
            } else {
                return "notAllowAccess";
            }
        }
    }
}
