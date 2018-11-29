package com.futech.smartbirth;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


public class BeritaAdapter extends RecyclerView.Adapter<BeritaAdapter.BeritaViewHolder>{

    private List<BeritaDataModel> beritaDataModelList;


    BeritaAdapter(List<BeritaDataModel> beritaDataModelList){
        this.beritaDataModelList = beritaDataModelList;
    }


    @Override
    public int getItemCount() {
        return beritaDataModelList.size();
    }


    @Override
    public BeritaViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_berita, viewGroup, false);
        BeritaViewHolder beritaViewHolder = new BeritaViewHolder(v);
        return beritaViewHolder;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onBindViewHolder(final BeritaViewHolder beritaViewHolder,final int i) {
        beritaViewHolder.judulBerita.setText(beritaDataModelList.get(i).getJudulBerita());
        beritaViewHolder.deskripsiBerita.setText(beritaDataModelList.get(i).getDeskripsiBerita());
        beritaViewHolder.tanggalBerita.setText(beritaDataModelList.get(i).getTanggalBerita());

    }


    public static class BeritaViewHolder extends RecyclerView.ViewHolder {
        TextView judulBerita;
        TextView deskripsiBerita;
        TextView tanggalBerita;

        BeritaViewHolder(View itemView) {
            super(itemView);
            judulBerita = itemView.findViewById(R.id.textViewJudul);
            deskripsiBerita = itemView.findViewById(R.id.textViewDeskripsi);
            tanggalBerita = itemView.findViewById(R.id.textViewTanggal);

        }
    }

}