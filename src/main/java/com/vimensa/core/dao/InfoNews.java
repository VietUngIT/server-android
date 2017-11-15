package com.vimensa.core.dao;

import com.google.gson.JsonObject;
import lombok.Data;

import java.io.Serializable;

@Data
public class InfoNews implements Serializable {
    private int id;
    private String title;
    private String content;
    private String image;
    private String author;
    private String createTime;
    public String toJsonString(){
        return toJsonObject().toString();
    }
    public JsonObject toJsonObject(){
        JsonObject json=new JsonObject();
        json.addProperty("id",getId());
        json.addProperty("title",getTitle());
        json.addProperty("content",getContent());
        json.addProperty("img",getImage());
        json.addProperty("author",getAuthor());
        json.addProperty("time_create",getCreateTime());
        return json;
    }
}
