package com.softSquared.mangoplate.src.main.models;

import com.google.gson.annotations.SerializedName;

public class SignInJwtToken {

    @SerializedName("at")
    String at;


    public SignInJwtToken(String at) {
        this.at = at;
    }

    public String getJwt() {
        return at;
    }
}
