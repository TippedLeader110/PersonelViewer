package com.example.personelviewer.datadetail;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface DetailInterface {

    @FormUrlEncoded
    @POST("info")
    Call<DataDetail> getInfo(@Field("id_person") String id_person);

}
