package fr.iat.tpcinema.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/*** Tutotial for create a configuration service which encapsulate configuration datas :
 * https://www.boraji.com/spring-boot-configurationproperties-example ***/
@ConfigurationProperties(prefix = "tpcinema.path")
@Validated
public class Path {

    private String affiche;
    private String personne;

    public String getAffiche() {
        return affiche;
    }

    public void setAffiche(String affiche) {
        this.affiche = affiche;
    }

    public String getPersonne() {
        return personne;
    }

    public void setPersonne(String personne) {
        this.personne = personne;
    }
}
