package com.rainy.service;


import com.rainy.model.User;

/**
 * Created by PC on 2017/7/26.
 */
public interface UserService {
    User queryByUserNameAndPassword(String userName,String password);
}
