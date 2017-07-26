package com.rainy.service.impl;

import com.rainy.model.User;
import com.rainy.repository.UserRepository;
import com.rainy.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by PC on 2017/7/26.
 */
@Transactional
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public User queryByUserNameAndPassword(String userName, String password) {
        return userRepository.queryByUserNameAndPassword(userName,password);
    }
}
