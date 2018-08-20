
package com.example.kanika.digitocracy.APIResponse.PollDetails_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PollVoteOption {

    @SerializedName("poll_vote_option_id")
    @Expose
    private String pollVoteOptionId;
    @SerializedName("option")
    @Expose
    private String option;
    @SerializedName("vote_count")
    @Expose
    private String voteCount;

    public String getPollVoteOptionId() {
        return pollVoteOptionId;
    }

    public void setPollVoteOptionId(String pollVoteOptionId) {
        this.pollVoteOptionId = pollVoteOptionId;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(String voteCount) {
        this.voteCount = voteCount;
    }

}
