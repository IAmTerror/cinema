//package fr.iat.cinema.model;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
//public class Film {
//    private int id;
//    private String titre;
//    private Double notation;
//    private String affiche;
//    private String resume;
//    private Person realisateur;
//    private List<Role> roles = new ArrayList<>();
//
//    public Film(int id, String titre, Double notation, String affiche, String resume, Person realisateur) {
//        this.id = id;
//        this.titre = titre;
//        this.notation = notation;
//        this.affiche = affiche;
//        this.resume = resume;
//        this.realisateur = realisateur;
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
//    public String getTitre() {
//        return titre;
//    }
//
//    public void setTitre(String titre) {
//        this.titre = titre;
//    }
//
//    public Double getNotation() {
//        return notation;
//    }
//
//    public void setNotation(Double notation) {
//        this.notation = notation;
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
//    public String getResume() {
//        return resume;
//    }
//
//    public void setResume(String resume) {
//        this.resume = resume;
//    }
//
//    public Person getRealisateur() {
//        return realisateur;
//    }
//
//    public void setRealisateur(Person realisateur) {
//        this.realisateur = realisateur;
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
//        if (!(o instanceof Film)) return false;
//        Film film = (Film) o;
//        return getId() == film.getId() &&
//                Objects.equals(getTitre(), film.getTitre()) &&
//                Objects.equals(getNotation(), film.getNotation()) &&
//                Objects.equals(getAffiche(), film.getAffiche()) &&
//                Objects.equals(getResume(), film.getResume()) &&
//                Objects.equals(getRealisateur(), film.getRealisateur()) &&
//                Objects.equals(getRoles(), film.getRoles());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getId(), getTitre(), getNotation(), getAffiche(), getResume(), getRealisateur(), getRoles());
//    }
//
//    @Override
//    public String toString() {
//        return "Film{" +
//                "id=" + id +
//                ", titre='" + titre + '\'' +
//                ", notation=" + notation +
//                ", affiche='" + affiche + '\'' +
//                ", resume='" + resume + '\'' +
//                ", realisateur=" + realisateur +
//                ", roles=" + roles +
//                '}';
//    }
//}

package fr.iat.cinema.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "films")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Basic
    @Column(name = "title", nullable = false, length = 210)
    private String title;

    @Basic
    @Column(name = "rating", nullable = true, precision = 1)
    private BigDecimal rating;

    @Basic
    @Column(name = "image_path", nullable = true, length = 120)
    private String imagePath;

    @Basic
    @Column(name = "summary", nullable = true, length = -1)
    private String summary;

    @Basic
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="release_date", nullable = true)
    private LocalDate releaseDate;

    @ManyToOne
    @JoinColumn(name = "film_director")
    private Person director;

    // TODO : repasser orphanRemoval à true si nécessaire pour la persistance
    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL, orphanRemoval = false)
    private Set<Role> roles;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="film_genre", joinColumns = @JoinColumn(name="film_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres;

//    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<Review> reviews;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Person getDirector() {
        return director;
    }

    public void setDirector(Person director) {
        this.director = director;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

//    public Set<Review> getReviews() {
//        return reviews;
//    }
//
//    public void setReviews(Set<Review> reviews) {
//        this.reviews = reviews;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Film)) return false;
        Film film = (Film) o;
        return getId() == film.getId() &&
                Objects.equals(getTitle(), film.getTitle()) &&
                Objects.equals(getRating(), film.getRating()) &&
                Objects.equals(getImagePath(), film.getImagePath()) &&
                Objects.equals(getSummary(), film.getSummary()) &&
                Objects.equals(getReleaseDate(), film.getReleaseDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getRating(), getImagePath(), getSummary(), getReleaseDate());
    }
}