package com.vimensa.core.dao;

import com.google.gson.JsonObject;
import lombok.Data;

import java.io.Serializable;
import java.util.Calendar;

@Data
public class UserData implements Serializable {
    private int id;
    private String phone;
    private String password;
    private String name;
    private Calendar timeCreated;
    public String toJsonString(){
        return toJsonObject().toString();
    }
    public JsonObject toJsonObject(){
        JsonObject json=new JsonObject();
        json.addProperty("id",getId());
        json.addProperty("phone",getPhone());
        json.addProperty("name",getName());
        return json;
    }
}
