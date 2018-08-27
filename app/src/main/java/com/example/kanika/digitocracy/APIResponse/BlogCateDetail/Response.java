
package com.example.kanika.digitocracy.APIResponse.BlogCateDetail;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("blog_category_list")
    @Expose
    private List<BlogCategoryList> blogCategoryList = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<BlogCategoryList> getBlogCategoryList() {
        return blogCategoryList;
    }

    public void setBlogCategoryList(List<BlogCategoryList> blogCategoryList) {
        this.blogCategoryList = blogCategoryList;
    }

}
