package com.example.foodordering.Activity.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.foodordering.Activity.Adapter.OrderAdapter;
import com.example.foodordering.Activity.Domain.Food;
import com.example.foodordering.Activity.Domain.Item;
import com.example.foodordering.Activity.Domain.Orders;
import com.example.foodordering.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class kitchenActivity extends AppCompatActivity {

    OrderAdapter orderAdapter;
    ArrayList<Orders> ordersArrayList;
    ArrayList<Item> itemArrayList;
    ArrayList<Food> foodArrayList;
    RecyclerView RVOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen);

        RVOrder = findViewById(R.id.lstOrder);

        ordersArrayList = new ArrayList<>();
        itemArrayList = new ArrayList<>();
        foodArrayList = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference orderRef = database.getReference("orders");
        DatabaseReference foodRef = database.getReference("food");


        orderRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                foodArrayList.clear();
                ordersArrayList.clear();
                foodRef.addValueEventListener((new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot snapshot1: snapshot.getChildren()) {
                            Food food = snapshot1.getValue(Food.class);
                            foodArrayList.add(food);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                }));

                for(DataSnapshot snapshot1: snapshot.getChildren()) {
                    Orders order = snapshot1.getValue(Orders.class);
                    order.setName(snapshot1.getKey());
                    ordersArrayList.add(order);
                    itemArrayList.clear();
                    for (int k=0;k<order.getItemList().size();k++) {
                        itemArrayList.add(order.getItemList().get(k));
                    }
                    //Toast.makeText(kitchenActivity.this,String.valueOf(itemArrayList.size()),Toast.LENGTH_LONG).show();
                }
                orderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        orderAdapter = new OrderAdapter(this,ordersArrayList,itemArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        RVOrder.setLayoutManager(linearLayoutManager);
        RVOrder.setAdapter(orderAdapter);

    }
}

//        int[] orderId = {69};
//        boolean[] isServed = {false};
//
//        //-------------------------------------------
//        HashMap<String,Integer> items1 = new HashMap<>();
//        items1.put("food",420);
//        items1.put("qty",69);
//        HashMap<String,Integer> items2 = new HashMap<>();
//        items2.put("food",420);
//        items2.put("qty",69);
//        HashMap<String,HashMap<String,Integer>> itemMap= new HashMap<>();
//        itemMap.put("item1",items1);
//        itemMap.put("item2",items2);
//        //--------------------------------------------
//
//        ordersArrayList = new ArrayList<>();
//
//        for(int i=0;i<orderId.length;i++) {
//            Orders order = new Orders(orderId[i],isServed[i],itemMap);
//            ordersArrayList.add(order);
//
//            itemArrayList = new ArrayList<>();
//            for(int j=0;j<itemMap.size();j++) {
//                Item item = order.getItemList().get(j);
//                itemArrayList.add(item);
//            }
//        }