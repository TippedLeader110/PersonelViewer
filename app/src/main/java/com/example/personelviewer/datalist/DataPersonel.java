package com.example.personelviewer.datalist;

import com.google.gson.annotations.SerializedName;

public class DataPersonel {

    @SerializedName("id_person")
    Integer id_person;

    @SerializedName("nama")
    String nama;

    @SerializedName("jk")
    Integer jk;

    @SerializedName("tgl_lahir")
    String tgl_lahir;

    @SerializedName("tempat_lahir")
    String tempat_lahir;

    @SerializedName("nik")
    String nik;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJk() {
        if(jk==1){
            return "Laki-Laki";
        }else{
            return "Perempuan";
        }
    }

    public void setJk(Integer jk) {
        this.jk = jk;
    }

    public String getTgl_lahir() {
        return tgl_lahir;
    }

    public void setTgl_lahir(String tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
    }

    public String getTempat_lahir() {
        return tempat_lahir;
    }

    public void setTempat_lahir(String tempat_lahir) {
        this.tempat_lahir = tempat_lahir;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public Integer getId_person() {
        return id_person;
    }

    public void setId_person(Integer id_person) {
        this.id_person = id_person;
    }
}
