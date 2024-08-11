package com.example.jpaexercise.Service;

import com.example.jpaexercise.Model.User;
import com.example.jpaexercise.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getUsers () {
        return userRepository.findAll();
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public boolean updateUser(Integer id,User user) {
        User oldUser = userRepository.getById(id);
        if (oldUser == null) {
            return false;
        }
        oldUser.setUsername(user.getUsername());
        oldUser.setPassword(user.getPassword());
        oldUser.setEmail(user.getEmail());
        oldUser.setRole(user.getRole());
        oldUser.setBalance(user.getBalance());

        userRepository.save(oldUser);
        return true;
    }

    public boolean deleteUser(Integer id) {
        User user = userRepository.getById(id);
        if (user == null) {
            return false;
        }
        userRepository.delete(user);
        return true;
    }



}
