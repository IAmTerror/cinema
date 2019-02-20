package fr.iat.cinema.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.checkerframework.common.aliasing.qual.Unique;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Unique
    private Long idtmdb;

    @Basic
    @Column(name = "name", nullable = true, length = 100)
    private String name; // surname + givenname

    @Basic
    @Column(name = "birthday", nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @Basic
    @Column(name = "image_path", nullable = true, length = 80)
    private String imagePath;

    @OneToMany(mappedBy = "director")
    @JsonBackReference
    private Set<Film> directedFilms;

    @OneToMany(mappedBy = "actor", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonBackReference
    private Set<Role> roles;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getIdtmdb() {
        return idtmdb;
    }

    public void setIdtmdb(Long idtmdb) {
        this.idtmdb = idtmdb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Set<Film> getDirectedFilms() {
        return directedFilms;
    }

    public void setDirectedFilms(Set<Film> directedFilms) {
        this.directedFilms = directedFilms;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return getId() == person.getId() &&
                Objects.equals(getIdtmdb(), person.getIdtmdb()) &&
                Objects.equals(getName(), person.getName()) &&
                Objects.equals(getBirthday(), person.getBirthday());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getIdtmdb(), getName(), getBirthday());
    }
}