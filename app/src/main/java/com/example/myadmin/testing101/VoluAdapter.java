package com.example.myadmin.testing101;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class VoluAdapter extends RecyclerView.Adapter<VoluAdapter.ViewHolder> {
    private  ClickListener clicklistener = null;
    private ArrayList <VoluntaryWork> table;

    StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("photos");

    public VoluAdapter(ArrayList table) {
        this.table=table;
    }

    @NonNull
    @Override
    public VoluAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewtype) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v = inflater.inflate(R.layout.activity_voluntary_work, viewGroup, false);
        VoluAdapter.ViewHolder vh = new VoluAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull VoluAdapter.ViewHolder viewHolder, final int position) {



        VoluntaryWork vol_work = (VoluntaryWork) table.get(position);
        viewHolder.volu_textView.setText(vol_work.getShortDesc());
        viewHolder.volu_date.setText(vol_work.getDate());
        viewHolder.volu_imageView.setVisibility(View.VISIBLE);
        Glide.with(viewHolder.volu_imageView.getContext())
                .load(vol_work.getPic_Url())
                .into(viewHolder.volu_imageView);




    }
    public void setClickListener(ClickListener clickListener){
        this.clicklistener = clickListener;
    }

    @Override
    public int getItemCount() {
        return table.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView  volu_textView;
        TextView  volu_date;
        ImageView volu_imageView;
        public View view;
        public VoluntaryWork currentItem;

        public ViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            volu_textView = (TextView) itemView.findViewById(R.id.volu_short_desc);
            volu_date=(TextView) itemView.findViewById(R.id.volu_date);
            volu_imageView = (ImageView) itemView.findViewById(R.id.volu_image);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(view.getContext(),Volu_News_More_Info.class);
                    intent.putExtra("Volu_work_clicked",table.get(getAdapterPosition()));
                    view.getContext().startActivity(intent);


                }
            });
        }
    }


}

