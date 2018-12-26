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

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class Play implements Serializable {

    @Basic
    @Column(name = "film_id")
    private Long filmId;

    @Basic
    @Column(name = "person_id")
    private Long personId;

    @Basic
    @Column(name = "rank")
    private Integer rank;

    @Basic
    @Column(name = "name")
    private String name;

    public Long getFilmId() {
        return filmId;
    }

    public void setFilmId(Long filmId) {
        this.filmId = filmId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Play)) return false;
        Play play = (Play) o;
        return Objects.equals(getFilmId(), play.getFilmId()) &&
                Objects.equals(getPersonId(), play.getPersonId()) &&
                Objects.equals(getRank(), play.getRank()) &&
                Objects.equals(getName(), play.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFilmId(), getPersonId(), getRank(), getName());
    }
}