package fr.iat.tpcinema;

import fr.iat.tpcinema.service.Path;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EnableConfigurationProperties(Path.class)
public class TpCinemaSpringBootApplication {

    public static void main(String[] args) {

        // TODO : renommer package tpcinema en cinema
        // TODO : créer panneau latéral pour créer acteurs, films (temporaires) (boostrap left menu)
        // TODO : gérer de façon plus simple le stream des images
        // TODO : Pour les photos : imput type = file en HTML dans les vues, puis créer un controller
        // Le type dans le @RequestParam sera MultipartFile
        // le Mapping sera du @PostMapping
        // Autres mots cléq : getContentType(), Image Manager, getInputStream()

        SpringApplication.run(TpCinemaSpringBootApplication.class, args);
    }
}
