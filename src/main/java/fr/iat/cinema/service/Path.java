package fr.iat.cinema.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/*** Tutotial for create a configuration service which encapsulate configuration datas :
 * https://www.boraji.com/spring-boot-configurationproperties-example ***/
@ConfigurationProperties(prefix = "cinema.path")
@Validated
public class Path {

    private String affiche; // image d'in film
    private String poster; // image d'une personne

    public String getAffiche() {
        return affiche;
    }

    public void setAffiche(String affiche) {
        this.affiche = affiche;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
