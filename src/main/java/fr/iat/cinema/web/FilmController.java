// ========== OLD CONTROLLER 1 ===================================================
//package fr.iat.cinema.web;
//
//import fr.iat.cinema.dao.FilmDao;
//import fr.iat.cinema.model.Film;
//import fr.iat.cinema.service.ImageManager;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//
//@Controller
//@RequestMapping(value = "/film")
//public class FilmController {
//
//    @Autowired
//    FilmDao reviewDao;
//
//    @Autowired
//    ImageManager imm;
//
//    @GetMapping("/list")
//    public String filmListe(Model model) {
//        model.addAttribute("films", reviewDao.getAll());
//        return "film/list";
//    }
//
//    @GetMapping("/detail/{id}")
//    public String film(Model model, @PathVariable("id") String id) {
//        long idPersonne = Long.parseLong(id);
//        model.addAttribute("film", reviewDao.getById(idPersonne));
//        return "film/detail";
//    }
//
//    @GetMapping("/modif/{id}")
//    public String filmModif(Model model, @PathVariable("id") String id) {
//        long idFilm = Long.parseLong(id);
//        model.addAttribute("film", reviewDao.getById(idFilm));
//        return "film/modif";
//    }
//
//    @GetMapping("/form")
//    public String filmForm(Model model) {
//        model.addAttribute("film", new Film());
//        return "film/form";
//    }
//
//    @PostMapping("/update")
//    public String filmUpdate(@ModelAttribute Film film){
//        reviewDao.save(film);
//        return "redirect:/film/list";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String filmDelete(@PathVariable("id") Long id){
//        reviewDao.delete(id);
//        return "redirect:/film/list";
//    }
//
//    @PostMapping("/add")
//    public String filmAdd(@ModelAttribute Film film){
//        reviewDao.save(film);
//        return "redirect:/film/list";
//    }
//
//    @PostMapping("/add")
//    public String submit(@RequestParam("poster") MultipartFile file, @ModelAttribute Film film){
//        if(file.getContentType().equalsIgnoreCase("image/jpeg")){
//            try {
//                imm.savePoster(film, file.getInputStream());
//            } catch (IOException ioe){
//                System.out.println("Erreur lecture : "+ioe.getMessage());
//            }
//        }
//        reviewDao.save(film);
//
//        return "redirect:/film/list";
//    }
//}

package fr.iat.cinema.web;

import fr.iat.cinema.dao.FilmDao;
import fr.iat.cinema.dao.PersonDao;
import fr.iat.cinema.dao.RoleDao;
import fr.iat.cinema.model.Film;
import fr.iat.cinema.service.ImageManager;
import fr.iat.cinema.service.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@Controller
@RequestMapping("/film")
public class FilmController {

    @Autowired
    FilmDao filmDao;

    @Autowired
    PersonDao personDao;

    @Autowired
    ImageManager imm;

    @Autowired
    Path path;

    @GetMapping("/list")
    public String list(Model model){
        Iterable<Film> films = filmDao.findAllByOrderByIdAsc();
        model.addAttribute("films", films);
        return "film/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id")long id, Model model){
        model.addAttribute("film", filmDao.findById(id).get());
        return "film/detail";
    }

    @GetMapping("/mod/{id}")
    public String mod(@PathVariable("id")long id, Model model){
        model.addAttribute("film", filmDao.findById(id).get());
        model.addAttribute("persons", personDao.findAll());
        return "film/form";
    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("film", new Film());
        model.addAttribute("persons", personDao.findAll());
        return "film/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        filmDao.deleteById(id);
        return "redirect:/film/list";
    }

    @PostMapping("/add")
    public String submit(@RequestParam("poster")MultipartFile file, @ModelAttribute Film film){
        if(file.getContentType().equalsIgnoreCase("image/jpeg")){
            try {
                imm.savePoster(film, file.getInputStream());
            } catch (IOException ioe){
                System.out.println("Erreur lecture : "+ioe.getMessage());
            }
        }
        filmDao.save(film);

        return "redirect:/film/list";
    }

    // ========== AFFICHES =======================================================
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
}
