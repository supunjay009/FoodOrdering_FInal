package com.example.foodordering.Activity.Activity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodordering.Activity.Domain.Cart;
import com.example.foodordering.Activity.Domain.Food;
import com.example.foodordering.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ShowDetailActivity extends AppCompatActivity {

    // ............New Adding

    private Button addToCardBtn;
    private TextView titleTxt, feeTxt, descriptionTxt, numberOrderTxt,foodid;
    private ImageView plusBtn, minusBtn, picFood;
    private Food food;
    private int numberOrder = 1;
    private Intent intent;
    //private Cart cart = new Cart();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        initView();
        getBundle();
    }

    private void getBundle() {

        intent = getIntent();
        food = (Food) intent.getSerializableExtra("FOOD");

        StorageReference storageReference = FirebaseStorage.getInstance().getReference("images");
        StorageReference photoRef = storageReference.child(food.getImage());
        photoRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                //holder.imgFood.setImageURI(uri);
                Picasso.get().load(uri).into(picFood);
            }
        });

        titleTxt.setText(food.getName());
        feeTxt.setText(String.valueOf(food.getPrice()));
        descriptionTxt.setText(food.getDescription());
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

                addingToCartList();
            }
        });

    }

    private void addingToCartList() {

        String saveCurrentTime,saveCurrentDate;

        Calendar calForDate =  Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final DatabaseReference cartListRef = FirebaseDatabase.getInstance().getReference().child("CartList");

        final HashMap<String,Object> cartMap = new HashMap<>();
        cartMap.put("fid",foodid.getText().toString());
        cartMap.put("fname",titleTxt.getText().toString());
        cartMap.put("price",feeTxt.getText().toString());
        cartMap.put("date",saveCurrentDate);
        cartMap.put("time",saveCurrentTime);
        cartMap.put("qty",numberOrderTxt.getText().toString());

//        cart.setFid(String.valueOf(food.getId()));
//        cart.setFname(food.getName());

        cartListRef.child("foodList").child(saveCurrentTime).updateChildren(cartMap).addOnCompleteListener(new OnCompleteListener<Void>() {
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
        feeTxt = findViewById(R.id.priceinviewTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        numberOrderTxt = findViewById(R.id.numberOrderTxt);
        plusBtn = findViewById(R.id.plusBtn);
        minusBtn = findViewById(R.id.minusBtn);
        picFood = findViewById(R.id.foodPic);

    }


}