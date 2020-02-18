package Modele.connections;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * Pattern Singleton
 * Créée une connection unique à la base Mongodb
 */
class MongoConnection implements Connection<MongoDatabase> {
    private static final String PROPERTIES_FILE="mongo";
    private static final String PROPERTIE_HOST="host";
    private static final String PROPERTIE_PORT="port";
    private static final String PROPERTIE_DATABASE="databaseName";
    private static final String PROPERTIE_USER = "user";
    private static final String PROPERTIE_PASSWORD = "password";

    private static MongoConnection connection;
    private MongoClient mongoClient;
    private MongoDatabase database;

    private MongoConnection(){
        ResourceBundle  resource = ResourceBundle.getBundle(PROPERTIES_FILE);

        /*char[] password = new char[] {'r', 'o', 'o', 't'};; // the password as a character array
        // ...

        MongoCredential credential = MongoCredential.createCredential(resource.getString(PROPERTIE_USER), resource.getString(PROPERTIE_DATABASE), password);

        MongoClientOptions options = MongoClientOptions.builder().sslEnabled(true).build();

        mongoClient = new MongoClient(new ServerAddress(resource.getString(PROPERTIE_HOST), Integer.valueOf(resource.getString(PROPERTIE_PORT))),
                Arrays.asList(credential),
                options);*/

        mongoClient = new MongoClient(resource.getString(PROPERTIE_HOST),Integer.valueOf(resource.getString(PROPERTIE_PORT)));
        database = mongoClient.getDatabase(resource.getString(PROPERTIE_DATABASE));

    }

    public MongoDatabase getConnection(){
        return database;
    }

    protected static Connection<MongoDatabase> getInstance() {
           if(connection == null){
               connection = new MongoConnection();
           }
        return connection;
    }
}
