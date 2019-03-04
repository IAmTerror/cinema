package fr.iat.cinema.web;

import fr.iat.cinema.dao.UserDao;
import fr.iat.cinema.model.Film;
import fr.iat.cinema.model.User;
import fr.iat.cinema.service.ImageManager;
import fr.iat.cinema.service.JpaUserService;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserDao userDao;

    @Autowired
    ImageManager imm;

    @Autowired
    Path path;

    @Autowired
    JpaUserService jpaUserService;

//    @GetMapping("/list")
//    public String list(Model model){
//        Iterable<User> users = userDao.findAllByOrderByIdAsc();
//        model.addAttribute("users", users);
//        return "user/list";
//    }

    @GetMapping("/list")
    public String list(Model model){
        Iterable<User> users = jpaUserService.findAllUsers();
        model.addAttribute("users", users);
        return "user/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id")long id, Model model){
//        model.addAttribute("user", userDao.findById(id).get());
        model.addAttribute("user", userDao.findById(id));
        return "user/detail";
    }

    @GetMapping("/mod/{id}")
    public String mod(@PathVariable("id")long id, Model model){
//        model.addAttribute("user", userDao.findById(id).get());
        model.addAttribute("user", userDao.findById(id));
        return "user/form";
    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("user", new User());
        return "user/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        userDao.deleteById(id);
        return "redirect:/user/list";
    }

    @PostMapping("/add")
    public String submit(@ModelAttribute User user){
        userDao.save(user);
        return "redirect:/user/list";
    }
}
