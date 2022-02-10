package com.example.personelviewer.datadetail;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.personelviewer.R;

import java.util.ArrayList;

public class RiwayatFragment extends Fragment {



    public static RiwayatFragment newInstance(String item, String title) {

        RiwayatFragment f = new RiwayatFragment();
        Bundle b = new Bundle();
        b.putString("title", title);
        b.putString("item", item);
        f.setArguments(b);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.riwayat_fragment, container, false);

        ((TextView) v.findViewById(R.id.title_riwayat)).setText(getArguments().getString("title"));
        ((TextView) v.findViewById(R.id.item_riwayat)).setText(getArguments().getString("item"));
        return v;
    }
}