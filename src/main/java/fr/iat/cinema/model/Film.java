package fr.iat.cinema.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.checkerframework.common.aliasing.qual.Unique;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @Unique
    private Long idtmdb;

    @Basic
    @Column(name = "title", nullable = true, length = 256)
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
    @JsonManagedReference
    private Person director;

    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonBackReference
    private Set<Role> roles;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="film_genre", joinColumns = @JoinColumn(name="film_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    @JsonManagedReference
    private Set<Genre> genres;

    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonIgnore
    private Set<Review> reviews;

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

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Film)) return false;
        Film film = (Film) o;
        return getId() == film.getId() &&
                getIdtmdb() == film.getIdtmdb() &&
                Objects.equals(getTitle(), film.getTitle()) &&
                Objects.equals(getRating(), film.getRating()) &&
                Objects.equals(getImagePath(), film.getImagePath()) &&
                Objects.equals(getSummary(), film.getSummary()) &&
                Objects.equals(getReleaseDate(), film.getReleaseDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getIdtmdb(), getTitle(), getRating(), getImagePath(), getSummary(), getReleaseDate());
    }
}