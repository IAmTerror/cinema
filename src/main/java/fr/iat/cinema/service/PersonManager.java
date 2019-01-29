package fr.iat.cinema.service;

import fr.iat.cinema.dao.PersonDao;
import fr.iat.cinema.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonManager {
    @Autowired
    PersonDao personDao;

    public List<Person> getAll(){
        return personDao.findAllByOrderByName();
    }
}
