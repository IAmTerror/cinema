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
        // TODO : CSS resize des images avec conservation des ratio H/W
        // TODO : différencier acteurs des réalisateurs (et autres métiers)
        // TODO : intégrer le release_date de Film dans les vues correspondantes
        // TODO : trouver un bugfix pour Review et User
        // TODO : pour la value du genre du film dans film/mod template, ne renvoyer qu'un genre au lieu de tous
        // TODO : remplacer Bootstrap par SemanticUI

        SpringApplication.run(CinemaSpringBootApplication.class, args);
    }
}
