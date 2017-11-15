package com.vimensa.apis.responses;

import com.google.gson.JsonObject;
import com.vimensa.core.dao.UserData;
import lombok.Data;

@Data
public class UserResponse extends _BaseResponse {
    private UserData userData;

    @Override
    public String toJonString() {
        JsonObject json = new JsonObject();
        json.addProperty("e", getError());
        JsonObject userInfo = new JsonObject();
        if (userData != null) {
            userInfo.addProperty("phone",userData.getPhone());
            userInfo.addProperty("name",userData.getName());
            json.add("aInfo", userInfo);
        }
        return json.toString();
    }
}
