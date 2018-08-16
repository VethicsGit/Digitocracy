
package com.example.kanika.digitocracy.signup;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Responsesignup {

    @SerializedName("response")
    @Expose
    private List<Response_> response = null;

    public List<Response_> getResponse() {
        return response;
    }

    public void setResponse(List<Response_> response) {
        this.response = response;
    }

}
