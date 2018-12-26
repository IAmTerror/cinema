package fr.iat.cinema;


import fr.iat.cinema.service.Path;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EnableConfigurationProperties(Path.class)
public class TpCinemaSpringBootApplication {

    public static void main(String[] args) {

        // TODO : renommer package cinema en cinema
        // TODO : créer panneau latéral
        // TODO : gérer de façon plus simple le stream des images
        // TODO : trouver une solution pour afficher les fieldset
        // TODO : merge jpa_integration branch to master
        // TODO : bug fix : quand on ajoute une person (et autre objet aussi ?), l'appli essaie de...
        // ... lui donner l'id=1, échec, puis l'id=2, échec, jusqu'à un id pas pris. Faire en sorte que...
        // après la fermeture le l'application, la mémoire du dernier id donné soit persistée

        SpringApplication.run(TpCinemaSpringBootApplication.class, args);
    }
}
