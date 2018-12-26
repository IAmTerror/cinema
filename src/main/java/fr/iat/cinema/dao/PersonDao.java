// ========== IMPORT MANUEL ===================================================
//package fr.iat.cinema.dao;
//
//import fr.iat.cinema.model.Film;
//import fr.iat.cinema.model.Person;
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
//public class PersonDao {
//
//    private List<Person> persons = new ArrayList<>();
//
//    public PersonDao() {
//        persons.add(new Person(1,"Noiret", "Phillipe", 1930, "p0001.jpg",1));
//        persons.add(new Person(2,"Galabru", "Michel", 1922, "p0002.jpg",1));
//        persons.add(new Person(3,"Huppert", "Isabelle", 1953, "p0003.jpg",1));
//        persons.add(new Person(4,"Hamill", "Mark", 1951, "p0004.jpg",1));
//        persons.add(new Person(5,"Ford", "Harrison", 1942, "p0005.jpg",1));
//        persons.add(new Person(6,"Fisher", "Carrie", 1956, "p0006.jpg",1));
//        persons.add(new Person(7,"Gere", "Richard", 1949, "p0007.jpg",1));
//        persons.add(new Person(8,"Roberts", "Julia", 1967, "p0008.jpg",1));
//        persons.add(new Person(9,"Bellamy", "Ralph", 1904, "p0009.jpg",1));
//        persons.add(new Person(10,"Hopkins", "Anthony", 1937, "p0010.jpg",1));
//        persons.add(new Person(11,"Pitt", "William Bradley", 1963, "p0011.jpg",1));
//        persons.add(new Person(12,"Quinn", "Aidan", 1959, "p0012.jpg",1));
//        persons.add(new Person(13,"Thomas", "Henry", 1971, "p0013.jpg",1));
//        persons.add(new Person(14,"Barrymore", "Drew Bythe", 1975, "p0014.jpg",1));
//        persons.add(new Person(15,"Wallace Stone", "Dee", 1948, "p0015.jpg",1));
//        persons.add(new Person(16,"Heston", "Charlton", 1923, "p0016.jpg",1));
//        persons.add(new Person(17,"Boyd", "Stephen", 1928, "p0017.jpg",1));
//        persons.add(new Person(18,"Hawkins", "Jack", 1910, "p0018.jpg", 1));
//        persons.add(new Person(19,"Tavernier", "Bertrand", 1941, "p0110.jpg", 2));
//        persons.add(new Person(20,"Lucas", "Georges", 1944, "p0045.jpg", 2));
//        persons.add(new Person(21,"Marshall", "Garry", 1934, "p0098.jpg", 2));
//        persons.add(new Person(22,"Wyler", "William", 1902, "p0044.jpg", 2));
//        persons.add(new Person(23,"Spielberg", "Steven", 1946, "p0019.jpg", 2));
//        persons.add(new Person(24,"Zwick", "Edward", 1952, "p0020.jpg", 2));
//    }
//
//    public List<Person> getPersonnes() {
//        return persons;
//    }
//
//    public void setPersonnes(List<Person> persons) {
//        this.persons = persons;
//    }
//
//    public List<Person> persons() {
//        return persons;
//    }
//
//    public Person getById(int id) {
//        Person person = null;
//        for (Person p : persons) {
//            if (p.getId() == id) {
//                person = p;
//                break;
//            }
//        }
//        return person;
//    }
//}

// ========== DAO SANS JPA ===================================================
//package fr.iat.cinema.dao;
//
//import fr.iat.cinema.model.Person;
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
//public class PersonDao {
//
//    @Autowired
//    private EntityManager entityManager;
//
//    @Transactional
//    public void save(Person p){
//        entityManager.merge(p);
//    }
//
//    @Transactional
//    public void delete(long id){
//        Person person = entityManager.find (Person.class, id);
//        if (person == null) {
//            System.out.println("Cette person n'existe pas dans la BDD");
//        } else {
//            entityManager.remove(person);
//        }
//    }
//
//    public List<Person> getAll(){
//        Query query = entityManager.createQuery("Select p from persons p");
//        return query.getResultList();
//    }
//
//    public Person getById(Long id){
//        Person retVal = null;
//        Query query = entityManager.createQuery("select p from persons p where p.id = :id");
//        query.setParameter("id", id);
//        List<Person> persons = query.getResultList();
//        if(!persons.isEmpty()){
//            retVal = persons.get(0);
//        }
//        return retVal;
//    }
//}

// ========== DAO AVEC JPA ===================================================
package fr.iat.cinema.dao;

import fr.iat.cinema.model.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonDao extends CrudRepository<Person, Long> {
    public List<Person> findAllByOrderByIdAsc();
}
