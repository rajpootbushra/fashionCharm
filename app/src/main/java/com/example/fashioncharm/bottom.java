package com.example.fashioncharm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class bottom extends AppCompatActivity {
    Button btnwhite, btnblack, btnpurple, btnmehroon;
    ImageView btnback1, btncart1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom);

        btnwhite = findViewById(R.id.btnwhite);
        btnblack = findViewById(R.id.btnblack);
        btnpurple = findViewById(R.id.btnpurple);
        btnmehroon = findViewById(R.id.btnmehroon);
        btnback1 = findViewById(R.id.btnback1);
        btncart1 = findViewById(R.id.btncart1);

        final String selectedItemType = "Bottom"; // Replace with actual item type

        btnwhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add the selected item to Firebase
                addItemToFirebase("White", selectedItemType);

                // Save data to SharedPreferences
                saveUserDataInSharedPreferences("White", selectedItemType);

                // Display a message or perform any other action as needed
                Toast.makeText(bottom.this, "Item added to cart", Toast.LENGTH_SHORT).show();
            }
        });

        btnblack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add the selected item to Firebase
                addItemToFirebase("Black", selectedItemType);

                // Save data to SharedPreferences
                saveUserDataInSharedPreferences("Black", selectedItemType);

                // Display a message or perform any other action as needed
                Toast.makeText(bottom.this, "Item added to cart", Toast.LENGTH_SHORT).show();
            }
        });

        btnpurple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add the selected item to Firebase
                addItemToFirebase("Purple", selectedItemType);

                // Save data to SharedPreferences
                saveUserDataInSharedPreferences("Purple", selectedItemType);

                // Display a message or perform any other action as needed
                Toast.makeText(bottom.this, "Item added to cart", Toast.LENGTH_SHORT).show();
            }
        });

        btnmehroon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add the selected item to Firebase
                addItemToFirebase("Mehroon", selectedItemType);

                // Save data to SharedPreferences
                saveUserDataInSharedPreferences("Mehroon", selectedItemType);

                // Display a message or perform any other action as needed
                Toast.makeText(bottom.this, "Item added to cart", Toast.LENGTH_SHORT).show();
            }
        });

        btnback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(bottom.this, mainpage.class);
                startActivity(i);
            }
        });
        btncart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(bottom.this,CartActivity.class);
                startActivity(i);
            }
        });

    }

    private void addItemToFirebase(String itemName, String itemType) {
        DatabaseReference cartRef = FirebaseDatabase.getInstance().getReference().child("user_cart");

        // Assume the user is identified by their username (you can use other identifiers)
        SharedPreferences preferences = getSharedPreferences("UsersData", MODE_PRIVATE);
        String username = preferences.getString("Username", "");

        // Create a unique key for each item added to the cart
        String cartItemId = cartRef.child(username).push().getKey();

        HashMap<String, Object> itemData = new HashMap<>();
        itemData.put("ItemName", itemName);
        itemData.put("ItemType", itemType);

        cartRef.child(username).child(cartItemId).setValue(itemData);
    }

    private void saveUserDataInSharedPreferences(String itemName, String itemType) {
        SharedPreferences pref = getSharedPreferences("UsersData", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("CartItemName", itemName);
        editor.putString("CartItemType", itemType);
        editor.apply();
    }
}
