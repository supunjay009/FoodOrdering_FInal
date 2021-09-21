package com.example.foodordering.Activity.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodordering.Activity.Domain.Item;
import com.example.foodordering.R;

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

        holder.txtFood.setText(String.valueOf(item.getFoodId()));
        holder.txtQty.setText(String.valueOf(item.getQty()));
    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder {

        TextView txtFood,txtQty;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtFood = itemView.findViewById(R.id.txtFood);
            txtQty = itemView.findViewById(R.id.txtQty);
        }
    }
}
