
package com.example.kanika.digitocracy.APIResponse.PollDetails_response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("poll_details")
    @Expose
    private List<PollDetail> pollDetails = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<PollDetail> getPollDetails() {
        return pollDetails;
    }

    public void setPollDetails(List<PollDetail> pollDetails) {
        this.pollDetails = pollDetails;
    }

}
