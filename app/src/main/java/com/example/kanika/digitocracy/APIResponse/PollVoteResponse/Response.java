
package com.example.kanika.digitocracy.APIResponse.PollVoteResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("response_msg")
    @Expose
    private String responseMsg;
    @SerializedName("total_poll_votes")
    @Expose
    private String totalPollVotes;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    public String getTotalPollVotes() {
        return totalPollVotes;
    }

    public void setTotalPollVotes(String totalPollVotes) {
        this.totalPollVotes = totalPollVotes;
    }

}
