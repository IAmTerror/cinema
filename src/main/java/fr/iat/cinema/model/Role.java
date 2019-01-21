//package fr.iat.cinema.model;
//
//public class Role {
//
//    private String alias;
//    private Person acteur;
//    private Integer ordre;
//    private Film film;
//
//    public Role(String alias, Person acteur, Integer ordre, Film film) {
//        this.alias = alias;
//        this.acteur = acteur;
//        this.ordre = ordre;
//        this.film = film;
//    }
//
//    public String getAlias() {
//        return alias;
//    }
//
//    public void setAlias(String alias) {
//        this.alias = alias;
//    }
//
//    public Person getActeur() {
//        return acteur;
//    }
//
//    public void setActeur(Person acteur) {
//        this.acteur = acteur;
//    }
//
//    public Integer getOrdre() {
//        return ordre;
//    }
//
//    public void setOrdre(Integer ordre) {
//        this.ordre = ordre;
//    }
//
//    public Film getFilm() {
//        return film;
//    }
//
//    public void setFilm(Film film) {
//        this.film = film;
//    }
//
//    public void ajouterRole() {
//        film.addRole(this);
//        acteur.addRole(this);
//    }
//
//    @Override
//    public String toString() {
//        return "Role{" +
//                "alias='" + alias + '\'' +
//                ", ordre=" + ordre +
//                '}';
//    }
//}

package fr.iat.cinema.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name="play")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    @Basic
    @Column(name = "rank")
    private Integer rank;

    @Basic
    @Column(name = "name", length = 90)
    private String name;

    @ManyToOne
    @JoinColumn(name="person_id")
    @JsonManagedReference
    private Person actor;

    @ManyToOne
    @JoinColumn(name="film_id")
    @JsonManagedReference
    private Film film;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getActor() {
        return actor;
    }

    public void setActor(Person actor) {
        this.actor = actor;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role = (Role) o;
        return Objects.equals(getId(), role.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}