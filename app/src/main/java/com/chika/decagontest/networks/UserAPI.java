package com.chika.decagontest.networks;

import com.chika.decagontest.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserAPI {

    @GET("accounts")
    Call<List<User>> getUsers();
}
