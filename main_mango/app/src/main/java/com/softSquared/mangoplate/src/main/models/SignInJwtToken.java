package com.softSquared.mangoplate.src.main.models;

import com.google.gson.annotations.SerializedName;

public class SignInJwtToken {

    @SerializedName("type")
    String type;
    @SerializedName("at")
    String jwt;


    public SignInJwtToken(String type,String jwt) {
        this.jwt = jwt;
        this.type = type;
    }

    public String getJwt() {
        return jwt;
    }
}
