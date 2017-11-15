package com.vimensa.apis.responses;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.vimensa.core.dao.InfoNews;
import lombok.Data;

import java.util.ArrayList;

@Data
public class GetNewsResponse extends  _BaseResponse {
    private ArrayList<InfoNews> array;
    @Override
    public String toJonString() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("e", getError());
        if(array != null){
            JsonArray jsonArray = new JsonArray();
            for(int i=0;i<array.size();i++){
                jsonArray.add((array.get(i)).toJsonObject());
            }
            jsonObject.add("data",jsonArray);
        }
        return jsonObject.toString();
    }
}
