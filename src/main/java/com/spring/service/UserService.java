package com.spring.service;

import com.spring.model.User;

public interface UserService {
    User findUserProfileByJwt(String jwt) throws Exception;

    User findUserByEmail(String email) throws Exception;

    User findUserById(Long userId) throws Exception;

    User addUserProjectSize(User user, int number) throws Exception;
}
