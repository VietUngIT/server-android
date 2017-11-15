package com.vimensa.apis.handlers;

import com.vimensa.apis.responses._BaseResponse;
import com.vimensa.core.dao.UserData;
import com.vimensa.core.service.UserService;
import com.vimensa.core.serviceimp.UserServiceImp;
import com.vimensa.core.utils.Utils;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class RegisterHandler extends _BaseApiHandler {
    private static Logger logger = LoggerFactory.getLogger(LoginHandler.class.getName());
    private UserService userService = new UserServiceImp();
    @Override
    public _BaseResponse handle(HttpServerRequest request) throws Exception {
        String phone = request.getFormAttribute("phone");
        String password = request.getFormAttribute("password");
        String name = request.getFormAttribute("name");
        password = Utils.sha256(password.trim().toString());
        UserData userData = new UserData();
        userData.setPhone(phone);
        userData.setName(name);
        userData.setPassword(password);
        _BaseResponse response = userService.register(userData);
        return response;
    }
}
