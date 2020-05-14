package com.softSquared.mangoplate.src.main.models;

import com.google.gson.annotations.SerializedName;


public class SignInKakaoTalk {

    @SerializedName("id")
    String id;
    @SerializedName("name")
    String  name;

    public SignInKakaoTalk(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
