package fr.iat.cinema.dao;

import fr.iat.cinema.model.Review;
import fr.iat.cinema.model.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewDao extends CrudRepository<Review, Long> {
    public List<Review> findAllByOrderByIdAsc();
}
