
package com.example.kanika.digitocracy.APIResponse.PrivacyPolice;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import retrofit2.Response;

public class PrivacyPolicy {

    @SerializedName("response")
    @Expose
    private List<com.example.kanika.digitocracy.APIResponse.PrivacyPolice.Response> response = null;


    public List<com.example.kanika.digitocracy.APIResponse.PrivacyPolice.Response> getResponse() {
        return response;
    }

    public void setResponse(List<com.example.kanika.digitocracy.APIResponse.PrivacyPolice.Response> response) {
        this.response = response;
    }

}
