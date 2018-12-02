package fr.iat.tpcinema.web;

import fr.iat.tpcinema.dao.FilmsDao;
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

@Controller
public class MainController {

    @Autowired
    FilmsDao filmsDao = new FilmsDao();

    @Autowired
    private Path path;

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("nom", "Cyril");
        model.addAttribute("films", filmsDao.films());
        return "index";
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
    public String detail(Model model, @PathVariable("id") String id) {
        Integer idFilm = Integer.parseInt(id);
        model.addAttribute("film", filmsDao.getById(idFilm));
        return "detail";
    }

    // TODO : deporter le nom du folder dans application.properties
    // Merci Patrick et Karl, overthinking de ma part...
    @GetMapping("/affiches/{id}")
    public void affiche (HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) throws IOException {

//        String affichesPath="C:\\Users\\cyril\\OUTER HEAVEN\\CDA\\varni\\tp-springboot\\sources\\affiches\\";
        String filename = path.getAffiche() + id;

        // ============ UTILITAIRE POUR IMPORTER DES IMAGES A PARTIR D'UN FOLDER EXTERNE A L'APPLICATION ============ //
        String mime = request.getServletContext ().getMimeType (filename);
        if (mime == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        response.setContentType(mime);
        File file = new File(filename);
        response.setContentLength((int)file.length());
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
