package com.example.foodordering.Activity.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.content.Intent;

import com.example.foodordering.Activity.Adapter.CategoryAdapter;
import com.example.foodordering.Activity.Adapter.PopularAdapter;
import com.example.foodordering.Activity.Domain.CategoryDomain;
import com.example.foodordering.Activity.Domain.FoodDomain;
import com.example.foodordering.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//Supun Category View
    private RecyclerView.Adapter adapter,adapter2;
    private RecyclerView recyclerViewCategoryList,recyclerViewPopularList;
//Cat View.................

    private Button button,btnKitchen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Category View.............................................................................
        recyclerViewCategory();
        recyclerViewPopular();



        button = (Button) findViewById(R.id.button);
        btnKitchen = findViewById(R.id.btnKitchen);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foodview();
            }
        });

        btnKitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginview();
            }
        });
    }

    private void recyclerViewPopular() {


        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewPopularList=findViewById(R.id.recyclerView2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);



        ArrayList<FoodDomain> foodlist=new ArrayList<>();
        foodlist.add(new FoodDomain("Black Chicken","pizza_2","",1050.00));
        foodlist.add(new FoodDomain("Cheese Burger","burger_2","",750.00));
        foodlist.add(new FoodDomain("Hot Dog","hotdog_2","",450.00));

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