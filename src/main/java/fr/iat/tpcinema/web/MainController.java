package fr.iat.tpcinema.web;

import fr.iat.tpcinema.dao.FilmDao;
import fr.iat.tpcinema.dao.PersonneDao;
import fr.iat.tpcinema.dao.RoleDao;
import fr.iat.tpcinema.service.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

//TODO séparer les acteurs des réalisateurs en typant les personnes, conditionner les controlleurs en fonction du type

@Controller
public class MainController {

    @Autowired
    FilmDao filmDao = new FilmDao();

    @Autowired
    PersonneDao personneDao = new PersonneDao();

    @Autowired
    RoleDao roleDao;

    @Autowired
    private Path path;

    @GetMapping("/")
    public String main(Model model) {
//        model.addAttribute("nom", "Cyril");
        model.addAttribute("films", filmDao.films());
        return "index";
    }

    @GetMapping("/liste-acteurs")
    public String listeActeurs(Model model) {
        model.addAttribute("personnes", personneDao.personnes());
        return "liste-acteurs";
    }

    // Version avec @RequestParam
//    @GetMapping("/film")
//    public String detail(Model model, @RequestParam("id") String id) {
//        Integer idFilm = Integer.parseInt(id);
//        model.addAttribute("film", filmsDao.getById(idFilm));
//        return "detail";
//    }

    // Version avec @PathVariable
    @GetMapping("/film/{id}")
    public String film(Model model, @PathVariable("id") String id) {
        int idFilm = Integer.parseInt(id);
        model.addAttribute("film", filmDao.getById(idFilm));
        return "film";
    }

    @GetMapping("/acteur/{id}")
    public String acteur(Model model, @PathVariable("id") String id) {
        int idActeur = Integer.parseInt(id);
        model.addAttribute("personne", personneDao.getById(idActeur));
        return "acteur";
    }

    // TODO : factoriser la gestion des paths pour les images
    @GetMapping("/affiches/{id}")
    public void affiche(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) throws IOException {

//        String affichesPath="C:\\Users\\cyril\\OUTER HEAVEN\\CDA\\varni\\tp-springboot\\sources\\affiches\\";
        String filename = path.getAffiche() + id;

        // ============ UTILITAIRE POUR IMPORTER DES IMAGES A PARTIR D'UN FOLDER EXTERNE A L'APPLICATION ============ //
        String mime = request.getServletContext().getMimeType(filename);
        if (mime == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        response.setContentType(mime);
        File file = new File(filename);
        response.setContentLength((int) file.length());
        FileInputStream in = new FileInputStream(file);
        OutputStream out = response.getOutputStream();
        byte[] buf = new byte[1024];
        int count = 0;
        while ((count = in.read(buf)) >= 0) {
            out.write(buf, 0, count);
        }
        out.close();
        in.close();
    }

    @GetMapping("/personnes/{id}")
    public void personne(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) throws IOException {

        String filename = path.getPersonne() + id;

        // ============ UTILITAIRE POUR IMPORTER DES IMAGES A PARTIR D'UN FOLDER EXTERNE A L'APPLICATION ============ //
        String mime = request.getServletContext().getMimeType(filename);
        if (mime == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        response.setContentType(mime);
        File file = new File(filename);
        response.setContentLength((int) file.length());
        FileInputStream in = new FileInputStream(file);
        OutputStream out = response.getOutputStream();
        byte[] buf = new byte[1024];
        int count = 0;
        while ((count = in.read(buf)) >= 0) {
            out.write(buf, 0, count);
        }
        out.close();
        in.close();
    }
}
