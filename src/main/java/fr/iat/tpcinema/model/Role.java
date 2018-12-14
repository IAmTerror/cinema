//package fr.iat.tpcinema.model;
//
//public class Role {
//
//    private String alias;
//    private Personne acteur;
//    private Integer ordre;
//    private Film film;
//
//    public Role(String alias, Personne acteur, Integer ordre, Film film) {
//        this.alias = alias;
//        this.acteur = acteur;
//        this.ordre = ordre;
//        this.film = film;
//    }
//
//    public String getAlias() {
//        return alias;
//    }
//
//    public void setAlias(String alias) {
//        this.alias = alias;
//    }
//
//    public Personne getActeur() {
//        return acteur;
//    }
//
//    public void setActeur(Personne acteur) {
//        this.acteur = acteur;
//    }
//
//    public Integer getOrdre() {
//        return ordre;
//    }
//
//    public void setOrdre(Integer ordre) {
//        this.ordre = ordre;
//    }
//
//    public Film getFilm() {
//        return film;
//    }
//
//    public void setFilm(Film film) {
//        this.film = film;
//    }
//
//    public void ajouterRole() {
//        film.addRole(this);
//        acteur.addRole(this);
//    }
//
//    @Override
//    public String toString() {
//        return "Role{" +
//                "alias='" + alias + '\'' +
//                ", ordre=" + ordre +
//                '}';
//    }
//}
