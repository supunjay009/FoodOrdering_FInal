package com.example.foodordering.Activity.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodordering.Activity.Adapter.cartviewAdapter;
import com.example.foodordering.Activity.Domain.cart;
import com.example.foodordering.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class cartlist extends AppCompatActivity {
    private ImageView plusBtn, minusBtn;
    private int tableno = 1;
    private TextView tabletxt,totaltxt;
    private Button orderbtn;
    private RecyclerView.LayoutManager layoutManagerger;
    private ScrollView scrollView;
    private TextView emptytxt;
    private RecyclerView recycleview;
    cartviewAdapter cartAdapter;
    ArrayList<cart> cartArrayList;
    private double cSubTotal = 0;
    private Double toteach,total=0.00;;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartlist);


        
        initView();
        setTableno();


        recycleview =findViewById(R.id.cartlist);
        recycleview.setHasFixedSize(true);
        layoutManagerger =new LinearLayoutManager(this);
        recycleview.setLayoutManager(layoutManagerger);

        orderbtn = (Button) findViewById(R.id.placeorderbtn);

        totaltxt = (TextView) findViewById(R.id.totalTxt);




        cartArrayList = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference cartref = database.getReference("Cart List").child("foodlist");

        cartref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1: snapshot.getChildren()) {
                    cart cart = snapshot1.getValue(cart.class);
                    cartArrayList.add(cart);


                }
                cartAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        cartAdapter = new cartviewAdapter(cartArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycleview.setLayoutManager(linearLayoutManager);
        recycleview.setAdapter(cartAdapter);




    }



    private void setTableno()
    {
        tabletxt.setText(String.valueOf(tableno));
        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableno = tableno + 1;
                tabletxt.setText(String.valueOf(tableno));
            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tableno > 1) {
                    tableno = tableno - 1;
                }
                tabletxt.setText(String.valueOf(tableno));

            }
        });
    }


    private void initView() {


        tabletxt = findViewById(R.id.textView16);
        plusBtn = findViewById(R.id.imageView5);

        minusBtn = findViewById(R.id.imageView4);


    }
}