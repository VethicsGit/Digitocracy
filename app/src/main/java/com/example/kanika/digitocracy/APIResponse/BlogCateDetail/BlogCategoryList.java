
package com.example.kanika.digitocracy.APIResponse.BlogCateDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BlogCategoryList {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("blog_category_id")
    @Expose
    private String blogCategoryId;
    @SerializedName("blog_category_title")
    @Expose
    private String blogCategoryTitle;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBlogCategoryId() {
        return blogCategoryId;
    }

    public void setBlogCategoryId(String blogCategoryId) {
        this.blogCategoryId = blogCategoryId;
    }

    public String getBlogCategoryTitle() {
        return blogCategoryTitle;
    }

    public void setBlogCategoryTitle(String blogCategoryTitle) {
        this.blogCategoryTitle = blogCategoryTitle;
    }

}
