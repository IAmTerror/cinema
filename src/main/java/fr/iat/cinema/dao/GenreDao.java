//package fr.iat.cinema.dao;
//
//import fr.iat.cinema.model.Film;
//import fr.iat.cinema.model.Person;
//import fr.iat.cinema.model.Role;
//import org.springframework.stereotype.Component;
//
//@Component
//public class RoleDao {
//
////    private FilmDao filmDao = new FilmDao();
////    private PersonDao personneDao = new PersonDao();
//
//    public RoleDao(FilmDao filmDao, PersonDao personneDao) {
//
////        Role r;
////
////        r = new Role(filmDao.getById(1), personneDao.getById(1), "Le juge Rousseau", 1);
////        filmDao.getById(1).addRole(r);
////        personneDao.getById(1).addRole(r);
////        r = new Role(filmDao.getById(1), personneDao.getById(1), "Le juge Rousseau", 1);
////        filmDao.getById(1).addRole(r);
////        personneDao.getById(1).addRole(r);
////        r = new Role(filmDao.getById(1), personneDao.getById(2), "Joseph Bouvier", 2);
////        filmDao.getById(1).addRole(r);
////        personneDao.getById(2).addRole(r);
////        r = new Role(filmDao.getById(1), personneDao.getById(3), "Rose", 3);
////        filmDao.getById(1).addRole(r);
////        personneDao.getById(3).addRole(r);
////        r = new Role(filmDao.getById(2), personneDao.getById(4), "Luke Skywalker", 1);
////        filmDao.getById(2).addRole(r);
////        personneDao.getById(4).addRole(r);
////        r = new Role(filmDao.getById(2), personneDao.getById(5), "Han Solo", 2);
////        filmDao.getById(2).addRole(r);
////        personneDao.getById(5).addRole(r);
////        r = new Role(filmDao.getById(2), personneDao.getById(6), "Princesse Leia Organa", 3);
////        filmDao.getById(2).addRole(r);
////        personneDao.getById(6).addRole(r);
////        r = new Role(filmDao.getById(3), personneDao.getById(7), "Edward Lewis", 1);
////        filmDao.getById(3).addRole(r);
////        personneDao.getById(7).addRole(r);
////        r = new Role(filmDao.getById(3), personneDao.getById(8), "Vivian Ward", 2);
////        filmDao.getById(3).addRole(r);
////        personneDao.getById(8).addRole(r);
////        r = new Role(filmDao.getById(3), personneDao.getById(9), "James", 3);
////        filmDao.getById(3).addRole(r);
////        personneDao.getById(9).addRole(r);
////        r = new Role(filmDao.getById(4), personneDao.getById(10), "Colonel William Ludlow", 1);
////        filmDao.getById(4).addRole(r);
////        personneDao.getById(10).addRole(r);
////        r = new Role(filmDao.getById(4), personneDao.getById(11), "Tristan Ludlow", 2);
////        filmDao.getById(4).addRole(r);
////        personneDao.getById(11).addRole(r);
////        r = new Role(filmDao.getById(4), personneDao.getById(12), "Alfred Ludlow", 3);
////        filmDao.getById(4).addRole(r);
////        personneDao.getById(12).addRole(r);
////        r = new Role(filmDao.getById(4), personneDao.getById(13), "Samuel Ludlow", 4);
////        filmDao.getById(4).addRole(r);
////        personneDao.getById(13).addRole(r);
////        r = new Role(filmDao.getById(5), personneDao.getById(13), "Eliott", 1);
////        filmDao.getById(5).addRole(r);
////        personneDao.getById(13).addRole(r);
////        r = new Role(filmDao.getById(5), personneDao.getById(14), "Gertie", 2);
////        filmDao.getById(5).addRole(r);
////        personneDao.getById(14).addRole(r);
////        r = new Role(filmDao.getById(5), personneDao.getById(15), "Mary", 3);
////        filmDao.getById(5).addRole(r);
////        personneDao.getById(15).addRole(r);
////        r = new Role(filmDao.getById(6), personneDao.getById(16), "Judas Ben-Hur", 1);
////        filmDao.getById(5).addRole(r);
////        personneDao.getById(16).addRole(r);
////        r = new Role(filmDao.getById(6), personneDao.getById(17), "Messala", 2);
////        filmDao.getById(6).addRole(r);
////        personneDao.getById(17).addRole(r);
////        r = new Role(filmDao.getById(6), personneDao.getById(18), "Quintus Arius", 3);
////        filmDao.getById(6).addRole(r);
////        personneDao.getById(18).addRole(r);
////        System.out.println(filmDao.getById(1).getRoles());
////        System.out.println(filmDao.getById(2).getRoles());
////        System.out.println(filmDao.getById(3).getRoles());
////        System.out.println(filmDao.getById(4).getRoles());
////        System.out.println(filmDao.getById(5).getRoles());
////        System.out.println(filmDao.getById(6).getRoles());
//
//        Film film;
//        Person person;
//
//        film = filmDao.getById(1);
//        person = personneDao.getById(1);
//        new Role("Le juge Rousseau", person,0, film).ajouterRole();
//        person = personneDao.getById(2);
//        new Role("Joseph Bouvier", person,1, film).ajouterRole();
//        person = personneDao.getById(3);
//        new Role("Rose", person,2, film).ajouterRole();
//        film = filmDao.getById(2);
//        person = personneDao.getById(4);
//        new Role("Luke Skywalker", person,0, film).ajouterRole();
//        person = personneDao.getById(5);
//        new Role("Han Solo", person,1, film).ajouterRole();
//        person = personneDao.getById(6);
//        new Role("Princesse Leia Organa", person,2, film).ajouterRole();
//        film = filmDao.getById(3);
//        person = personneDao.getById(7);
//        new Role("Edward Lewis", person,0, film).ajouterRole();
//        person = personneDao.getById(8);
//        new Role("Vivan Ward", person,1, film).ajouterRole();
//        person = personneDao.getById(9);
//        new Role("James", person,2, film).ajouterRole();
//        film = filmDao.getById(6);
//        person = personneDao.getById(10);
//        new Role("Colonel William Ludlow", person,0, film);
//        person = personneDao.getById(11);
//        new Role("Tristan Ludlow", person,1, film).ajouterRole();
//        person = personneDao.getById(12);
//        new Role("Alfred Ludlow", person,2, film).ajouterRole();
//        person = personneDao.getById(13);
//        new Role("Samuel Ludlow", person,3, film).ajouterRole();
//        film = filmDao.getById(5);
//        person = personneDao.getById(13);
//        new Role("Eliott", person,0, film).ajouterRole();
//        person = personneDao.getById(14);
//        new Role("Gertie", person,1, film).ajouterRole();
//        person = personneDao.getById(15);
//        new Role("Mary", person,2, film).ajouterRole();
//        film = filmDao.getById(4);
//        person = personneDao.getById(16);
//        new Role("Judas Ben-Hur", person,0, film).ajouterRole();
//        person = personneDao.getById(17);
//        new Role("Messala", person,1, film).ajouterRole();
//        person = personneDao.getById(18);
//        new Role("Quintus Arrius", person,2, film).ajouterRole();
//
//        // ============================== TEST AFFICHAGE ROLES PAR FILMS ET PAR ACTEURS ==============================
//        System.out.println(filmDao.getById(1).getRoles());
//        System.out.println(filmDao.getById(2).getRoles());
//        System.out.println(filmDao.getById(3).getRoles());
//        System.out.println(filmDao.getById(4).getRoles());
//        System.out.println(filmDao.getById(5).getRoles());
//        System.out.println(filmDao.getById(6).getRoles());
//        System.out.println("********************************************************************");
//        System.out.println(personneDao.getById(1).getRoles());
//        System.out.println(personneDao.getById(2).getRoles());
//        System.out.println(personneDao.getById(3).getRoles());
//        System.out.println(personneDao.getById(4).getRoles());
//        System.out.println(personneDao.getById(5).getRoles());
//        System.out.println(personneDao.getById(6).getRoles());
//        System.out.println(personneDao.getById(7).getRoles());
//        System.out.println(personneDao.getById(8).getRoles());
//        System.out.println(personneDao.getById(9).getRoles());
//        System.out.println(personneDao.getById(10).getRoles());
//        System.out.println(personneDao.getById(11).getRoles());
//        System.out.println(personneDao.getById(12).getRoles());
//        System.out.println(personneDao.getById(13).getRoles());
//        System.out.println(personneDao.getById(14).getRoles());
//        System.out.println(personneDao.getById(15).getRoles());
//        System.out.println(personneDao.getById(16).getRoles());
//        System.out.println(personneDao.getById(17).getRoles());
//        System.out.println(personneDao.getById(18).getRoles());
//    }
//}

package fr.iat.cinema.dao;

import fr.iat.cinema.model.Genre;
import fr.iat.cinema.model.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GenreDao extends CrudRepository<Genre, Long> {
    public List<Genre> findAllByOrderByIdAsc();
}
