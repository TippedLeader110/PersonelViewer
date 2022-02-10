package com.example.personelviewer.datalist;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personelviewer.datadetail.DetailPersonelView;
import com.example.personelviewer.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PersonelRecyclerAdapter extends RecyclerView.Adapter<PersonelRecyclerAdapter.PersonelViewHolderAdapter> implements Filterable {

    Context context;
    ArrayList<DataPersonel> data;
    ArrayList<DataPersonel> dataTemp;

    public PersonelRecyclerAdapter(Context context) {
        this.context = context;
        data = new ArrayList<DataPersonel>();
        dataTemp = new ArrayList<DataPersonel>();
    }

    @NonNull
    @Override
    public PersonelViewHolderAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View vew = inflater.inflate(R.layout.listview_menu, parent, false);
        return new PersonelViewHolderAdapter(vew);
    }

    public void setData(List<DataPersonel> data) {
        this.data = new ArrayList<DataPersonel>(data);
        this.dataTemp = new ArrayList<DataPersonel>(data);
        Log.w("dataTempSize", String.valueOf(dataTemp.size()));
        this.notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull PersonelViewHolderAdapter holder, int position) {
        int pos = position;
        int id = this.data.get(pos).getId_person();

        holder.nama.setText(this.data.get(position).getNama() + " (" + this.data.get(position).getNik() + ")");
        holder.lahir.setText(this.data.get(position).getTempat_lahir() + ", " + this.data.get(position).getTgl_lahir());

        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.w("id_person", String.valueOf(id));
                Intent intent = new Intent(context, DetailPersonelView.class);
                intent.putExtra("id_person", String.valueOf(id));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(data!=null){
            return data.size();
        }else{
            return 0;
        }
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            Log.w("Seq", charSequence.toString());
            ArrayList<DataPersonel> tempFilter = new ArrayList<DataPersonel>();
            Log.w("Seq Temp Size", String.valueOf(dataTemp.size()));

            if(charSequence.toString().isEmpty()){
                Log.w("Seq", "Empty");
                Log.w("Seq Temp Size EMP", String.valueOf(dataTemp.size()));
                tempFilter.addAll(dataTemp);
            }else {
                for (DataPersonel pos : data){
                    if(pos.getNama().toLowerCase().contains(charSequence.toString().toLowerCase())){
                        tempFilter.add(pos);
                    }else {
                        if(pos.getTempat_lahir().toLowerCase().contains(charSequence.toString().toLowerCase())){
                            tempFilter.add(pos);
                        }else{
                            if(pos.getNik().toLowerCase().contains(charSequence.toString().toLowerCase())){
                                tempFilter.add(pos);
                            }
                        }
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = tempFilter;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            data.clear();
            data.addAll((Collection<? extends DataPersonel>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class PersonelViewHolderAdapter extends RecyclerView.ViewHolder{

        TextView nama, lahir;
        LinearLayout ll;

        public PersonelViewHolderAdapter(@NonNull View itemView) {
            super(itemView);
            ll = itemView.findViewById(R.id.ll);
            nama = itemView.findViewById(R.id.nama);
            lahir = itemView.findViewById(R.id.lahir);
        }
    }



}
