package com.example.personelviewer.datalist;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personelviewer.R;
import com.example.personelviewer.api.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonelView extends AppCompatActivity {

    RecyclerView recyclerView;
    String data;
    ArrayList<DataPersonel> dataPersonels;
    PersonelRecyclerAdapter personelRecyclerAdapter;
    ProgressDialog pd;
    PersonelInterface personelInterface;
    RetrofitClient retrofitClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);
        recyclerView = findViewById(R.id.rc_list);

        data = getIntent().getStringExtra("data");

        String upperString = data.substring(0, 1).toUpperCase() + data.substring(1).toLowerCase();


        ActionBar ab = getSupportActionBar();
        ab.setTitle("Daftar " + upperString);
//        ab.setSubtitle("sub-title");
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
        retrofitClient = new RetrofitClient();
        personelInterface = retrofitClient.getClient().create(PersonelInterface.class);

        personelRecyclerAdapter = new PersonelRecyclerAdapter(this);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        personelRecyclerAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(personelRecyclerAdapter);

        pd = new ProgressDialog(PersonelView.this);
        pd.setTitle("Loading");
        pd.setMessage("Mengambil Data.....");
        pd.setCancelable(false);
        pd.show();
        fetchData();
    }

    private void fetchData() {

        Call<ArrayList<DataPersonel>> getData;

        if (data.equals("personel")){
            getData = personelInterface.getPersonel();
        }else if(data.equals("dosen")){
            getData = personelInterface.getDosen();
        }else if(data.equals("mahasiswa")){
            getData = personelInterface.getMahasiswa();
        }else{
            getData = personelInterface.getPegawai();
        }

        getData.enqueue(new Callback<ArrayList<DataPersonel>>() {
            @Override
            public void onResponse(Call<ArrayList<DataPersonel>> call, Response<ArrayList<DataPersonel>> response) {
                dataPersonels = response.body();

                personelRecyclerAdapter.setData(dataPersonels);
                pd.dismiss();

                Toast.makeText(PersonelView.this, "Berhasil mengambil data" + data, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<ArrayList<DataPersonel>> call, Throwable t) {
                Toast.makeText(PersonelView.this, "Terjadi kesalahan (cek Logcat)", Toast.LENGTH_SHORT).show();
                Log.e("Err ", String.valueOf(call));
                t.printStackTrace();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_view, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                personelRecyclerAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }
}
