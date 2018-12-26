package fr.iat.cinema.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * RoleId manage composite key from Role id
 * @See https://vladmihalcea.com/the-best-way-to-map-a-many-to-many-association-with-extra-columns-when-using-jpa-and-hibernate/
 */

@Embeddable
public class RoleId implements Serializable {

    @Column(name = "person_id")
    private Long personId;
    @Column(name = "film_id")
    private Long filmId;

    public RoleId(Long personId, Long filmId) {
        this.personId = personId;
        this.filmId = filmId;
    }

    public RoleId() {
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long getFilmId() {
        return filmId;
    }

    public void setFilmId(Long filmId) {
        this.filmId = filmId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoleId)) return false;
        RoleId roleId = (RoleId) o;
        return Objects.equals(getPersonId(), roleId.getPersonId()) &&
                Objects.equals(getFilmId(), roleId.getFilmId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPersonId(), getFilmId());
    }
}
