package fr.iat.cinema.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/**
 * Projet thyme-security
 * Pour LAERCE SAS
 * <p>
 * Créé le  21/03/2017.
 *
 * @author fred
 */
@Entity
@Table(name= "GROUPS")
public class Groups {
    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private long id;
    @Basic
    @Column(name = "NAME", nullable = false, length = 50)
    private String name;
    @Basic
    @Column(name = "ROLE", nullable = false, length = 50)
    private String role;
    @ManyToMany(mappedBy = "groups")
    private Set<User> users;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Groups)) return false;
        Groups groups = (Groups) o;
        return getId() == groups.getId() &&
                Objects.equals(getName(), groups.getName()) &&
                Objects.equals(getRole(), groups.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getRole());
    }
}
