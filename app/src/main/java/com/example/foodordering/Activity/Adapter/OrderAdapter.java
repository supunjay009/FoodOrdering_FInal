package com.example.foodordering.Activity.Adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodordering.Activity.Domain.Item;
import com.example.foodordering.Activity.Domain.Orders;
import com.example.foodordering.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private Activity activity;
    ArrayList<Orders> ordersArrayList;
    //ArrayList<Item> itemArrayList;

//    public OrderAdapter(Activity activity, ArrayList<Orders> ordersArrayList, ArrayList<Item> itemArrayList) {
    public OrderAdapter(Activity activity, ArrayList<Orders> ordersArrayList) {
        this.activity = activity;
        this.ordersArrayList = ordersArrayList;
        //this.itemArrayList = itemArrayList;
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
            holder.txtServeState.setTextColor(Color.parseColor("#365243"));
            holder.btnDone.setVisibility(View.GONE);
        }
        else {
            holder.txtServeState.setText(pending);
            holder.txtServeState.setTextColor(Color.parseColor("#fc5203"));
            holder.btnDone.setVisibility(View.VISIBLE);
        }

        FoodAdapter foodAdapter = new FoodAdapter(order.getItemList());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        holder.lstFood.setLayoutManager(linearLayoutManager);
        holder.lstFood.setAdapter(foodAdapter);

        holder.btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference orderRef = database.getReference("orders");
                orderRef.child(order.getName()).child("served").setValue(true);
            }
        });

    }

    @Override
    public int getItemCount() {
        return ordersArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtOrderNo,txtServeState;
        RecyclerView lstFood;
        Button btnDone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtOrderNo = itemView.findViewById(R.id.txtOrderNo);
            txtServeState = itemView.findViewById(R.id.txtServed);
            lstFood = itemView.findViewById(R.id.lstFood);
            btnDone = itemView.findViewById(R.id.btnDone);
        }

    }

}
