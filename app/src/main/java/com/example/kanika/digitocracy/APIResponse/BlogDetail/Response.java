
package com.example.kanika.digitocracy.APIResponse.BlogDetail;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("blog_details")
    @Expose
    private List<BlogDetail_> blogDetails = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<BlogDetail_> getBlogDetails() {
        return blogDetails;
    }

    public void setBlogDetails(List<BlogDetail_> blogDetails) {
        this.blogDetails = blogDetails;
    }

}
