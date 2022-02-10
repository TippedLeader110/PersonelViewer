package com.example.personelviewer.datadetail;

import com.example.personelviewer.datalist.DataPersonel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DataDetail {

    @SerializedName("info")
    DataPersonel detail;

    @SerializedName("role")
    DetailRole role;

    @SerializedName("detailriwayat")
    ArrayList<DetailRiwayat> riwayat;

    @SerializedName("jenisriwayat")
    ArrayList<JenisRiwayat> jenisriwayat;

    public ArrayList<JenisRiwayat> getJenisriwayat() {
        return jenisriwayat;
    }

    public void setJenisriwayat(ArrayList<JenisRiwayat> jenisriwayat) {
        this.jenisriwayat = jenisriwayat;
    }

    public DataPersonel getDetail() {
        return detail;
    }

    public void setDetail(DataPersonel detail) {
        this.detail = detail;
    }

    public DetailRole getRole() {
        return role;
    }

    public void setRole(DetailRole role) {
        this.role = role;
    }

    public ArrayList<DetailRiwayat> getRiwayat() {
        return riwayat;
    }

    public void setRiwayat(ArrayList<DetailRiwayat> riwayat) {
        this.riwayat = riwayat;
    }
}

class DetailRiwayat {

    @SerializedName("id_riwayat")
    int id_riwayat;

    @SerializedName("id_jenisriwayat")
    int id_jenisriwayat;

    @SerializedName("nama_riwayat")
    String nama_riwayat;

    @SerializedName("riwayat")
    String riwayat;

    @SerializedName("tgl_riwayat")
    String tgl_riwayat;

    public int getId_riwayat() {
        return id_riwayat;
    }

    public void setId_riwayat(int id_riwayat) {
        this.id_riwayat = id_riwayat;
    }

    public int getId_jenisriwayat() {
        return id_jenisriwayat;
    }

    public void setId_jenisriwayat(int id_jenisriwayat) {
        this.id_jenisriwayat = id_jenisriwayat;
    }

    public String getNama_riwayat() {
        return nama_riwayat;
    }

    public void setNama_riwayat(String nama_riwayat) {
        this.nama_riwayat = nama_riwayat;
    }

    public String getRiwayat() {
        return riwayat;
    }

    public void setRiwayat(String riwayat) {
        this.riwayat = riwayat;
    }

    public String getTgl_riwayat() {
        return tgl_riwayat;
    }

    public void setTgl_riwayat(String tgl_riwayat) {
        this.tgl_riwayat = tgl_riwayat;
    }
}

class DetailRole {

    @SerializedName("id_mahasiswa")
    int id_mahasiswa;

    @SerializedName("status_menikah")
    String status_menikah;

    @SerializedName("tanggal_menikah")
    String tanggal_menikah;

    public int getId_mahasiswa() {
        return id_mahasiswa;
    }

    public void setId_mahasiswa(int id_mahasiswa) {
        this.id_mahasiswa = id_mahasiswa;
    }

    public String getStatus_menikah() {
        if(status_menikah.equals("1") && status_menikah!=null){
            return "Sudah Menikah";
        }else{
            return "Belum Menikah";
        }
    }

    public void setStatus_menikah(String status_menikah) {
        this.status_menikah = status_menikah;
    }

    public String getTanggal_menikah() {
        if(status_menikah!=null){
            return tanggal_menikah;
        }else{
            return "-";
        }
    }

    public void setTanggal_menikah(String tanggal_menikah) {
        this.tanggal_menikah = tanggal_menikah;
    }
}

class JenisRiwayat{

    @SerializedName("id_qjenisriwayat")
    Integer id_qjenisriwayat;

    @SerializedName("nama_jenisriwayat")
    String nama_jenisriwayat;

    public Integer getId_qjenisriwayat() {
        return id_qjenisriwayat;
    }

    public void setId_qjenisriwayat(Integer id_qjenisriwayat) {
        this.id_qjenisriwayat = id_qjenisriwayat;
    }

    public String getNama_jenisriwayat() {
        return nama_jenisriwayat;
    }

    public void setNama_jenisriwayat(String nama_jenisriwayat) {
        this.nama_jenisriwayat = nama_jenisriwayat;
    }
}


