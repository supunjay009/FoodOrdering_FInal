package com.example.foodordering.Activity.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodordering.Activity.Domain.Item;
import com.example.foodordering.Activity.Domain.Orders;
import com.example.foodordering.R;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private Activity activity;
    ArrayList<Orders> ordersArrayList;
    ArrayList<Item> itemArrayList;

    public OrderAdapter(Activity activity, ArrayList<Orders> ordersArrayList, ArrayList<Item> itemArrayList) {
        this.activity = activity;
        this.ordersArrayList = ordersArrayList;
        this.itemArrayList = itemArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lst_row_order_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Orders order = ordersArrayList.get(position);
        String finish = "FINISHED";
        String pending = "PENDING";

        holder.txtOrderNo.setText(String.valueOf(order.getId()));
        if(order.isServed()) {
            holder.txtServeState.setText(finish);
        }
        else {
            holder.txtServeState.setText(pending);
        }

        FoodAdapter foodAdapter = new FoodAdapter(itemArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        holder.lstFood.setLayoutManager(linearLayoutManager);
        holder.lstFood.setAdapter(foodAdapter);

    }

    @Override
    public int getItemCount() {
        return ordersArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtOrderNo,txtServeState;
        RecyclerView lstFood;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtOrderNo = itemView.findViewById(R.id.txtOrderNo);
            txtServeState = itemView.findViewById(R.id.txtServed);
            lstFood = itemView.findViewById(R.id.lstFood);
        }
    }

}
