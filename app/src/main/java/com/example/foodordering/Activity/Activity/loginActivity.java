package com.example.foodordering.Activity.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.foodordering.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class loginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private Button btnSignin;
    private EditText editEmail,editPassword;
    private String email,password;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        progressBar = findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.GONE);

        editEmail = findViewById(R.id.txtEmail);
        editPassword = findViewById(R.id.txtPassword);

        btnSignin = findViewById(R.id.btnLogin);
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = editEmail.getText().toString().trim();
                password = editPassword.getText().toString();

                btnSignin.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);

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
                            btnSignin.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(loginActivity.this,"Failed to login. Check Email and Password!",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}