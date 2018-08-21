
package com.example.kanika.digitocracy.APIResponse.DebatesList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DebateParticipant {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("profile_pic")
    @Expose
    private String profilePic;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("member_since")
    @Expose
    private String memberSince;
    @SerializedName("profile_pic_thumb")
    @Expose
    private String profilePicThumb;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMemberSince() {
        return memberSince;
    }

    public void setMemberSince(String memberSince) {
        this.memberSince = memberSince;
    }

    public String getProfilePicThumb() {
        return profilePicThumb;
    }

    public void setProfilePicThumb(String profilePicThumb) {
        this.profilePicThumb = profilePicThumb;
    }

}
