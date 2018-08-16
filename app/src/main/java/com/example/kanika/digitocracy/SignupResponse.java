package com.example.kanika.digitocracy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class SignupResponse {


    @SerializedName("response")
    @Expose
    private String response;


    public String getResponse() {
        return response;
    }
    public void setResponse(String response) {
        this.response = response;
    }





}
