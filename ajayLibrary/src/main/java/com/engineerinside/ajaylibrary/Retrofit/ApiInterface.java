package com.engineerinside.ajaylibrary.Retrofit;



import com.engineerinside.ajaylibrary.AddEntryResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("tele.php")
    Call<AddEntryResponse> add_entry(@Field("app_name") String app_name,
                                     @Field("user") String user,
                                     @Field("action") String action
    );




}
