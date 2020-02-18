package Modele.dto;

import java.util.ArrayList;

public interface Entreprise {

    void proposerSujet(String nom, String ville, Sujet.Statut statut);
    ArrayList<Sujet> voirPropositions();
    Sujet voirStatut(int propositionId);
}
