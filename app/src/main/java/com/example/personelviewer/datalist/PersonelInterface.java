package com.example.personelviewer.datalist;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PersonelInterface {

    @GET("personel")
    Call<ArrayList<DataPersonel>> getPersonel();

    @GET("dosen")
    Call<ArrayList<DataPersonel>> getDosen();

    @GET("mahasiswa")
    Call<ArrayList<DataPersonel>> getMahasiswa();

    @GET("pegawai")
    Call<ArrayList<DataPersonel>> getPegawai();
}
