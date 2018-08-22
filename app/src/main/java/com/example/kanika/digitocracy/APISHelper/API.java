package com.example.kanika.digitocracy.APISHelper;

import com.example.kanika.digitocracy.APIResponse.Aboutus.Aboutus;
import com.example.kanika.digitocracy.APIResponse.BlogList.BlogListResponse;
import com.example.kanika.digitocracy.APIResponse.DebatesList.Debates;
import com.example.kanika.digitocracy.APIResponse.ForgotPass.ForgotpassResponse;
import com.example.kanika.digitocracy.APIResponse.PollCategoryList.PollCategoryList;
import com.example.kanika.digitocracy.APIResponse.PollDetails_response.PollDetail;
import com.example.kanika.digitocracy.APIResponse.PollDetails_response.PollDetailsResponse;
import com.example.kanika.digitocracy.APIResponse.PollList_Response.PollListResponse;
import com.example.kanika.digitocracy.APIResponse.PollVoteResponse.PollVoteResponse;
import com.example.kanika.digitocracy.APIResponse.PrivacyPolice.PrivacyPolicy;

import com.example.kanika.digitocracy.APIResponse.Termsofuse.Termsofuse;
import com.example.kanika.digitocracy.APIResponse.UpdateLocation.UpdateLocationResponse;
import com.example.kanika.digitocracy.location.LocationResponse;
import com.example.kanika.digitocracy.APIResponse.login.LoginResponse;
import com.example.kanika.digitocracy.APIResponse.signup.Responsesignup;

import okhttp3.internal.http.RealResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface API {

    @FormUrlEncoded
    @POST("Signup")
    Call<Responsesignup> Signup(@Field("name") String name, @Field("email") String email, @Field("password") String password, @Field("gender") String gender);

    @FormUrlEncoded
    @POST("Privacy_Policy")
    Call<PrivacyPolicy> Privacy_Policy(@Field("name") String name);

    @POST("About_US")
    Call<Aboutus> About_Us();

    @POST("Terms_and_condition")
    Call<Termsofuse> Terms_and_condition();

    @POST("Resend_verification_email")
    Call<RealResponseBody> Resend_verification_email(@Field("email") String email);

    @FormUrlEncoded
    @POST("signin?")
    Call<LoginResponse> Sigin(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded

    @POST("Forgot_Password")
    Call<ForgotpassResponse> Forgot_Password(@Field("email") String email);

    @POST("Change_Password")
    Call<RealResponseBody> Change_Password(@Field("user_id") String user_id, @Field("password") String password);

    @POST("Uadated_device_token")
    Call<RealResponseBody> Uadated_device_token(@Field("user_id") String user_id, @Field("device_type") String device_type, @Field("device_token") String device_token);

    @POST("Logout")
    Call<RealResponseBody> Logout(@Field("user_id") String user_id, @Field("device_token") String device_token);

    @POST("Contact_Us")
    Call<RealResponseBody> Contact_Us(@Field("user_id") String user_id, @Field("front_email") String front_email, @Field("subject") String subject, @Field("message") String message, @Field("phone_number") String phone_number, @Field("From_name") String From_name);

    @FormUrlEncoded
    @POST("User_profile")
    Call<RealResponseBody> User_profile(@Field("user_id") String user_id);

    @POST("Upadate_user_profile")
    Call<LocationResponse> Upadate_user_profile(@Field("user_id") String user_id, @Field("name") String name, @Field("gender") String gender, @Field("notification_status") String notification_status, @Field("Profile_Pic") String profile_pic);

    @FormUrlEncoded
    @POST("update_location")
    Call<UpdateLocationResponse> Upadate_location(@Field("user_id") String user_id, @Field("lat") String lat, @Field("lng") String lng, @Field("location_address") String location_address, @Header("Authorization") String token

    );

    @FormUrlEncoded
    @POST("Poll_category_list")
    Call<PollCategoryList> Poll_category_list(@Field("user_id") String user_id, @Header("Authorization") String authorization);

    @POST("blog_category_list")
    Call<RealResponseBody> bolg_category_list(@Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("blog_list")
    Call<BlogListResponse> blog_list(@Field("user_id") String user_id, @Field("offset") int offset, @Field("blog_category_id") String blog_category_id, @Header("Authorization") String token);

    @POST("blog_details")
    Call<RealResponseBody> blog_details(@Field("user_id") String user_id, @Field("blog_is") String blog_is);

    @FormUrlEncoded
    @POST("Poll_list")
    Call<PollListResponse> Poll_list(@Field("user_id") String user_id,
                                     @Field("offset") int offset,
                                     @Field("type") String type,
                                     @Field("poll_category_id") int poll_category_id,
                                     @Header("Authorization") String token);

    @FormUrlEncoded
    @POST("send_vote_on_poll")
    Call<PollVoteResponse> send_vote_on_poll(@Field("user_id") String user_id,
                                             @Field("poll_id") String poll_id,
                                             @Field("poll_option_id") String poll_option_id,
                                             @Header("Authorization") String token);

    @FormUrlEncoded
    @POST("poll_details")
    Call<PollDetailsResponse> poll_details(@Field("user_id") String user_id,
                                           @Field("poll_id") int poll_id,
                                           @Header("Authorization") String token);

    @FormUrlEncoded
    @POST("debate_list")
    Call<Debates> debates_list(@Field("user_id")String user_id,
                               @Field("offset") int offset,
                                @Header("Authorization") String token);



}
