package com.example.foodordering.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.content.Intent;

import com.example.foodordering.R;

public class MainActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foodview();
            }
        });
    }

    public void foodview() {
        Intent intent = new Intent(this, foodview.class);
        startActivity(intent);
    }
}