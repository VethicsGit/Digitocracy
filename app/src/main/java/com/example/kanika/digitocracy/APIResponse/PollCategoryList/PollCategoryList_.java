
package com.example.kanika.digitocracy.APIResponse.PollCategoryList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PollCategoryList_ {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("poll_category_id")
    @Expose
    private String pollCategoryId;
    @SerializedName("poll_category_icon")
    @Expose
    private String pollCategoryIcon;
    @SerializedName("poll_category_title")
    @Expose
    private String pollCategoryTitle;
    @SerializedName("poll_category_description")
    @Expose
    private String pollCategoryDescription;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPollCategoryId() {
        return pollCategoryId;
    }

    public void setPollCategoryId(String pollCategoryId) {
        this.pollCategoryId = pollCategoryId;
    }

    public String getPollCategoryIcon() {
        return pollCategoryIcon;
    }

    public void setPollCategoryIcon(String pollCategoryIcon) {
        this.pollCategoryIcon = pollCategoryIcon;
    }

    public String getPollCategoryTitle() {
        return pollCategoryTitle;
    }

    public void setPollCategoryTitle(String pollCategoryTitle) {
        this.pollCategoryTitle = pollCategoryTitle;
    }

    public String getPollCategoryDescription() {
        return pollCategoryDescription;
    }

    public void setPollCategoryDescription(String pollCategoryDescription) {
        this.pollCategoryDescription = pollCategoryDescription;
    }

}
