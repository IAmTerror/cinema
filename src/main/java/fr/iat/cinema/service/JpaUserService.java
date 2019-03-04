package fr.iat.cinema.service;

import fr.iat.cinema.dao.GroupDao;
import fr.iat.cinema.dao.UserDao;
import fr.iat.cinema.model.Groups;
import fr.iat.cinema.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;


@Service
public class JpaUserService {
    private UserDao userDao;
    private GroupDao groupDao;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }

    @Autowired
    public void setGroupDao(GroupDao groupDao){
        this.groupDao = groupDao;
    }

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder){
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void save(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setGroups(new HashSet<>(groupDao.findAll()));
        userDao.save(user);
    }

    public User findByUserName(String userName){
        return userDao.findBySurname(userName);
    }

    public List<User> findAllUsers(){
        return userDao.findAll();
    }

    public User findByUserId(long id){
        return userDao.findById(id);
    }

}
