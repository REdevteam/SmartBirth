package com.futech.smartbirth;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder>{

    private List<DataModel> dataModelList;


    ListAdapter(List<DataModel> dataModelList){
        this.dataModelList = dataModelList;
    }


    @Override
    public int getItemCount() {
        return dataModelList.size();
    }


    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_list, viewGroup, false);
        ListViewHolder listViewHolder = new ListViewHolder(v);
        return listViewHolder;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onBindViewHolder(final ListViewHolder listViewHolder,final int i) {
        listViewHolder.tanggal.setText(dataModelList.get(i).getTanggal());
        listViewHolder.berat.setText(dataModelList.get(i).getBerat());

    }


    public static class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tanggal;
        TextView berat;

        ListViewHolder(View itemView) {
            super(itemView);
            tanggal = itemView.findViewById(R.id.textViewTanggal);
            berat = itemView.findViewById(R.id.textViewBerat);

        }
    }

}