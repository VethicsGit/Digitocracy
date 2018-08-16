package com.example.kanika.digitocracy;

import com.example.kanika.digitocracy.login.Responsee;

import okhttp3.ResponseBody;
import okhttp3.internal.http.RealResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface API {

    @FormUrlEncoded
    @POST("Signup")
    Call<Response_> Signup(@Field("name") String name,
                                    @Field("email") String email,
                                    @Field("password") String password,
                                    @Field("gender") String gender);
    @FormUrlEncoded
    @POST("Privacy_Policy")
    Call<ResponseBody> Privacy_Policy (@Field("name") String name,
                                    @Field("email") String email,
                                    @Field("password") String password);

    @POST("About_US")
    Call<RealResponseBody> About_Us (@Field("name") String name,
                                           @Field("email") String email,
                                           @Field("password") String password);


    @POST("Terms_and_condition")
    Call<RealResponseBody> Terms_and_condition (@Field("name") String name,
                                     @Field("email") String email,
                                     @Field("password") String password);

    @POST("Resend_verification_email")
    Call<RealResponseBody> Resend_verification_email(@Field("email") String email);

    @FormUrlEncoded
    @POST("signin?")
    Call<Responsee> Sigin (@Field("email") String email,
                                                @Field("password") String password);

    @POST("Forgot_Password")
    Call<RealResponseBody> Forgot_Password (@Field("email") String email);


    @POST("Change_Password")
    Call<RealResponseBody> Change_Password(@Field("user_id") String user_id,
                                                @Field("password") String password);


    @POST("Uadated_device_token")
    Call<RealResponseBody> Uadated_device_token(@Field("user_id") String user_id,
                                                @Field("device_type") String device_type,
                                                @Field("device_token") String device_token);

    @POST("Logout")
    Call<RealResponseBody> Logout(@Field("user_id") String user_id,
                                           @Field("device_token") String device_token);


    @POST("Contact_Us")
    Call<RealResponseBody> Contact_Us(@Field("user_id") String user_id,
                                  @Field("front_email") String front_email,
                                  @Field("subject") String subject,
                                  @Field("message") String message,
                                      @Field("phone_number") String phone_number,
                                      @Field("From_name") String From_name);


    @POST("User_profile")
    Call<RealResponseBody> User_profile(@Field("user_id") String user_id);



    @POST("Upadate_user_profile")
    Call<RealResponseBody> Upadate_user_profile(@Field("user_id") String user_id,
                                                @Field("name") String name,
                                                @Field("gender") String gender,
                                                @Field("notification_status") String notification_status,
                                                @Field("Profile_Pic") String profile_pic);



    @POST("Upadate_location")
    Call<RealResponseBody> Upadate_location(@Field("user_id") String user_id,
                                                @Field("lat") String lat,
                                                @Field("lng") String lng,
                                                @Field("location_address") String location_address);




    @POST("Poll_category_list")
    Call<RealResponseBody> Poll_category_list(@Field("user_id") String user_id);




    @POST("blog_category_list")
    Call<RealResponseBody> bolg_category_list(@Field("user_id") String user_id);



    @POST("blog_list")
    Call<RealResponseBody> blog_list(@Field("user_id") String user_id);



    @POST("blog_details")
    Call<RealResponseBody> blog_details(@Field("user_id") String user_id,
                                        @Field("blog_is") String blog_is);



    @POST("Poll_list")
    Call<RealResponseBody> Poll_list(@Field("user_id") String user_id,
                                            @Field("offset") String offset,
                                            @Field("type") String type,
                                            @Field("poll_category_id") String poll_category_id);


    @POST("send_vote_on_poll")
    Call<RealResponseBody> send_vote_on_poll(@Field("user_id") String user_id,
                                     @Field("poll_id") String poll_id,
                                     @Field("poll_option_id") String poll_option_id);


    @POST("poll_details")
    Call<RealResponseBody> poll_details(@Field("user_id") String user_id,
                                             @Field("poll_id") String poll_id);


}
