package com.blogspot.programer27android.copasqenedrive.api;

import com.blogspot.programer27android.copasqenedrive.model.ResponseData;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by gery on 11/20/17.
 */

public interface RestApi {
    //insert
    @FormUrlEncoded
    @POST("insert.php")
    Call<ResponseData> senddatauser(@Field("nama") String nama,
                                    @Field("email") String email,
                                    @Field("password") String password);

    //read
    @GET("read.php")
    Call<ResponseData> getdatauser();

    //update menggunakan 3 parameter
    @FormUrlEncoded
    @POST("update.php")
    Call<ResponseData> updateData( @Field("id") String id,
                                    @Field("nama") String nama,
                                    @Field("email") String email,
                                    @Field("password") String password);
    //delete menggunakan parameter id
    @FormUrlEncoded
    @POST("delete.php")
    Call<ResponseData> deleteData(@Field("id") String id);
}
