
package com.example.kanika.digitocracy.APIResponse.PollCategoryList;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("poll_category_list")
    @Expose
    private List<PollCategoryList_> pollCategoryList = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<PollCategoryList_> getPollCategoryList() {
        return pollCategoryList;
    }

    public void setPollCategoryList(List<PollCategoryList_> pollCategoryList) {
        this.pollCategoryList = pollCategoryList;
    }

}
