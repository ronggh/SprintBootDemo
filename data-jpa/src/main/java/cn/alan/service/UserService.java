package cn.alan.service;


import cn.alan.entity.User;
import cn.alan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User insertUser(User user){
        User save = userRepository.save(user);
        return save;
    }

    public User getUser(Integer id){
        User user = userRepository.findById(id).get();
        return user;
    }

//    public List<User> searchByName(String name){
//        return userRepository.searchByName(name);
//    }
}
