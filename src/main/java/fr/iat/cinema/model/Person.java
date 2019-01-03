//package fr.iat.cinema.model;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
//public class Person {
//    private int id;
//    private String nom;
//    private String prenom;
//    private Integer naissance;
//    private String affiche;
//    private int type;  // 1 = acteur ; 2 = réalisateur
//    private List<Role> roles = new ArrayList<>();
//
//    public Person(int id, String nom, String prenom, Integer naissance, String affiche, int type) {
//        this.id = id;
//        this.nom = nom;
//        this.prenom = prenom;
//        this.naissance = naissance;
//        this.affiche = affiche;
//        this.type = type;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getNom() {
//        return nom;
//    }
//
//    public void setNom(String nom) {
//        this.nom = nom;
//    }
//
//    public String getPrenom() {
//        return prenom;
//    }
//
//    public void setPrenom(String prenom) {
//        this.prenom = prenom;
//    }
//
//    public Integer getNaissance() {
//        return naissance;
//    }
//
//    public void setNaissance(Integer naissance) {
//        this.naissance = naissance;
//    }
//
//    public String getAffiche() {
//        return affiche;
//    }
//
//    public void setAffiche(String affiche) {
//        this.affiche = affiche;
//    }
//
//    public int getType() {
//        return type;
//    }
//
//    public void setType(int type) {
//        this.type = type;
//    }
//
//    public List<Role> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(List<Role> roles) {
//        this.roles = roles;
//    }
//
//    public void addRole(Role role){
//        if (!roles.contains(role)) {
//            this.roles.add(role);
//        }
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Person)) return false;
//        Person person = (Person) o;
//        return getId() == person.getId() &&
//                Objects.equals(getNom(), person.getNom()) &&
//                Objects.equals(getPrenom(), person.getPrenom()) &&
//                Objects.equals(getNaissance(), person.getNaissance()) &&
//                Objects.equals(getAffiche(), person.getAffiche()) &&
//                Objects.equals(roles, person.roles);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getId(), getNom(), getPrenom(), getNaissance(), getAffiche(), roles);
//    }
//
//    @Override
//    public String toString() {
//        return "Person{" +
//                "id=" + id +
//                ", nom='" + nom + '\'' +
//                ", prenom='" + prenom + '\'' +
//                ", naissance=" + naissance +
//                ", affiche='" + affiche + '\'' +
//                ", roles=" + roles +
//                '}';
//    }
//}

package fr.iat.cinema.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
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

    @Basic
    @Column(name = "surname", nullable = false, length = 60)
    private String surname; //nom

    @Basic
    @Column(name = "givenname", nullable = true, length = 40)
    private String givenname; //prenom

    @Basic
    @Column(name = "birthday", nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @Basic
    @Column(name = "image_path", nullable = true, length = 80)
    private String imagePath;

    @OneToMany(mappedBy = "director")
    private Set<Film> directedFilms;

    // TODO: repasser orphanRemoval à true si nécessaire pour la persistance
    @OneToMany(mappedBy = "actor", cascade = CascadeType.ALL, orphanRemoval = false)
    private Set<Role> roles;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGivenname() {
        return givenname;
    }

    public void setGivenname(String givenname) {
        this.givenname = givenname;
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
                Objects.equals(getSurname(), person.getSurname()) &&
                Objects.equals(getGivenname(), person.getGivenname()) &&
                Objects.equals(getBirthday(), person.getBirthday()) &&
                Objects.equals(getImagePath(), person.getImagePath());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSurname(), getGivenname(), getBirthday(), getImagePath());
    }
}
