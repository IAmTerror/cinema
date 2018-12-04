package fr.iat.tpcinema.model;

public class Role {
    private Film film;
    private Personne personne;
    private String alias;
    private Integer ordre;

    public Role(Film film, Personne personne, String alias, Integer ordre) {
        this.film = film;
        this.personne = personne;
        this.alias = alias;
        this.ordre = ordre;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Integer getOrdre() {
        return ordre;
    }

    public void setOrdre(Integer ordre) {
        this.ordre = ordre;
    }

    @Override
    public String toString() {
        return "Role{" +
                "film=" + film +
                ", personne=" + personne +
                ", alias='" + alias + '\'' +
                ", ordre=" + ordre +
                '}';
    }
}
