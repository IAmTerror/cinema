package fr.iat.cinema.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "review")
public class Review {

    // TODO : faire un PlantUML du cycle de vie des commentaires

    public static final long WAITING_MODERATION = 1;
    public static final long PUBLISHED = 2;
    public static final long MUST_BE_MODIFIED = 3 ;
    public static final long DELETED = 4;
    public static final long ABANDONED = 5;
    public static final long REJECTED = 6 ;

    private long state = Review.WAITING_MODERATION;

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

    public void validByModerator() throws IllegalTransitionStateException {
        if(this.getState() == Review.WAITING_MODERATION) {
            this.state = Review.PUBLISHED;
        } else {
            throw new IllegalTransitionStateException("Transition non autorisée");
        }
    }

    public void keepForEditByModerator() throws IllegalTransitionStateException {
        if(this.getState() == Review.WAITING_MODERATION) {
            this.state = Review.MUST_BE_MODIFIED;
        } else {
            throw new IllegalTransitionStateException("Transition non autorisée");
        }
    }

    public void deleteByUser() throws IllegalTransitionStateException {
        if(this.getState() == Review.PUBLISHED) {
            this.state = Review.DELETED;
        } else {
            throw new IllegalTransitionStateException("Transition non autorisée");
        }
    }

    public void abandonByUser() throws IllegalTransitionStateException {
        if(this.getState() == Review.MUST_BE_MODIFIED) {
            this.state = Review.ABANDONED;
        } else {
            throw new IllegalTransitionStateException("Transition non autorisée");
        }
    }

    public void rejectedByModerator() throws IllegalTransitionStateException {
        if(this.getState() == Review.WAITING_MODERATION) {
            this.state = Review.REJECTED;
        } else {
            throw new IllegalTransitionStateException("Transition non autorisée");
        }
    }

    public void editByUser() throws IllegalTransitionStateException {
        if(this.getState() == Review.MUST_BE_MODIFIED || this.getState() == Review.PUBLISHED ||
                this.getState() == Review.WAITING_MODERATION) {
            this.state = Review.WAITING_MODERATION;
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
