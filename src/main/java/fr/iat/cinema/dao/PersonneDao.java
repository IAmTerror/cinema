// ========== IMPORT MANUEL ===================================================
//package fr.iat.cinema.dao;
//
//import fr.iat.cinema.model.Film;
//import fr.iat.cinema.model.Personne;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
///**
// * Created by fred on 03/02/2016.
// *
// * @author student : IAmTerror
// */
//
//@Component
//public class PersonneDao {
//
//    private List<Personne> personnes = new ArrayList<>();
//
//    public PersonneDao() {
//        personnes.add(new Personne(1,"Noiret", "Phillipe", 1930, "p0001.jpg",1));
//        personnes.add(new Personne(2,"Galabru", "Michel", 1922, "p0002.jpg",1));
//        personnes.add(new Personne(3,"Huppert", "Isabelle", 1953, "p0003.jpg",1));
//        personnes.add(new Personne(4,"Hamill", "Mark", 1951, "p0004.jpg",1));
//        personnes.add(new Personne(5,"Ford", "Harrison", 1942, "p0005.jpg",1));
//        personnes.add(new Personne(6,"Fisher", "Carrie", 1956, "p0006.jpg",1));
//        personnes.add(new Personne(7,"Gere", "Richard", 1949, "p0007.jpg",1));
//        personnes.add(new Personne(8,"Roberts", "Julia", 1967, "p0008.jpg",1));
//        personnes.add(new Personne(9,"Bellamy", "Ralph", 1904, "p0009.jpg",1));
//        personnes.add(new Personne(10,"Hopkins", "Anthony", 1937, "p0010.jpg",1));
//        personnes.add(new Personne(11,"Pitt", "William Bradley", 1963, "p0011.jpg",1));
//        personnes.add(new Personne(12,"Quinn", "Aidan", 1959, "p0012.jpg",1));
//        personnes.add(new Personne(13,"Thomas", "Henry", 1971, "p0013.jpg",1));
//        personnes.add(new Personne(14,"Barrymore", "Drew Bythe", 1975, "p0014.jpg",1));
//        personnes.add(new Personne(15,"Wallace Stone", "Dee", 1948, "p0015.jpg",1));
//        personnes.add(new Personne(16,"Heston", "Charlton", 1923, "p0016.jpg",1));
//        personnes.add(new Personne(17,"Boyd", "Stephen", 1928, "p0017.jpg",1));
//        personnes.add(new Personne(18,"Hawkins", "Jack", 1910, "p0018.jpg", 1));
//        personnes.add(new Personne(19,"Tavernier", "Bertrand", 1941, "p0110.jpg", 2));
//        personnes.add(new Personne(20,"Lucas", "Georges", 1944, "p0045.jpg", 2));
//        personnes.add(new Personne(21,"Marshall", "Garry", 1934, "p0098.jpg", 2));
//        personnes.add(new Personne(22,"Wyler", "William", 1902, "p0044.jpg", 2));
//        personnes.add(new Personne(23,"Spielberg", "Steven", 1946, "p0019.jpg", 2));
//        personnes.add(new Personne(24,"Zwick", "Edward", 1952, "p0020.jpg", 2));
//    }
//
//    public List<Personne> getPersonnes() {
//        return personnes;
//    }
//
//    public void setPersonnes(List<Personne> personnes) {
//        this.personnes = personnes;
//    }
//
//    public List<Personne> personnes() {
//        return personnes;
//    }
//
//    public Personne getById(int id) {
//        Personne personne = null;
//        for (Personne p : personnes) {
//            if (p.getId() == id) {
//                personne = p;
//                break;
//            }
//        }
//        return personne;
//    }
//}

// ========== DAO SANS JPA ===================================================
//package fr.iat.cinema.dao;
//
//import fr.iat.cinema.model.Personne;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import javax.persistence.Query;
//import java.math.BigInteger;
//import java.util.List;
//
//@Component
//public class PersonneDao {
//
//    @Autowired
//    private EntityManager entityManager;
//
//    @Transactional
//    public void save(Personne p){
//        entityManager.merge(p);
//    }
//
//    @Transactional
//    public void delete(long id){
//        Personne personne = entityManager.find (Personne.class, id);
//        if (personne == null) {
//            System.out.println("Cette personne n'existe pas dans la BDD");
//        } else {
//            entityManager.remove(personne);
//        }
//    }
//
//    public List<Personne> getAll(){
//        Query query = entityManager.createQuery("Select p from persons p");
//        return query.getResultList();
//    }
//
//    public Personne getById(Long id){
//        Personne retVal = null;
//        Query query = entityManager.createQuery("select p from persons p where p.id = :id");
//        query.setParameter("id", id);
//        List<Personne> personnes = query.getResultList();
//        if(!personnes.isEmpty()){
//            retVal = personnes.get(0);
//        }
//        return retVal;
//    }
//}

// ========== DAO AVEC JPA ===================================================
package fr.iat.cinema.dao;

import fr.iat.cinema.model.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonneDao extends CrudRepository<Personne, Long> {
    public List<Personne> findAllByOrderByIdAsc();
}
