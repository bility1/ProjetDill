package Actions.Etudiants;

import Global.Environnement;
import Modele.dto.ActeurDTO;
import Modele.dto.AuthentificationDTO;
import Modele.dto.Voeux;
import kafka.EtudiantKafka;
import kafka.KafkaConsumerExample;
import kafka.KafkaProducerExample;

import java.util.ArrayList;
import java.util.List;

public class NotificationVoeu extends Environnement {

    private ArrayList<String> notifs=new ArrayList<>();

    public ArrayList<String> getNotifs() {


        String tmp;
        String[] lines = new String[0];
        try {
            tmp = KafkaConsumerExample.runConsumer();
            lines= tmp.split("\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (String s:lines) {
            notifs.add(s);

        }
        return notifs;
    }

    public List<Voeux> getVoeux() {
        try {
            return getActeurDAO().getEtudiant(((AuthentificationDTO) getSession().get(SESSION_NAME)).getAdresseEmail()).getVoeux();
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public String execute() throws Exception {
        return SUCCESS;

    }
    public String validerenvoieMail(){
        //"{\"email\":\"aminatou.barry97@gmail.com\",\"nomProjet\":\"projet1\",\"position\":\"1\",\"etat\":\"valider\"}"

        Voeux v = null;
        for (Voeux vo:getVoeux()) {
            if(vo.getEtat()==Voeux.EtatVoeux.EN_ATTENTE){
                v=vo;
            }
        }
        v.setEtat(Voeux.EtatVoeux.VALIDER);

        KafkaProducerExample kafkaProducerExample=new KafkaProducerExample();

        for (ActeurDTO acteurDTO: getActeurDAO().getAllEtudiants()) {
            EtudiantKafka etudiantKafka =new EtudiantKafka(acteurDTO);
            kafkaProducerExample.addObservateur(etudiantKafka);
        }
        String messagetopic=getActeurDAO().getEtudiant(((AuthentificationDTO)getSession().get(SESSION_NAME)).getAdresseEmail()).getAdresseEmail()+","+v.getNomProjet()+","+v.getPosition()+","+v.getEtat();

        kafkaProducerExample.notifier(getActeurDAO().getEtudiant(((AuthentificationDTO)getSession().get(SESSION_NAME)).getAdresseEmail()).getAdresseEmail(),messagetopic);
        try {
            kafkaProducerExample.runProducer(messagetopic);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return SUCCESS;
    }

    public String listeNotifs() throws Exception {

        return SUCCESS;
    }
}
