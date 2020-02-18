package Actions.admin;

import Global.Environnement;
import Modele.AbstractDAOFactory;
import Modele.dao.DAO;
import Modele.dao.MongoDAOFactory;
import com.mongodb.MongoClient;
import org.bson.Document;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FileUploadAction extends Environnement {
    private static final long serialVersionUID = 1L;


    private File uploadFile;
    private String uploadFileContentType;
    private String uploadFileFileName;

    DAO dao;
    {
        try {
            dao = ((MongoDAOFactory) AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_TYPE.MONGO)).getActeurDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public File getUploadFile() {
        return uploadFile;
    }
    public void setUploadFile(File uploadFile) {
        this.uploadFile = uploadFile;
    }

    public String getUploadFileContentType() {
        return uploadFileContentType;
    }
    public void setUploadFileContentType(String uploadFileContentType) {
        this.uploadFileContentType = uploadFileContentType;
    }

    public String getUploadFileFileName() {
        return uploadFileFileName;
    }
    public void setUploadFileFileName(String uploadFileFileName) {
        this.uploadFileFileName = uploadFileFileName;
    }


    public String execute()
    {
        try{
            if(uploadFileFileName==null){
                addFieldError("uploadFile", "Un fichier est requis !");
                return INPUT;
            }
            // On vérifie si le fichier est bien un .csv
            String name = getUploadFileFileName();
            String []fileext = name.split("\\.");
            String ext = fileext[1];
            if(ext.equals("csv")){
                // Initialisation de la conversion du CSV en JSONObject
                int i;
                String line = "";
                BufferedReader fileReader = null;
                boolean initial = true;
                ArrayList<String> header = new ArrayList<String>();
                JSONObject record = new JSONObject();
                String json;

                try {
                    // On lit le fichier csv importer
                    fileReader = new BufferedReader(new FileReader(uploadFile));
                    while ((line = fileReader.readLine()) != null) {
                        i = 0;
                        String[] tokens = line.split(",");
                        if (initial) {

                            for (String token : tokens) {
                                if (token.startsWith("\"")) {
                                    token = token.substring(1);
                                }
                                if (token.endsWith("\"")) {
                                    token = token.substring(0, token.length() - 1);
                                }
                                header.add(token);
                            }
                            initial = false;
                            continue;

                        }
                        // Récupère tout les tokens sur la ligne
                        for (String token : tokens) {
                            if (token.startsWith("\"")) {
                                token = token.substring(1);
                            }
                            if (token.endsWith("\"")) {
                                token = token.substring(0, token.length() - 1);
                            }
                            record.put(header.get(i).toString(), token);
                            i++;
                            if (i == header.size())
                                break;

                        }
                        // Vérification que le fichier csv à bien été passer en jsonObject
                        System.out.println(record);
                        // On passe le contenu du JSONObject en String
                        json=record.toString();
                        // Commande qui permet d'insérer le JSON qui est en String
                        new MongoClient().getDatabase("projet-dill").getCollection("Acteur").insertOne(Document.parse(json));

                        //insertion dans le table authentification
                        //ResourceBundle resource = ResourceBundle.getBundle("mariadb");
                        //DriverManager.getConnection(resource.getString("url"), resource.getString("user"), resource.getString("password_csv"));
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else{
                addFieldError("uploadFile", "Le type de fichier n'est pas un csv !");
                return INPUT;
            }
        }catch(Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
            return INPUT;
        }
        return SUCCESS;
    }

}
