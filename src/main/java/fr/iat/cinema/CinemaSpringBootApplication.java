package fr.iat.cinema;


import fr.iat.cinema.service.Path;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EnableConfigurationProperties(Path.class)
public class CinemaSpringBootApplication {

    public static void main(String[] args) {

        // TODO : créer panneau latéral
        // TODO : gérer de façon plus simple le stream des images
        // TODO : différencier acteurs des réalisateurs (et autres métiers)
        // TODO : intégrer le release_date de Film dans les vues correspondantes
        // TODO : trouver un bugfix pour Review et User
        // TODO : pour la value du genre du film dans film/mod template, ne renvoyer qu'un genre au lieu de tous
        // TODO : responsive
        // TODO : séparer le JS du HTML
        // TODO : uniformiser appelations Role et Play (convertir classe Role en Play, de même pour les model.attribute)
        // TODO : afficher un message à la fin des populate pour prévenir que l'import via ETL est terminé

        // ----------- TRAVAIL du 28/01/2019
        // TODO : creer des identifiants TMDB dans Persons et Movies, puis dans les DAO, ajouter une condition pour...
        // ... vérifier si les personnes et films ont déjà été importés en base, en fonction de l'identifiant TMDB...
        // ... et ceci, afin de ne pas importer dix fois le même réalisateur par exemple
        // ... lors de l'import de ses 10 films
        // TODO : transfermer nom et prenom de persons en name (tout réuni) pour conformité avec l'API TMDB
        // TODO : transfermer nom et prenom de persons en name (tout réuni) pour conformité avec l'API TMDB
        // TODO : éventuellement, enrichir les fiches persons et movies (durée du film, etc...)
        // TODO : Rest Template (ou bien, de facon manuelle, taper dans l'URL) pour l'API REST qui permettra...
        // ... d'interroger l'API
        // TODO : tmdb_movies et tmdb_persons ne servira qu'a vérifier qu'un film ou qu'une personne est déjà présent(e)
        // ... dans la base (persons et movies) mais on pourrait très bien s'en passer et interroger directement l'API
        // TODO : le travail à faire consiste à créer un second ETL (après celui d'hier) pour, via une recherche...
        //  ... de l'admin par id (ou titre / name), importer UN film...
        //  ... (avec toutes les informations relatives à ce film, y compris les personnes
        // TODO : Completer le TmdbClientRecupérer les personnes et les réalisateurs


        SpringApplication.run(CinemaSpringBootApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner movieDetail(TmdbClient tc) {
//        return args -> {
//            tc.getMovieByTmdbId(616);
//        };
//    }
}
