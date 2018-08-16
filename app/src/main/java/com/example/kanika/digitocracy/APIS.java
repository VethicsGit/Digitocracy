package com.example.kanika.digitocracy;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIS {

    public static final String BASE_URL1= "http://35.154.40.14/democracy_ios_app/webservice/v1/api/";
    private static Retrofit retrofit = null;


    public static Retrofit getRetrofit(){
        if (retrofit==null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL1).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
