package fr.iat.tpcinema.dao;

import fr.iat.tpcinema.model.Film;
import fr.iat.tpcinema.model.Personne;
import fr.iat.tpcinema.model.Role;

import java.util.ArrayList;
import java.util.List;

public class RoleDao {

    private FilmDao filmDao = new FilmDao();
    private PersonneDao personneDao = new PersonneDao();

    public RoleDao() {

        Role r;

        r = new Role(filmDao.getById(1), personneDao.getById(1), "Le juge Rousseau", 1);
        filmDao.getById(1).addRole(r);
        personneDao.getById(1).addRole(r);
        r = new Role(filmDao.getById(1), personneDao.getById(1), "Le juge Rousseau", 1);
        filmDao.getById(1).addRole(r);
        personneDao.getById(1).addRole(r);
        r = new Role(filmDao.getById(1), personneDao.getById(2), "Joseph Bouvier", 2);
        filmDao.getById(1).addRole(r);
        personneDao.getById(2).addRole(r);
        r = new Role(filmDao.getById(1), personneDao.getById(3), "Rose", 3);
        filmDao.getById(1).addRole(r);
        personneDao.getById(3).addRole(r);
        r = new Role(filmDao.getById(2), personneDao.getById(4), "Luke Skywalker", 1);
        filmDao.getById(2).addRole(r);
        personneDao.getById(4).addRole(r);
        r = new Role(filmDao.getById(2), personneDao.getById(5), "Han Solo", 2);
        filmDao.getById(2).addRole(r);
        personneDao.getById(5).addRole(r);
        r = new Role(filmDao.getById(2), personneDao.getById(6), "Princesse Leia Organa", 3);
        filmDao.getById(2).addRole(r);
        personneDao.getById(6).addRole(r);
        r = new Role(filmDao.getById(3), personneDao.getById(7), "Edward Lewis", 1);
        filmDao.getById(3).addRole(r);
        personneDao.getById(7).addRole(r);
        r = new Role(filmDao.getById(3), personneDao.getById(8), "Vivian Ward", 2);
        filmDao.getById(3).addRole(r);
        personneDao.getById(8).addRole(r);
        r = new Role(filmDao.getById(3), personneDao.getById(9), "James", 3);
        filmDao.getById(3).addRole(r);
        personneDao.getById(9).addRole(r);
        r = new Role(filmDao.getById(4), personneDao.getById(10), "Colonel William Ludlow", 1);
        filmDao.getById(4).addRole(r);
        personneDao.getById(10).addRole(r);
        r = new Role(filmDao.getById(4), personneDao.getById(11), "Tristan Ludlow", 2);
        filmDao.getById(4).addRole(r);
        personneDao.getById(11).addRole(r);
        r = new Role(filmDao.getById(4), personneDao.getById(12), "Alfred Ludlow", 3);
        filmDao.getById(4).addRole(r);
        personneDao.getById(12).addRole(r);
        r = new Role(filmDao.getById(4), personneDao.getById(13), "Samuel Ludlow", 4);
        filmDao.getById(4).addRole(r);
        personneDao.getById(13).addRole(r);
        r = new Role(filmDao.getById(5), personneDao.getById(13), "Eliott", 1);
        filmDao.getById(5).addRole(r);
        personneDao.getById(13).addRole(r);
        r = new Role(filmDao.getById(5), personneDao.getById(14), "Gertie", 2);
        filmDao.getById(5).addRole(r);
        personneDao.getById(14).addRole(r);
        r = new Role(filmDao.getById(5), personneDao.getById(15), "Mary", 3);
        filmDao.getById(5).addRole(r);
        personneDao.getById(15).addRole(r);
        r = new Role(filmDao.getById(6), personneDao.getById(16), "Judas Ben-Hur", 1);
        filmDao.getById(5).addRole(r);
        personneDao.getById(16).addRole(r);
        r = new Role(filmDao.getById(6), personneDao.getById(17), "Messala", 2);
        filmDao.getById(6).addRole(r);
        personneDao.getById(17).addRole(r);
        r = new Role(filmDao.getById(6), personneDao.getById(18), "Quintus Arius", 3);
        filmDao.getById(6).addRole(r);
        personneDao.getById(18).addRole(r);
    }
}
