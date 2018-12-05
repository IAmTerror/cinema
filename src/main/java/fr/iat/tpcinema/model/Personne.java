package fr.iat.tpcinema.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Personne {
    private int id;
    private String nom;
    private String prenom;
    private Integer naissance;
    private String photoPath;
    private List<Role> roles = new ArrayList<>();

    public Personne(int id, String nom, String prenom, Integer naissance, String photoPath) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.naissance = naissance;
        this.photoPath = photoPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Integer getNaissance() {
        return naissance;
    }

    public void setNaissance(Integer naissance) {
        this.naissance = naissance;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role){
        if (!roles.contains(role)) {
            this.roles.add(role);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Personne)) return false;
        Personne personne = (Personne) o;
        return getId() == personne.getId() &&
                Objects.equals(getNom(), personne.getNom()) &&
                Objects.equals(getPrenom(), personne.getPrenom()) &&
                Objects.equals(getNaissance(), personne.getNaissance()) &&
                Objects.equals(getPhotoPath(), personne.getPhotoPath()) &&
                Objects.equals(roles, personne.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNom(), getPrenom(), getNaissance(), getPhotoPath(), roles);
    }

    @Override
    public String toString() {
        return "Personne{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", naissance=" + naissance +
                ", photoPath='" + photoPath + '\'' +
                ", roles=" + roles +
                '}';
    }
}
