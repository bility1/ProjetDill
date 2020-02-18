package kafka;

import Global.Environnement;
import Global.SendEmail;
import Modele.dto.ActeurDTO;

public class EtudiantKafka extends Environnement implements ObservateurKafka {
    ActeurDTO acteurDTO;

    public ActeurDTO getActeurDTO() {
        return acteurDTO;
    }

    public EtudiantKafka(ActeurDTO acteurDTO) {
        this.acteurDTO = acteurDTO;
    }

    @Override
    public void notification(String message) {
        System.out.println(message);
        String to=message.split(",")[0];
        String projet=message.split(",")[1];
        String etat=message.split(",")[3];
        SendEmail obj = new SendEmail();
        String mail = "aminatou.barry97@gmail.com";
        String pass = "Kadiatou93";
        String subject = "Notification";
        String m = "Votre voeu concernant le projet "+projet+" à maintenant un etat "+etat;
        obj.sendMail(mail, pass, to, subject, m);
    }
}
