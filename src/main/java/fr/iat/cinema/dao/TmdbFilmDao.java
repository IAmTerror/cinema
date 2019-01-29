package fr.iat.cinema.dao;

import fr.iat.cinema.model.Film;
import fr.iat.cinema.model.TmdbFilm;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TmdbFilmDao extends CrudRepository<TmdbFilm, Long> {

    public TmdbFilm findByIdtmdb(long id);
}
