package fr.iat.cinema.dao;

import fr.iat.cinema.model.Role;
import fr.iat.cinema.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserDao extends CrudRepository<User, Long> {
    public List<User> findAllByOrderByIdAsc();
}
