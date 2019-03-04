package fr.iat.cinema.web;

import fr.iat.cinema.dao.UserDao;
import fr.iat.cinema.model.User;
import fr.iat.cinema.service.JpaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    UserDao userDao;

    @Autowired
    JpaUserService jpaUserService;

    @GetMapping("/recovery/{id}")
    public String recovery(Model model, @PathVariable("id") long id){
        model.addAttribute("user", jpaUserService.findByUserId(id));
        model.addAttribute("idUser", id);
        return "recovery";
    }

    @GetMapping("modPasswordByAdmin")
    public String modPasswordByAdmin(@RequestParam("id") long id, @RequestParam("newPassword") String newPassword, @RequestParam("confirmPassword") String confirmPassword) {
        if (confirmPassword.equals(newPassword)) {
            User user = jpaUserService.findByUserId(id);
            user.setPassword(newPassword);
            jpaUserService.save(user);
        }
        return "redirect:/film/list";
    }

    @GetMapping("modPasswordByUser")
    public String modPasswordByUser(@RequestParam("id") long id, @RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword, @RequestParam("confirmPassword") String confirmPassword) {
        if (confirmPassword.equals(newPassword)) {

            User user = jpaUserService.findByUserId(id);
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            if (passwordEncoder.matches(oldPassword, user.getPassword())) {
                user.setPassword(newPassword);
                jpaUserService.save(user);
            }
        }
        return "redirect:/index";
    }
}
