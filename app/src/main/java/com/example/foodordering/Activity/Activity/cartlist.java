package com.example.foodordering.Activity.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodordering.Activity.Adapter.cartviewholder;
import com.example.foodordering.Activity.Domain.cart;
import com.example.foodordering.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class cartlist extends AppCompatActivity {
    private ImageView plusBtn, minusBtn;
    private int tableno = 1;
    private TextView tabletxt,taxtxt,totaltxt,itemtotaltxt,txtname,txtqtyincart,txtprice;
    private Button orderbtn;
    private RecyclerView.LayoutManager layoutManagerger;
    private RecyclerView recycleview;

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
        itemtotaltxt = (TextView) findViewById(R.id.totalFeeTxt);
        totaltxt = (TextView) findViewById(R.id.totalTxt);
        taxtxt = (TextView) findViewById(R.id.taxTxt);





    }
    @Override
    protected void onStart() {
        super.onStart();

        final DatabaseReference cartlistref = FirebaseDatabase.getInstance().getReference().child("Cart List");


        FirebaseRecyclerOptions<cart> options= new FirebaseRecyclerOptions.Builder<cart>()
                .setQuery(cartlistref.child("foodlist"),cart.class).build();

        FirebaseRecyclerAdapter<cart, cartviewholder> adapter
                = new FirebaseRecyclerAdapter<cart, cartviewholder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull cartviewholder holder, int position, @NonNull cart model) {

                holder.txtqty.setText(model.getQty());
                holder.txttitle.setText(model.getFname());
                holder.txtprice.setText(model.getPrice());
            }

            @NonNull
            @Override
            public cartviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout,parent,false);
                cartviewholder holder = new cartviewholder(view);
                return holder;
            }
        };
        recycleview.setAdapter(adapter);

        adapter.startListening();

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