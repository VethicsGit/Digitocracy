
package com.example.kanika.digitocracy.APIResponse.PollDetails_response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PollDetail {

    @SerializedName("poll_id")
    @Expose
    private String pollId;
    @SerializedName("poll_category_id")
    @Expose
    private String pollCategoryId;
    @SerializedName("poll_category_title")
    @Expose
    private String pollCategoryTitle;
    @SerializedName("poll_title")
    @Expose
    private String pollTitle;
    @SerializedName("start_date_time")
    @Expose
    private String startDateTime;
    @SerializedName("end_date_time")
    @Expose
    private String endDateTime;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("poll_vote_options")
    @Expose
    private List<PollVoteOption> pollVoteOptions = null;
    @SerializedName("total_poll_votes")
    @Expose
    private String totalPollVotes;
    @SerializedName("already_voted")
    @Expose
    private String alreadyVoted;
    @SerializedName("my_poll_vote_option_id")
    @Expose
    private String myPollVoteOptionId;

    public String getPollId() {
        return pollId;
    }

    public void setPollId(String pollId) {
        this.pollId = pollId;
    }

    public String getPollCategoryId() {
        return pollCategoryId;
    }

    public void setPollCategoryId(String pollCategoryId) {
        this.pollCategoryId = pollCategoryId;
    }

    public String getPollCategoryTitle() {
        return pollCategoryTitle;
    }

    public void setPollCategoryTitle(String pollCategoryTitle) {
        this.pollCategoryTitle = pollCategoryTitle;
    }

    public String getPollTitle() {
        return pollTitle;
    }

    public void setPollTitle(String pollTitle) {
        this.pollTitle = pollTitle;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public List<PollVoteOption> getPollVoteOptions() {
        return pollVoteOptions;
    }

    public void setPollVoteOptions(List<PollVoteOption> pollVoteOptions) {
        this.pollVoteOptions = pollVoteOptions;
    }

    public String getTotalPollVotes() {
        return totalPollVotes;
    }

    public void setTotalPollVotes(String totalPollVotes) {
        this.totalPollVotes = totalPollVotes;
    }

    public String getAlreadyVoted() {
        return alreadyVoted;
    }

    public void setAlreadyVoted(String alreadyVoted) {
        this.alreadyVoted = alreadyVoted;
    }

    public String getMyPollVoteOptionId() {
        return myPollVoteOptionId;
    }

    public void setMyPollVoteOptionId(String myPollVoteOptionId) {
        this.myPollVoteOptionId = myPollVoteOptionId;
    }

}
