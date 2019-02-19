package fr.iat.cinema.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "review")
public class Review {

    // TODO : faire un PlantUML du cycle de vie des commentaires

    public static final int WAITING_MODERATION = 1;
    public static final int PUBLISHED = 2;
    public static final int MUST_BE_MODIFIED = 3 ;
    public static final int DELETED = 4;
    public static final int ABANDONED = 5;
    public static final int REJECTED = 6 ;

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

    private boolean canTransitTo(int targetState) {
        boolean result;
        switch (targetState) {
            case Review.REJECTED :
                result = this.getState() == Review.WAITING_MODERATION;
                break;
            case Review.DELETED :
                result = this.getState() == Review.PUBLISHED;
                break;
            case Review.WAITING_MODERATION :
                result = this.getState() == Review.PUBLISHED || this.getState() == Review.WAITING_MODERATION
                        || this.getState() == Review.MUST_BE_MODIFIED;
                break;
            case Review.PUBLISHED :
                result = this.getState() == Review.WAITING_MODERATION;
                break;
            case Review.MUST_BE_MODIFIED :
                result = this.getState() == Review.WAITING_MODERATION;
                break;
            case Review.ABANDONED :
                result = this.getState() == Review.MUST_BE_MODIFIED;
                break;

                default:
                    result = false;
        }
        return result;
    }

    public void transitTo(int target) throws IllegalTransitionStateException {
        if(canTransitTo(target)) {
            this.state = target;
        } else {
            throw new IllegalTransitionStateException("Transition non autorisée");
        }
    }

    public void validByModerator() throws IllegalTransitionStateException {
        transitTo(Review.PUBLISHED);
    }

    public void keepForEditByModerator() throws IllegalTransitionStateException {
        transitTo(Review.MUST_BE_MODIFIED);
    }

    public void deleteByUser() throws IllegalTransitionStateException {
        transitTo(Review.DELETED);
    }

    public void abandonByUser() throws IllegalTransitionStateException {
        transitTo(Review.ABANDONED);
    }

    public void rejectedByModerator() throws IllegalTransitionStateException {
        transitTo(Review.REJECTED);
    }

    public void editByUser() throws IllegalTransitionStateException {
        transitTo(Review.WAITING_MODERATION);
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
