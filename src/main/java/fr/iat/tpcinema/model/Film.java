package fr.iat.tpcinema.model;

import java.util.Objects;

public class Film {

    int id;
    String titre;
    String afficheNom;
    double note;

    public Film(int id, String titre, String afficheNom, double note) {
        this.id = id;
        this.titre = titre;
        this.afficheNom = afficheNom;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAfficheNom() {
        return afficheNom;
    }

    public void setAfficheNom(String afficheNom) {
        this.afficheNom = afficheNom;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Film)) return false;
        Film film = (Film) o;
        return getId() == film.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", afficheNom='" + afficheNom + '\'' +
                ", note=" + note +
                '}';
    }
}