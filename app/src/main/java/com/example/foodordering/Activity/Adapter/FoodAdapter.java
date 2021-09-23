package com.example.foodordering.Activity.Adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodordering.Activity.Domain.Item;
import com.example.foodordering.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    ArrayList<Item> itemArrayList;

    public FoodAdapter(ArrayList<Item> itemArrayList) {
        this.itemArrayList = itemArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lst_row_food,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Item item = itemArrayList.get(position);

        StorageReference storageReference = FirebaseStorage.getInstance().getReference("images");
        StorageReference photoRef = storageReference.child(item.getImage());
        photoRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                //holder.imgFood.setImageURI(uri);
                Picasso.get().load(uri).into(holder.imgFood);
            }
        });

        holder.txtFood.setText(String.valueOf(item.getName()));
        holder.txtQty.setText(String.valueOf(item.getQty()));
        holder.txtFoodID.setText(String.valueOf(item.getId()));
    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder {

        TextView txtFood,txtQty,txtFoodID;
        ImageView imgFood;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtFood = itemView.findViewById(R.id.txtFood);
            txtQty = itemView.findViewById(R.id.txtQty);
            txtFoodID = itemView.findViewById(R.id.txtFoodID);
            imgFood = itemView.findViewById(R.id.imgFood);
        }
    }
}
