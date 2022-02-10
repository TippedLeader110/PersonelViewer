package com.example.personelviewer.datadetail;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.personelviewer.R;
import com.example.personelviewer.api.RetrofitClient;
import com.example.personelviewer.datalist.PersonelView;

import java.util.ArrayList;
import java.util.HashSet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPersonelView extends AppCompatActivity {

    DataDetail dataDetail;
    ProgressDialog pd;
    DetailInterface detailInterface;
    RetrofitClient retrofitClient;
    Integer id_person;
    TextView id, nama, nik, jk, status_menikah, tgl_menikah, tempattgl_lahir;
    LinearLayout linearLayout, ll;
    Integer curid = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_personel);

        id_person = Integer.valueOf(getIntent().getStringExtra("id_person"));

        linearLayout = (LinearLayout) findViewById(R.id.ll_riwayat);
        ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setId(curid*12*2);


        id = findViewById(R.id.id_personel);
        nama = findViewById(R.id.nama);
        nik = findViewById(R.id.nik);
        jk = findViewById(R.id.jk);
        status_menikah = findViewById(R.id.status_menikah);
        tgl_menikah = findViewById(R.id.tgl_menikah);
        tempattgl_lahir = findViewById(R.id.lahir);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Detail ");
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        retrofitClient = new RetrofitClient();
        detailInterface = retrofitClient.getClient().create(DetailInterface.class);

        pd = new ProgressDialog(DetailPersonelView.this);
        pd.setTitle("Loading");
        pd.setMessage("Mengambil Data.....");
        pd.setCancelable(false);
        pd.show();
        fetchData();

    }

    private void fetchData() {
        Log.w("Post", String.valueOf(this.id_person));

        Call<DataDetail> infoGet = detailInterface.getInfo(String.valueOf(id_person));

        infoGet.enqueue(new Callback<DataDetail>() {
            @Override
            public void onResponse(Call<DataDetail> call, Response<DataDetail> response) {
                Log.w("Response Detail", String.valueOf(response.body()));
                DataDetail dataDetail = response.body();

                id.setText(Integer.toString(dataDetail.detail.getId_person()));
                nama.setText(dataDetail.detail.getNama());
                nik.setText(dataDetail.detail.getNik());
                jk.setText(dataDetail.detail.getJk());
                tempattgl_lahir.setText(dataDetail.detail.getTempat_lahir() + ", " + dataDetail.detail.getTgl_lahir());
                status_menikah.setText(dataDetail.role.getStatus_menikah());
                tgl_menikah.setText(dataDetail.role.getTanggal_menikah());

                ArrayList<JenisRiwayat> jenisRiwayat = dataDetail.getJenisriwayat();
                ArrayList<DetailRiwayat> detailRiwayats = dataDetail.getRiwayat();

                for(int i = 0; i < jenisRiwayat.size() ; i++){

                    String item = "";

                    for(int j = 0; j < detailRiwayats.size(); j++){
                        if(jenisRiwayat.get(i).getId_qjenisriwayat() == detailRiwayats.get(j).getId_jenisriwayat()){
                            item = item + detailRiwayats.get(j).getRiwayat() + " (" + detailRiwayats.get(j).getTgl_riwayat() + ")\n";
                        }
                    }

                    getFragmentManager().beginTransaction()
                            .add(ll.getId(), RiwayatFragment.newInstance(item, jenisRiwayat.get(i).getNama_jenisriwayat()), "someTag1")
                            .commit();

                }

                linearLayout.addView(ll);
                pd.dismiss();
//                Toast.makeText(DetailPersonelView.this, "Loading sel", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<DataDetail> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(DetailPersonelView.this, "Terjadi kesalah", Toast.LENGTH_SHORT).show();
            }
        });

    }
}