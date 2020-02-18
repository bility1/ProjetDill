package kafka;

import Modele.dto.ActeurDTO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public  interface ObservableKafka {
    public void notifier(String mail,String message);

}
