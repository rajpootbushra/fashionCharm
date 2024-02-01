package com.example.fashioncharm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class createAccount extends AppCompatActivity {
    Button btncreate;
    TextView tvlogin2;
    EditText etemail, etusername2, etpassword2, etconfirmpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
       init();
        btncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email, Password, Username, confirmPassword;
                Email = etemail.getText().toString().trim();
                Username = etusername2.getText().toString().trim();
                Password = etpassword2.getText().toString();
                confirmPassword = etconfirmpassword.getText().toString();

                if (Email.isEmpty() || Username.isEmpty() || Password.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(createAccount.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                } else if (Password.equals(confirmPassword)) {

                    Intent i = new Intent(createAccount.this, loginActivity.class);
                    i.putExtra("Email", Email);
                    i.putExtra("Username", Username);
                    i.putExtra("Password", Password);
                    i.putExtra("ConfirmPassword", confirmPassword);
                    Toast.makeText(createAccount.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                    startActivity(i);
                    HashMap<String,Object> data=new HashMap<>();
                    data.put("Email",Email);
                    data.put("Username",Username);
                    data.put("Password",Password);
                    data.put("ConfirmPassword",confirmPassword);

                    FirebaseDatabase.getInstance().getReference().child("users").push().setValue(data);

                } else {
                    Toast.makeText(createAccount.this, "Password not match", Toast.LENGTH_SHORT).show();
                }
            }
        });
        tvlogin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(createAccount.this, loginActivity.class);
                startActivity(i);

            }
        });
    }
    private void init(){

        btncreate = findViewById(R.id.btncreate);
        tvlogin2 = findViewById(R.id.tvlogin2);
        etemail = findViewById(R.id.etemail);
        etusername2 = findViewById(R.id.etusername2);
        etpassword2 = findViewById(R.id.etpassword2);
        etconfirmpassword = findViewById(R.id.etConfirmpassword);
    }
}