
package com.example.kanika.digitocracy.APIResponse.BlogList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BlogList {

    @SerializedName("blog_id")
    @Expose
    private String blogId;
    @SerializedName("blog_category_id")
    @Expose
    private String blogCategoryId;
    @SerializedName("blog_category_title")
    @Expose
    private String blogCategoryTitle;
    @SerializedName("cover_image")
    @Expose
    private String coverImage;
    @SerializedName("blog_title")
    @Expose
    private String blogTitle;
    @SerializedName("short_description")
    @Expose
    private String shortDescription;
    @SerializedName("created_at")
    @Expose
    private String createdAt;

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId;
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

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
