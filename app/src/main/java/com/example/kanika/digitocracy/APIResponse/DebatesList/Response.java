
package com.example.kanika.digitocracy.APIResponse.DebatesList;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("debate_list")
    @Expose
    private List<DebateList> debateList = null;
    @SerializedName("offset")
    @Expose
    private Integer offset;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DebateList> getDebateList() {
        return debateList;
    }

    public void setDebateList(List<DebateList> debateList) {
        this.debateList = debateList;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

}
