package fr.iat.cinema.dao;

import fr.iat.cinema.model.Groups;
import fr.iat.cinema.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.HashSet;
import java.util.List;

public interface GroupDao extends CrudRepository<Groups, Long> {
    HashSet<Groups> findAll();
}
