
package com.example.kanika.digitocracy.login;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("response")
    @Expose
    private List<com.example.kanika.digitocracy.login.Responsee> response = null;

    public List<com.example.kanika.digitocracy.login.Responsee> getResponse() {
        return response;
    }

    public void setResponse(List<com.example.kanika.digitocracy.login.Responsee> response) {
        this.response = response;
    }

}
