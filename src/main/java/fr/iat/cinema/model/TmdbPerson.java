package fr.iat.cinema.model;

import org.checkerframework.common.aliasing.qual.Unique;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "tmdb_persons")
public class TmdbPerson {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String name;

    @NotNull
    @Unique
    private long idtmdb;

    public TmdbPerson() {
    }

    public TmdbPerson(String name, long idtmdb) {
        this.name = name;
        this.idtmdb = idtmdb;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getIdtmdb() {
        return idtmdb;
    }

    public void setIdtmdb(long idtmdb) {
        this.idtmdb = idtmdb;
    }
}
