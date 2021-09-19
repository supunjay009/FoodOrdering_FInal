package com.example.foodordering.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.foodordering.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class loginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private Button btnSignin;
    private EditText editEmail,editPassword;
    private String email,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        mAuth = FirebaseAuth.getInstance();

        editEmail = findViewById(R.id.txtEmail);
        editPassword = findViewById(R.id.txtPassword);

        btnSignin = findViewById(R.id.btnLogin);
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = editEmail.getText().toString().trim();
                password = editPassword.getText().toString();

                userLogin();
            }
        });
    }

    public void kitchenView() {
        Intent intent = new Intent(this, kitchenActivity.class);
        startActivity(intent);
    }

    private void userLogin(){
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(loginActivity.this,"Login Successful!",Toast.LENGTH_LONG).show();
                            kitchenView();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(loginActivity.this,"Failed to login. Check Email and Password!",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}