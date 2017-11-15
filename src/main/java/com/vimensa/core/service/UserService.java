package com.vimensa.core.service;

import com.vimensa.apis.responses.UserResponse;
import com.vimensa.core.dao.UserData;

import java.sql.SQLException;

public interface UserService {
    public UserResponse login(UserData userData) throws SQLException;
    public UserResponse register(UserData userData)  throws SQLException;
}
