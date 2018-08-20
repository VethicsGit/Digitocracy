
package com.example.kanika.digitocracy.APIResponse.Termsofuse;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import retrofit2.Response;

public class Termsofuse {

    @SerializedName("response")
    @Expose
    private List<Response> response = null;

    public List<com.example.kanika.digitocracy.APIResponse.Termsofuse.Response> getResponse() {
        return response;
    }

    public void setResponse(List<Response> response) {
        this.response = response;
    }

}
