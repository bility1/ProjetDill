package Modele.dao;

import Modele.dto.ActeurDTO;

import java.util.List;

/**
 * Data Access Object Interface
 *
 * définit les méthodes CRUD que tous les DAO de l'appli doivent implémenter
 */
public interface DAO<T> {

    T get(T dto);
    void set(T dto);
    void update(T dto);
    void delete(T dto);

    List<T> getAll();
    void setAll(List<T> dtos);
    void updateAll(List<T> dtos);
    void deleteAll(List<T> dtos);



    //Ne pas ajouter de methode ici

}
