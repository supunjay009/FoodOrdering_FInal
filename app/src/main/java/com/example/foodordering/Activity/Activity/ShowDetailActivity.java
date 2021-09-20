package com.example.foodordering.Activity.Activity;


import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.os.Bundle;


import com.example.foodordering.R;

public class ShowDetailActivity extends AppCompatActivity {
    int minteger = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodview);
    }
    public void increaseInteger(View view) {
        minteger = minteger + 1;
        display(minteger);

    }public void decreaseInteger(View view) {

        if(minteger!=0)
        { minteger = minteger - 1;
            display(minteger);
        }
        else
        {
            minteger = 0;
        }

    }

    private void display(int number) {
        TextView displayInteger = (TextView) findViewById(R.id.qty);
        displayInteger.setText("" + number);
    }
}