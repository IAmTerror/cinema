//package fr.iat.tpcinema.dao;
//
//import fr.iat.tpcinema.model.Film;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//import java.util.List;
//
///**
// * Created by fred on 03/02/2016.
// *
// * @author student : IAmTerror
// */
//
//@Component
//public class FilmDao {
//
//    PersonneDao personneDao = new PersonneDao();
//    private List<Film> films;
//
//    public FilmDao() {
//
//        films = Arrays.asList(
//                new Film(1,
//                        "Le juge et l'assassin",
//                        3.8,
//                        "f0001.jpg",
//                        "Fin du XIXème, " +
//                                "Joseph Bouvier est révoqué de l'armée à cause de ses excès de violence. Suite à ce renvoi, l'homme " +
//                                "s'attaque à sa fiancée et tente de se suicider, en vain. Après un séjour en hôpital psychiatrique, " +
//                                "Joseph ressort de cet endroit encore plus enragé et décide de se venger sur toutes les personnes qui " +
//                                "croiseront son chemin en Ardèche. Non loin de là, le juge Rousseau, passionné par l'affaire, prend " +
//                                "part à l'investigation et se met sur les traces de Bouvier. Bien décidé à le mettre sous les verrous, " +
//                                "c'est le début d'une chasse à l'homme...",
//                        personneDao.getById(19)),
//                new Film(2,
//                        "La guerre des étoiles",
//                        4.4,
//                        "f0002.jpg",
//                        "Il y a bien longtemps, dans une galaxie très lointaine... La guerre civile fait rage entre " +
//                                "l'Empire galactique et l'Alliance rebelle. Capturée par les troupes de choc de l'Empereur " +
//                                "menées par le sombre et impitoyable Dark Vador, la princesse Leia Organa dissimule les plans " +
//                                "de l'Etoile Noire, une station spatiale invulnérable, à son droïde R2-D2 avec pour mission de " +
//                                "les remettre au Jedi Obi-Wan Kenobi. Accompagné de son fidèle compagnon, le droïde de " +
//                                "protocole C-3PO, R2-D2 s'échoue sur la planète Tatooine et termine sa quête chez le jeune " +
//                                "Luke Skywalker. Rêvant de devenir pilote mais confiné aux travaux de la ferme, ce dernier " +
//                                "se lance à la recherche de ce mystérieux Obi-Wan Kenobi, devenu ermite au coeur des montagnes " +
//                                "désertiques de Tatooine...",
//                        personneDao.getById(20)),
//                new Film(3,
//                        "Pretty woman",
//                        3.5,
//                        "f0003.jpg",
//                        "Edward Lewis, homme d'affaires performant, rencontre par hasard Vivian Ward, beaute fatale " +
//                                "qui arpente chaque nuit les trottoirs d'Hollywood Boulevard. La jeune femme ne fera qu'une " +
//                                "bouchee du brillant PDG.",
//                        personneDao.getById(21)),
//                new Film(4,
//                        "BEN-HUR",
//                        4.0,
//                        "f0006.jpg",
//                        "Judas Ben-Hur, " +
//                                "prince de Judée, retrouve son ami d'enfance Messala, venu prendre la tête de la garnison de " +
//                                "Jérusalem. Mais leur amitié ne peut résister à leurs caractères différents. Alors qu'une " +
//                                "pierre tombe du balcon de la maison familiale de Ben-Hur, manquant de tuer le gouverneur " +
//                                "qui paradait plus bas, Messala trahit son ami qu'il sait innocent en l'envoyant aux galères " +
//                                "et en jetant en prison sa mère et sa sœur. Ben-Hur jure alors de reconquérir sa liberté " +
//                                "et prépare sa vengeance.",
//                        personneDao.getById(22)),
//                new Film(5,
//                        "E.T. l'extra-terrestre",
//                        4.2,
//                        "f0005.jpg",
//                        "Une soucoupe volante atterrit en pleine nuit près de Los Angeles. Quelques extraterrestres, " +
//                                "envoyés sur Terre en mission d'exploration botanique, sortent de l'engin, mais un des leurs " +
//                                "s'aventure au-delà de la clairière où se trouve la navette. Celui-ci se dirige alors vers " +
//                                "la ville. C'est sa première découverte de la civilisation humaine. Bientôt traquée par des " +
//                                "militaires et abandonnée par les siens, cette petite créature apeurée se nommant E.T. se " +
//                                "réfugie dans une résidence de banlieue. Elliot, un garçon de dix ans, le découvre et lui " +
//                                "construit un abri dans son armoire. Rapprochés par un échange télépathique, les deux êtres " +
//                                "ne tardent pas à devenir amis. Aidé par sa soeur Gertie et son frère aîné Michael, Elliot " +
//                                "va alors tenter de garder la présence d'E.T. secrète.",
//                        personneDao.getById(23)),
//                new Film(6,
//                        "Légendes d'automne",
//                        3.9,
//                        "f0004.jpg",
//                        "Au cœur des contrées sauvages du Montana, trois frères, Alfred, Tristan et Samuel sont " +
//                                "élevés par leur père, le colonel William Ludlow et ses amis indiens. Un jour, Samuel, " +
//                                "le plus fragile, présente sa ravissante fiancée, Susannah à sa famille. Les trois jeunes " +
//                                "hommes s’engagent dans la première Guerre Mondiale et Samuel décède sur le champ de " +
//                                "bataille. A leur retour, Tristan et Alfred se battent pour conquérir le cœur de " +
//                                "la belle veuve.",
//                        personneDao.getById(24))
//        );
//    }
//
//    public List<Film> films() {
//        return films;
//    }
//
//    public Film getById(int id) {
//        Film film = null;
//        for (Film f : films) {
//            if (f.getId() == id) {
//                film = f;
//                break;
//            }
//        }
//        return film;
//    }
//}
