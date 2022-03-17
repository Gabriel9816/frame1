package br.edu.ifms.frame1.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public void delete(User user){
        userRepository.delete(user);

    }
    public Optional<User> findById(UUID id){
        return userRepository.findById(id);
    }

    public  User get(UUID userID){
        Optional<User> user = this.userRepository.findById(userID);
        if(user.isPresent())
            return user.get();
        return null;
    }
    
    // public Optional<User> get(UUID userID){
    //     return userRepository.findById(userID);
    // }

}
