package com.example.instagramalex;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.sql.Date;

@ParseClassName("Post")
public class Post extends ParseObject {
    public static final String KEY_DESCRIPTION = "KEY_DESCRIPTION";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_USER = "user";
    public static final String KEY_CREATED_AT = "createdAt";

    public Date getcreatedAt() {
        return (Date) getDate(KEY_CREATED_AT);
    }

    public String getDescrption() {
        return getString(KEY_DESCRIPTION);
    }

    public void setDescrption(String descrption) {
        put("KEY_DESCRIPTION", descrption);
    }

//    public void setKeyCreatedAt(String createdAt) {
//        put("KEY_CREATED_AT", createdAt);
//    }

    public ParseFile getImage(){
        return getParseFile(KEY_IMAGE);
    }

    public void setImage(ParseFile parseFile){
        put(KEY_IMAGE, parseFile);
    }

    public ParseUser getUser(){
        return getParseUser(KEY_USER);
    }

    public void setUSer(ParseUser parseUser){
        put(KEY_USER, parseUser);
    }
}