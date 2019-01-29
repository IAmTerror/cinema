package fr.iat.cinema.dao;

import fr.iat.cinema.model.TmdbPerson;
import org.springframework.data.repository.CrudRepository;

public interface TmdbPersonDao extends CrudRepository<TmdbPerson, Long> {
    public TmdbPerson findByIdtmdb(long id);
}
