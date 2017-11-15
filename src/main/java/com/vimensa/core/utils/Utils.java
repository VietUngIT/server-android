package com.vimensa.core.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.security.MessageDigest;

public class Utils {
    private static final JsonParser jsonParser = new JsonParser();

    public static void main(String[] args){
//        String src = "password";
//        System.out.println(sha256(src));
    }

    public static String sha256(String src){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(src.getBytes());
            byte byteData[] = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        }catch (Exception e){
            return "";
        }
    }

    private static Gson gson = new Gson();
    public static JsonObject toJsonObject(String json){
        try {
            return gson.fromJson(json, JsonObject.class);
        }catch (Exception e){
            return null;
        }
    }

    public static String toMd5(String src){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(src.getBytes());
            byte byteData[] = md.digest();

            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        }catch (Exception e){
            return "";
        }
    }

    public static String sha1(String src){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(src.getBytes());
            byte byteData[] = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        }catch (Exception e){
            return "";
        }
    }
}
