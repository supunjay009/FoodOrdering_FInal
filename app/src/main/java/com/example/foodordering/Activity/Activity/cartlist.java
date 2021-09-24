package com.example.foodordering.Activity.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodordering.Activity.Adapter.cartviewAdapter;
import com.example.foodordering.Activity.Domain.Cart;
import com.example.foodordering.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class cartlist extends AppCompatActivity {
    private ImageView plusBtn, minusBtn;
    private int tableno = 1;
    private TextView tabletxt,overallalltotal,orderqty,fidtxtincart, txtnameofitem ;
    public static Button orderbtn;
    private RecyclerView.LayoutManager layoutManagerger;
    private ScrollView scrollView;
    private TextView emptytxt;
    private RecyclerView recycleview;
    cartviewAdapter cartAdapter;
    ArrayList<Cart> cartArrayList;




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
        tabletxt=(TextView) findViewById(R.id.textView16);
        overallalltotal = (TextView) findViewById(R.id.totalTxt);
//get tot from adpter
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReciver,new IntentFilter("MyTotalAmmount"));


        cartArrayList = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference cartref = database.getReference("Cart List").child("foodlist");

        cartref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1: snapshot.getChildren()) {
                    Cart cart = snapshot1.getValue(Cart.class);
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
//        orderbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                placeorder();
//            }
//        });
    }

    private void placeorder() {

//
//        final DatabaseReference orderlistref = FirebaseDatabase.getInstance().getReference().child("orders");
//        String tno =tabletxt.getText().toString();
//        final HashMap<String,Object> cartmap = new HashMap<>();
//        cartmap.put("id",oid);
//        cartmap.put("tableno",tno);
//        cartmap.put("served","False");
//
//        orderlistref.child(String.valueOf(oid)).updateChildren(cartmap).addOnCompleteListener(new OnCompleteListener<Void>() {
//
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if (task.isSuccessful())
//                {
//
//
//                    Toast.makeText(cartlist.this,"Your order is placed.",Toast.LENGTH_SHORT).show();
//                    Intent intent =new Intent(cartlist.this, MainActivity.class);
//                    startActivity(intent);
//
//
//                }
//            }
//        });
//        //int qty = Integer.parseInt(orderqty.getText().toString());
//        //int fid = Integer.parseInt(fidtxtincart.getText().toString());
//        int qty = 1;
//        int fid =1;
//        final HashMap<String,Object> itemmap = new HashMap<>();
//        itemmap.put("id",fid);
//        itemmap.put("image","test.jpg");
//        //cartmap.put("name",txtnameofitem.getText().toString());
//        itemmap.put("name","test");
//        itemmap.put("qty",qty);
//        itemmap.put("totprice",overallalltotal.getText().toString());
//
//        orderlistref.child(String.valueOf(oid)).child("items").child(String.valueOf(fid)).updateChildren(itemmap).addOnCompleteListener(new OnCompleteListener<Void>() {
//
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if (task.isSuccessful())
//                {
//                }
//            }
//        });
    }


    private void initView() {


        plusBtn = findViewById(R.id.imageView5);
        minusBtn = findViewById(R.id.imageView4);
        tabletxt = findViewById(R.id.textView16);
        fidtxtincart=findViewById(R.id.fidtxtincart);
        //orderqty = cartAdapter.findViewById(R.id.txtqtyincart);
        txtnameofitem = findViewById(R.id.title2Txt);
    }

    public BroadcastReceiver mMessageReciver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int totalbill =intent.getIntExtra("totalAmmount",0);

            overallalltotal.setText("LKR "+totalbill+".00");
        }
    };
}