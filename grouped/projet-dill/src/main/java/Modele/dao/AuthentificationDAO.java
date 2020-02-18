package Modele.dao;

import Modele.dto.ActeurDTO;
import Modele.dto.AuthentificationDTO;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import org.bson.Document;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class AuthentificationDAO implements DAO<AuthentificationDTO> {

    private Connection connection;

    AuthentificationDAO(Connection connection){
        this.connection = connection;
    }

    @Override
    public AuthentificationDTO get(AuthentificationDTO dto) {
        AuthentificationDTO data = null;
        String sql = "SELECT * FROM authentification WHERE email = '"+((AuthentificationDTO) dto).getAdresseEmail()+"' ";
            try {
                ResultSet result = connection.createStatement()
                        .executeQuery(sql);
                if (result.first()) {
                    data = new AuthentificationDTO(result.getString("email"), result.getString("password"), AuthentificationDTO.Role.valueOf(result.getString("role")),Integer.parseInt(result.getString("isAdmin")));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return data;
    }


    public String getmdp(String mail) {
        String data = null;
        try {
            System.out.println("JE SUIS AVANT LA REQUETE ");
            ResultSet result = connection.createStatement()
                    .executeQuery("SELECT * FROM authentification WHERE email="+mail);
            System.out.println("JE SUIS APRES LA REQUETE");

                data =  result.getString("password");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("HELPOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");

        System.out.println(data);
        return data;



    }



    @Override
    public void set(AuthentificationDTO dto) {
        String sql = "INSERT INTO  authentification VALUES ('" + ((AuthentificationDTO) dto).getAdresseEmail() + "','" + ((AuthentificationDTO) dto).getPassword() + "','" + ((AuthentificationDTO) dto).getRole().toString() + "','" + ((AuthentificationDTO) dto).getIsAdmin()+ "')";

        try {
                ResultSet result = connection.createStatement()
                        .executeQuery( sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void update(AuthentificationDTO dto) {
        String sql="UPDATE authentification SET password='" + ((AuthentificationDTO) dto).getPassword() + "'  WHERE email='" + ((AuthentificationDTO) dto).getAdresseEmail()+"'";
            try {
                ResultSet result = connection.createStatement()
                        .executeQuery(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void delete(AuthentificationDTO dto) {
            try {
                ResultSet result = connection.createStatement()
                        .executeQuery("DELETE FROM Authentification WHERE adresseEmail=" + ((AuthentificationDTO) dto).getAdresseEmail());
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public List<AuthentificationDTO> getAll() {
        List<AuthentificationDTO> dtos = new ArrayList<>();
        try{
            ResultSet result = connection.createStatement()
                    .executeQuery("SELECT * FROM Authentification");
            while(result.next()){
                dtos.add(new AuthentificationDTO(result.getString("adresseEmail"),result.getString("password")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return dtos;
    }

    @Override
    public void setAll(List<AuthentificationDTO> dtos) {
        for(AuthentificationDTO dto : dtos){
            set(dto);
        }
    }

    @Override
    public void updateAll(List<AuthentificationDTO> dtos) {
        for(AuthentificationDTO dto: dtos){
          update(dto);
        }
    }

    @Override
    public void deleteAll(List<AuthentificationDTO> dtos) {
        for(AuthentificationDTO dto : dtos){
            delete(dto);
        }

    }


    //todo ajouter des methodes pour des requetes specifiques ici
}
