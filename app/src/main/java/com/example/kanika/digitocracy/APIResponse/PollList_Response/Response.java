
package com.example.kanika.digitocracy.APIResponse.PollList_Response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("poll_list")
    @Expose
    private List<PollList> pollList = null;
    @SerializedName("offset")
    @Expose
    private Integer offset;
    @SerializedName("type")
    @Expose
    private String type;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<PollList> getPollList() {
        return pollList;
    }

    public void setPollList(List<PollList> pollList) {
        this.pollList = pollList;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
