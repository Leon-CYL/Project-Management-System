package com.spring.service;

import com.spring.config.JwtProvider;
import com.spring.model.User;
import com.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserProfileByJwt(String jwt) throws Exception {
        String email = JwtProvider.getEmailFromToken(jwt);
        return findUserByEmail(email);
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new Exception("User not found!");
        }
        return user;
    }

    @Override
    public User findUserById(Long userId) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new Exception("User not found!");
        }
        return user.get();
    }

    @Override
    public User addUserProjectSize(User user, int number) throws Exception {
        user.setProjectSize(user.getProjectSize() + number);
        if (user.getProjectSize() < 0) {
            throw new Exception("Project size cannot be negative!");
        }
        return userRepository.save(user);
    }
}
