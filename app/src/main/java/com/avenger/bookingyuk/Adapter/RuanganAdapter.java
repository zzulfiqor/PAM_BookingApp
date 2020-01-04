package com.avenger.bookingyuk.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.avenger.bookingyuk.Models.ModelRuangan;
import com.avenger.bookingyuk.R;
import com.avenger.bookingyuk.View.Activity.Description;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class RuanganAdapter extends RecyclerView.Adapter<RuanganAdapter.Viewholder> {

    private ArrayList<ModelRuangan> listRuangan;

    public RuanganAdapter(ArrayList<ModelRuangan> listRuangan){
        this.listRuangan = listRuangan;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ruangan, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, final int position) {
        final ModelRuangan modelRuangan = listRuangan.get(position);
        Glide.with(holder.itemView.getContext())
                .load(modelRuangan.getPhoto())
                .apply(new RequestOptions().override(55,55))
                .into(holder.imgRuangan);
        holder.tvRuangan.setText(modelRuangan.getNamaRuangan());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Description.class);
                i.putExtra(Description.EXTRA_PHOTO, modelRuangan.getPhoto());
                i.putExtra(Description.EXTRA_NAME, modelRuangan.getNamaRuangan());
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listRuangan.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView imgRuangan;
        TextView tvRuangan;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imgRuangan = itemView.findViewById(R.id.img_ruangan);
            tvRuangan = itemView.findViewById(R.id.nama_ruangan);
        }
    }
}
