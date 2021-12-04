package com.engineerinside.ajaylibrary;

import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.engineerinside.ajaylibrary.Retrofit.ApiInterface;
import com.engineerinside.ajaylibrary.Retrofit.RetrofitApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Telemetry {



        public static void record(String app_name,String user_name,String action){

                String userName = user_name;
                if(user_name.equals("")){
                        userName = Build.MODEL+" "+Build.MANUFACTURER+" "+Build.ID;
                }

                RetrofitApi.getRetrofitInstance().create(ApiInterface.class).add_entry(
                        app_name,userName,action
                ).enqueue(new Callback<AddEntryResponse>() {
                        @Override
                        public void onResponse(Call<AddEntryResponse> call, Response<AddEntryResponse> response) {
                                Log.d("telemetry","tele success");
                        }

                        @Override
                        public void onFailure(Call<AddEntryResponse> call, Throwable t) {
                                Log.d("telemetry","tele fail");
                        }
                });
        }

}
