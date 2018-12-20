//package fr.iat.cinema.model;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
//public class Personne {
//    private int id;
//    private String nom;
//    private String prenom;
//    private Integer naissance;
//    private String affiche;
//    private int type;  // 1 = acteur ; 2 = réalisateur
//    private List<Role> roles = new ArrayList<>();
//
//    public Personne(int id, String nom, String prenom, Integer naissance, String affiche, int type) {
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
//        if (!(o instanceof Personne)) return false;
//        Personne personne = (Personne) o;
//        return getId() == personne.getId() &&
//                Objects.equals(getNom(), personne.getNom()) &&
//                Objects.equals(getPrenom(), personne.getPrenom()) &&
//                Objects.equals(getNaissance(), personne.getNaissance()) &&
//                Objects.equals(getAffiche(), personne.getAffiche()) &&
//                Objects.equals(roles, personne.roles);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getId(), getNom(), getPrenom(), getNaissance(), getAffiche(), roles);
//    }
//
//    @Override
//    public String toString() {
//        return "Personne{" +
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

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity(name = "persons")
public class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "surname", nullable = false, length = 60)
    private String nom;
    @Basic
    @Column(name = "givenname", nullable = true, length = 40)
    private String prenom;
    @Basic
    @Column(name = "birthday", nullable = true)
    // TODO : manage LocalDate to controller
    private LocalDate naissance;
    @Basic
    @Column(name = "image_path", nullable = true, length = 80)
    private String imagePath;
    // TODO : replacer List par Set (permet de s'assurer qu'il n'y a pas de redondances dans la collection)
    @OneToMany(mappedBy = "director")
    private List<Film> directedFilms;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String surname) {
        this.nom = surname;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String givenname) {
        this.prenom = givenname;
    }

    public LocalDate getNaissance() {
        return naissance;
    }

    public void setNaissance(LocalDate naissance) {
        this.naissance = naissance;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public List<Film> getDirectedFilms() {
        return directedFilms;
    }

    public void setDirectedFilms(List<Film> films) {
        this.directedFilms = films;
    }

    public void addDirectedFilm(Film film) {
        if (!directedFilms.contains(film)) {
            directedFilms.add(film);
            film.setDirector(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Personne persons = (Personne) o;

        if (id != persons.id) return false;
        if (nom != null ? !nom.equals(persons.nom) : persons.nom != null) return false;
        if (prenom != null ? !prenom.equals(persons.prenom) : persons.prenom != null) return false;
        if (naissance != null ? !naissance.equals(persons.naissance) : persons.naissance != null) return false;
        if (imagePath != null ? !imagePath.equals(persons.imagePath) : persons.imagePath != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        result = 31 * result + (prenom != null ? prenom.hashCode() : 0);
        result = 31 * result + (naissance != null ? naissance.hashCode() : 0);
        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
        return result;
    }
}
