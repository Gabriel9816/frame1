package br.edu.ifms.frame1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifms.frame1.model.User;
import br.edu.ifms.frame1.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public List<User> getUsers() {

        return this.userRepository.findAll();
    }

    public void saveUser(User user) {
        this.userRepository.save(user);
    }
}
