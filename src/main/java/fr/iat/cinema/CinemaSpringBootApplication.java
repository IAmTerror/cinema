package fr.iat.cinema;


import fr.iat.cinema.service.Path;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EnableConfigurationProperties(Path.class)
public class CinemaSpringBootApplication {

    public static void main(String[] args) {

        // TODO : créer panneau latéral
        // TODO : gérer de façon plus simple le stream des images
        // TODO : trouver une solution pour afficher les fieldset
        // TODO : bug fix : quand on ajoute une person (et autre objet aussi ?), l'appli essaie de...
        // TODO : CSS resize des images avec conservation des ratio H/W
        // TODO : différencier acteurs des réalisateurs (et autres métiers)
        // TODO : permettre de choisir un réalisateur lors de la création du film
        // TODO : le CRUD de Role ne fonctionne pas (erreur Hibernate : Could not set field value by reflection)
        // TODO : Remplacer les List par des Set
        // TODO : intégrer le release_date de Film dans les vues correspondantes
        // TODO : trouver un bugfix pour Review et User

        SpringApplication.run(CinemaSpringBootApplication.class, args);
    }
}
