
package com.example.kanika.digitocracy.APIResponse.Termsofuse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("terms_and_condition")
    @Expose
    private String termsAndCondition;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTermsAndCondition() {
        return termsAndCondition;
    }

    public void setTermsAndCondition(String termsAndCondition) {
        this.termsAndCondition = termsAndCondition;
    }

}
