package Actions;

public class Deconnexion extends  Authentification{

    @Override
    public String execute() throws Exception {

        this.logout();

        return SUCCESS;
    }
}
