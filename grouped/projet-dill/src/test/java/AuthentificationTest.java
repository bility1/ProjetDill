import Modele.AbstractDAOFactory;
import Modele.dao.AuthentificationDAO;
import Modele.dao.DAO;
import Modele.dao.SQLDAOFactory;
import Modele.dto.DTO;
import org.junit.Before;
import org.junit.Test;
import Modele.dto.AuthentificationDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthentificationTest {
    DAO dao;

        @Before
        public void testConnection() throws SQLException {
            dao = ((SQLDAOFactory) AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_TYPE.SQL)).getAuthentificationDAO();
        }

        @Test
        public void testInsertOne() {
            //dao.set(new AuthentificationDTO("test1@test1.fr","test1"));

        }

        @Test
        public void testInsertMAny(){
            /*List<DTO> dtos = new ArrayList<>();
            dtos.add(new AuthentificationDTO("test2@test1.fr","test21"));
            dtos.add(new AuthentificationDTO("test2@test2","test22"));
            dao.setAll(dtos);*/
        }

        @Test
        public void testUpdateOne(){
            //dao.update(new AuthentificationDTO("test1@test1.fr","test3"));
        }

        @Test
        public void testUpdateMany(){
            /*List<DTO> dtos = new ArrayList<>();
            dtos.add(new AuthentificationDTO("test2@test1.fr","test41"));
            dtos.add(new AuthentificationDTO("test2@test2","test42"));
            dao.updateAll(dtos);*/


        }


        @Test
        public void testDeleteOne(){

        }

        @Test
        public void testDeleteMany(){

        }

}
