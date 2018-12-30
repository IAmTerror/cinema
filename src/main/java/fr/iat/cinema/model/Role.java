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

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "play")
public class Role {

    // Composite key, for Person id + Film id (see RoleId)
    @EmbeddedId
    private RoleId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id") // TODO : très probablement inutile mais...
    @MapsId("filmId")
    private Film film;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id") // TODO : très probablement inutile mais...
    @MapsId("personId")
    private Person person;

    @Basic
    @Column(name = "rank")
    private Integer rank;

    @Basic
    @Column(name = "name")
    private String name;

    public Role(Film film, Person person) {
        this.film = film;
        this.person = person;
        this.id = new RoleId(film.getId(), person.getId());
    }

    public Role() {
    }

    public RoleId getId() {
        return id;
    }

    public void setId(RoleId id) {
        this.id = id;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
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

    public void ajouterRole() {
        film.addRole(this);
        person.addRole(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role = (Role) o;
        return Objects.equals(getId(), role.getId()) &&
                Objects.equals(getFilm(), role.getFilm()) &&
                Objects.equals(getPerson(), role.getPerson()) &&
                Objects.equals(getRank(), role.getRank()) &&
                Objects.equals(getName(), role.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFilm(), getPerson(), getRank(), getName());
    }
}