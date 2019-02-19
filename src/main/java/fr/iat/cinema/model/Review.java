package fr.iat.cinema.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "review")
public class Review {

    // TODO: renommer valider() en validByModerator(), delete() en deleteByUser(), etc...
    // TODO : faire un PlantUML du cycle de vie des commentaires

    public static final long ATTENTE_MODERATION = 1;
    public static final long PUBLIE = 2;
    public static final long ATTENTE_MODIFICATION = 3 ;
    public static final long SUPPRIME = 4;
    public static final long ABANDONNE = 5;
    public static final long REJETE = 6 ;

    private long state = Review.ATTENTE_MODERATION;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "article", nullable = true, length = -1)
    private String article;
    @Basic
    @Column(name = "datte", nullable = false)
    private Timestamp date;
    @ManyToOne
    @JoinColumn(name="film_id")
    private Film film;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp datte) {
        this.date = datte;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getState() {
        return this.state;
    }

    public void valider() throws IllegalTransitionStateException {
        if(this.getState() == Review.ATTENTE_MODERATION) {
            this.state = Review.PUBLIE;
        } else {
            throw new IllegalTransitionStateException("Transition non autorisée");
        }
    }

    public void retenirPourModification() throws IllegalTransitionStateException {
        if(this.getState() == Review.ATTENTE_MODERATION) {
            this.state = Review.ATTENTE_MODIFICATION;
        } else {
            throw new IllegalTransitionStateException("Transition non autorisée");
        }
    }

    public void supprimer() throws IllegalTransitionStateException {
        if(this.getState() == Review.PUBLIE) {
            this.state = Review.SUPPRIME;
        } else {
            throw new IllegalTransitionStateException("Transition non autorisée");
        }
    }

    public void annuler() throws IllegalTransitionStateException {
        if(this.getState() == Review.ATTENTE_MODIFICATION) {
            this.state = Review.ABANDONNE;
        } else {
            throw new IllegalTransitionStateException("Transition non autorisée");
        }
    }

    public void rejeter() throws IllegalTransitionStateException {
        if(this.getState() == Review.ATTENTE_MODERATION) {
            this.state = Review.REJETE;
        } else {
            throw new IllegalTransitionStateException("Transition non autorisée");
        }
    }

    public void editer() throws IllegalTransitionStateException {
        if(this.getState() == Review.ATTENTE_MODIFICATION || this.getState() == Review.PUBLIE ||
                this.getState() == Review.ATTENTE_MODERATION) {
            this.state = Review.ATTENTE_MODERATION;
        } else {
            throw new IllegalTransitionStateException("Transition non autorisée");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Review review = (Review) o;

        if (id != review.id) return false;
        if (article != null ? !article.equals(review.article) : review.article != null) return false;
        if (date != null ? !date.equals(review.date) : review.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (article != null ? article.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
