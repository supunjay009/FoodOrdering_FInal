package com.example.foodordering.Activity.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodordering.Activity.Adapter.CategoryAdapter;
import com.example.foodordering.Activity.Adapter.PopularAdapter;
import com.example.foodordering.Activity.Domain.CategoryDomain;
import com.example.foodordering.Activity.Domain.Food;
import com.example.foodordering.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//Supun Category View
    private RecyclerView.Adapter adapter;
    private PopularAdapter popularAdapter;
    private RecyclerView recyclerViewCategoryList,recyclerViewPopularList;
    private LinearLayout kitchenbtn,add_btn,setting_btn;
    private ArrayList<Food> foodArrayList;
    private EditText editText;

//Cat View.................
    private Button btnKitchen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText =(EditText) findViewById(R.id.editTextTextPersonName);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(!s.toString().isEmpty())
                {
                    seacrch(s.toString());
                }
                else
                {
                    seacrch("");
                }
            }
        });








        //Category View.............................................................................
        recyclerViewCategory();
        recyclerViewPopular();

        kitchenbtn = findViewById(R.id.kitchen_btn);
        kitchenbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginview();
            }

        });

        add_btn = findViewById(R.id.add_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mapview();
            }



        });
        setting_btn = findViewById(R.id.settings_btn);
        setting_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setting();
            }



        });
        FloatingActionButton cartbtn = (FloatingActionButton) findViewById(R.id.cart_btn);
        cartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,cartlist.class);
                startActivity(intent);



            }
        });


    }

    private void seacrch(String toString) {

        ArrayList<Food> filterdList = new ArrayList<>();

        for(Food items:foodArrayList )
        {
            if(items.getName().toLowerCase().startsWith(toString.toLowerCase()))
        {
            filterdList.add(items);
        }

        }
        popularAdapter.filterList(filterdList);
    }

    private void recyclerViewPopular() {

        recyclerViewPopularList = findViewById(R.id.recyclerView2);

        foodArrayList = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference foodRef = database.getReference("food");
        foodRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                foodArrayList.clear();
                for(DataSnapshot snapshot1: snapshot.getChildren()) {
                    Food food = snapshot1.getValue(Food.class);
                    foodArrayList.add(food);
                }
                popularAdapter.notifyDataSetChanged();
                //Toast.makeText(MainActivity.this,String.valueOf(foodArrayList.size()),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//        foodArrayList.add(new FoodDomain("Pizza - Black Chicken","pizza_2","Flavoursome pieces of black chicken and crunchy onion with a double layer of mozzarella cheese.",1050.00));
//        foodArrayList.add(new FoodDomain("Cheese Burger","burger_2","Features a flame-grilled patty made from plants topped with tomatoes, lettuce, mayo, ketchup, pickles, and onions. *For guests looking for a meat-free option, a non-broiler method of preparation is available upon request. Entreé only",750.00));
//        foodArrayList.add(new FoodDomain("Hot Dog","hotdog_2","Shallow fry Venky's Chicken Hot Dog in a non stick pan with very little oil for 2 to 3 minutes or put in a steamer for 2 to 3 minutes.",450.00));
//        foodArrayList.add(new FoodDomain("Donut","donut_2","Good One",300.00));
//        foodArrayList.add(new FoodDomain("Beverages","drink_2","Availble Pepsi ans Coke",150.00));

        popularAdapter = new PopularAdapter(foodArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);
        recyclerViewPopularList.setAdapter(popularAdapter);
    }


    // ..........Category View........................................................................
    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewCategoryList=findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> categoryList=new ArrayList<>();
        categoryList.add(new CategoryDomain("Pizza","cat_1"));
        categoryList.add(new CategoryDomain("Burger","cat_2"));
        categoryList.add(new CategoryDomain("HotDog","cat_3"));
        categoryList.add(new CategoryDomain("Drink","cat_4"));
        categoryList.add(new CategoryDomain("Donut","cat_5"));

        adapter=new CategoryAdapter(categoryList);
        recyclerViewCategoryList.setAdapter(adapter);

    }

    public void foodview() {
        Intent intent = new Intent(this, ShowDetailActivity.class);
        startActivity(intent);
    }

    public void loginview() {
        Intent intent = new Intent(this, loginActivity.class);
        startActivity(intent);
    }

    public void Mapview() {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void setting() {
        Intent intent = new Intent(this, info.class);
        startActivity(intent);
    }
}