package com.chika.decagontest.networks;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {
    private static Retrofit instance;

    public static Retrofit getInstance(){
        if (instance == null){
            //address of the users
            String url = "https://android-json-test-api.herokuapp.com/";
            instance = new Retrofit.Builder().baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return instance;
    }
}
