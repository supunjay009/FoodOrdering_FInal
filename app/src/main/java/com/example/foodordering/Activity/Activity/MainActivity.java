package com.example.foodordering.Activity.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodordering.Activity.Adapter.CategoryAdapter;
import com.example.foodordering.Activity.Adapter.PopularAdapter;
import com.example.foodordering.Activity.Domain.CategoryDomain;
import com.example.foodordering.Activity.Domain.FoodDomain;
import com.example.foodordering.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//Supun Category View
    private RecyclerView.Adapter adapter,adapter2;
    private RecyclerView recyclerViewCategoryList,recyclerViewPopularList;
    private LinearLayout kitchenbtn,add_btn;
//Cat View.................

    private Button btnKitchen;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                Intent intent = new Intent( MainActivity.this,location.class);
                startActivity(intent);
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



    private void recyclerViewPopular() {


        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewPopularList=findViewById(R.id.recyclerView2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);



        ArrayList<FoodDomain> foodlist=new ArrayList<>();
        foodlist.add(new FoodDomain("Pizza - Black Chicken","pizza_2","Flavoursome pieces of black chicken and crunchy onion with a double layer of mozzarella cheese.",1050.00));
        foodlist.add(new FoodDomain("Cheese Burger","burger_2","Features a flame-grilled patty made from plants topped with tomatoes, lettuce, mayo, ketchup, pickles, and onions. *For guests looking for a meat-free option, a non-broiler method of preparation is available upon request. Entreé only",750.00));
        foodlist.add(new FoodDomain("Hot Dog","hotdog_2","Shallow fry Venky's Chicken Hot Dog in a non stick pan with very little oil for 2 to 3 minutes or put in a steamer for 2 to 3 minutes.",450.00));
        foodlist.add(new FoodDomain("Donut","donut_2","Good One",300.00));
        foodlist.add(new FoodDomain("Beverages","drink_2","Availble Pepsi ans Coke",150.00));

        adapter2=new PopularAdapter(foodlist);
        recyclerViewPopularList.setAdapter(adapter2);
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
}