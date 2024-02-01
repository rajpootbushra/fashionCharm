// loginActivity.java
package com.example.fashioncharm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class loginActivity extends AppCompatActivity {
    Button btnlogin;
    EditText etusername, etpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        String intentEmail, intentUsername, intentPassword, intentConfirmPassword;
        Intent i = getIntent();
        intentEmail = i.getStringExtra("Email");
        intentUsername = i.getStringExtra("Username");
        intentPassword = i.getStringExtra("Password");
        intentConfirmPassword = i.getStringExtra("ConfirmPassword");

        init();

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loginUsername = etusername.getText().toString().trim();
                String loginPassword = etpassword.getText().toString();

                DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("users");

                usersRef.orderByChild("Username").equalTo(loginUsername).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                                String storedPassword = userSnapshot.child("Password").getValue(String.class);

                                if (loginPassword.equals(storedPassword)) {
                                    // Password matches, login successful

                                    // Save data to SharedPreferences
                                    SharedPreferences pref = getSharedPreferences("UsersData", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = pref.edit();
                                    editor.putString("Username", loginUsername);
                                    editor.putString("Password", loginPassword);
                                    editor.apply();

                                    // Start the mainpage activity
                                    Intent i = new Intent(loginActivity.this, mainpage.class);
                                    startActivity(i);
                                    finish();
                                } else {
                                    // Password doesn't match
                                    Toast.makeText(loginActivity.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } else {
                            // Username not found in the database
                            Toast.makeText(loginActivity.this, "Invalid Username", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(loginActivity.this, "Database Error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    private void init() {
        btnlogin = findViewById(R.id.btnlogin);
        etusername = findViewById(R.id.etusername);
        etpassword = findViewById(R.id.etpassword);
    }
}
