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
        // ... lui donner l'id=1, échec, puis l'id=2, échec, jusqu'à un id pas pris. Faire en sorte que...
        // après la fermeture le l'application, la mémoire du dernier id donné soit persistée
        // TODO : CSS resize des images avec conservation des ratio H/W
        // TODO : différencier acteurs des réalisateurs (et autres métiers)
        // TODO : listes déroulantes dans les templates

        SpringApplication.run(CinemaSpringBootApplication.class, args);
    }
}
