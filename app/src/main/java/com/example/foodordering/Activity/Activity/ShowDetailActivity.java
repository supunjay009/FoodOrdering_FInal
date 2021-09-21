package com.example.foodordering.Activity.Activity;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.foodordering.Activity.Domain.FoodDomain;
import com.example.foodordering.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ShowDetailActivity extends AppCompatActivity {

    // ............New Adding

    private Button addToCardBtn;
    private TextView titleTxt, feeTxt, descriptionTxt, numberOrderTxt,foodid;
    private ImageView plusBtn, minusBtn, picFood;
    private FoodDomain object;
    private int numberOrder = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        initView();
        getBundle();
    }

    private void getBundle() {

        object = (FoodDomain) getIntent().getSerializableExtra("object");

        int drawableResourceId = this.getResources().getIdentifier(object.getPic(), "drawable", this.getPackageName());

        Glide.with(this)
                .load(drawableResourceId)
                .into(picFood);

        titleTxt.setText(object.getTitle());
        feeTxt.setText("LKR " + object.getFee());
        descriptionTxt.setText(object.getDescription());
        numberOrderTxt.setText(String.valueOf(numberOrder));

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOrder = numberOrder + 1;
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberOrder > 1) {
                    numberOrder = numberOrder - 1;
                }
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });

        addToCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addingtocartlist();
            }
        });

    }

    private void addingtocartlist() {

        String savecurrenttime,savecurrentdate;

        Calendar calfordate =  Calendar.getInstance();
        SimpleDateFormat curentdate = new SimpleDateFormat("MMM dd yyyy");
        savecurrentdate = curentdate.format(calfordate.getTime());

        SimpleDateFormat currenttime = new SimpleDateFormat("HH:mm:ss a");
        savecurrenttime = currenttime.format(calfordate.getTime());
       final DatabaseReference cartlistref = FirebaseDatabase.getInstance().getReference().child("Cart List");

        final HashMap<String,Object> cartmap = new HashMap<>();
        cartmap.put("fid",foodid.getText().toString());
        cartmap.put("fname",titleTxt.getText().toString());
        cartmap.put("price",feeTxt.getText().toString());
        cartmap.put("date",savecurrentdate);
        cartmap.put("time",savecurrenttime);
        cartmap.put("qty",numberOrderTxt.getText().toString());

        cartlistref.child("foodlist").child(savecurrenttime).updateChildren(cartmap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                {


                    Toast.makeText(ShowDetailActivity.this,"Food Added to the cart.",Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent(ShowDetailActivity.this, MainActivity.class);
                    startActivity(intent);


                }
            }
        });

    }

    private void initView() {

        addToCardBtn = findViewById(R.id.addtocrt_btn);
        foodid = findViewById(R.id.pid);
        titleTxt = findViewById(R.id.titleTxt);
        feeTxt = findViewById(R.id.priceTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        numberOrderTxt = findViewById(R.id.numberOrderTxt);
        plusBtn = findViewById(R.id.plusBtn);
        minusBtn = findViewById(R.id.minusBtn);
        picFood = findViewById(R.id.foodPic);

    }


}