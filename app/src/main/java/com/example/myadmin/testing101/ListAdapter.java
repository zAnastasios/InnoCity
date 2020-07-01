package com.example.myadmin.testing101;

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



    public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

        private ArrayList table;
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("photos");

        public ListAdapter(ArrayList table) {
            this.table=table;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewtype) {

            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item,viewGroup,false);
            return  new ViewHolder(itemView);

        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

            viewHolder.textView.setText(table.get(position).toString());
            Form f = (Form) table.get(position);
            viewHolder.imageView.setVisibility(View.VISIBLE);
            Glide.with(viewHolder.imageView.getContext())
                    .load(f.getUrl())
                    .into(viewHolder.imageView);

        }

        @Override
        public int getItemCount() {
            return table.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView textView;
            ImageView imageView;

            public ViewHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.viewItem);
                imageView = (ImageView) itemView.findViewById(R.id.eikona);
            }
        }

    }