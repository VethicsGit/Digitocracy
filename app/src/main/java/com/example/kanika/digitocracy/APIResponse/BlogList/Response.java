
package com.example.kanika.digitocracy.APIResponse.BlogList;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("blog_list")
    @Expose
    private List<BlogList> blogList = null;
    @SerializedName("offset")
    @Expose
    private Integer offset;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<BlogList> getBlogList() {
        return blogList;
    }

    public void setBlogList(List<BlogList> blogList) {
        this.blogList = blogList;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

}
