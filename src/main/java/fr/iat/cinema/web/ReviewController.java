package fr.iat.cinema.web;

import fr.iat.cinema.dao.ReviewDao;
import fr.iat.cinema.model.Review;
import fr.iat.cinema.service.ImageManager;
import fr.iat.cinema.service.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    ReviewDao reviewDao;

    @Autowired
    ImageManager imm;

    @Autowired
    Path path;

    @GetMapping("/list")
    public String list(Model model){
        Iterable<Review> reviews = reviewDao.findAllByOrderByIdAsc();
        model.addAttribute("reviews", reviews);
        return "reviewDao/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id")long id, Model model){
        model.addAttribute("review", reviewDao.findById(id).get());
        return "reviewDao/detail";
    }

    @GetMapping("/mod/{id}")
    public String mod(@PathVariable("id")long id, Model model){
        model.addAttribute("review", reviewDao.findById(id).get());
        return "reviewDao/form";
    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("review", new Review());
        return "reviewDao/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        reviewDao.deleteById(id);
        return "redirect:/review/list";
    }

    @PostMapping("/add")
    public String submit(@ModelAttribute Review review){
        reviewDao.save(review);
        return "redirect:/review/list";
    }
}
