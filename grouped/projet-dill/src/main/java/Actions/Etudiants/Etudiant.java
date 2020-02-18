package Actions.Etudiants;

import Modele.AbstractDAOFactory;
import Modele.dao.ActeurDAO;
import Modele.dao.DAO;
import Modele.dao.MongoDAOFactory;
import Modele.dto.ActeurDTO;
import com.opensymphony.xwork2.ActionSupport;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Etudiant extends ActionSupport {
    private Collection<ActeurDTO> etudiants=new ArrayList<>();
    private  ArrayList<Integer >  groupes=new ArrayList<>();

    public Collection<ActeurDTO> getEtudiants() {
        DAO dao = null;
        {
            try {
                dao = ((MongoDAOFactory) AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_TYPE.MONGO)).getActeurDAO();        } catch (SQLException e) {
                e.printStackTrace();
            }
        }
      //  AuthentificationDTO adto= (AuthentificationDTO) varSession.get("user");
        /*dao.set(acteur2);*/
        etudiants =  ((ActeurDAO)dao).getAllEtudiantsGroupeByNumeroGroupe();
        return etudiants;
    }

    public  ArrayList<Integer >  getGroupes() {
        for (ActeurDTO a: etudiants) {

                if(!groupes.contains(a.getNumeroGroupe())){
                    groupes.add(a.getNumeroGroupe());
                }

        }
        return groupes;
    }

    public void parGroupe(Collection<ActeurDTO> acteurs){
        HashMap<Integer,ArrayList<String >> ma=new HashMap<>();
        for (ActeurDTO a: acteurs) {
            //ma.put(a.getNumeroGroupe(),)
            ArrayList<String> tmp=new ArrayList<>();
            for (ActeurDTO a2: acteurs) {
                if(a.getNumeroGroupe()==a2.getNumeroGroupe()){
                    tmp.add(a2.getPrenom()+","+a2.getNom());
                }
            }
        }
    }
}
