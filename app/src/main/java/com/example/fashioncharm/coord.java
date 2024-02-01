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

public class coord extends AppCompatActivity {
    Button btnpinkprint, btnpink, btnpurpleprint, btnprinted;
    ImageView btnback, btncart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coord);

        btnback = findViewById(R.id.btnback);
        btncart = findViewById(R.id.btncart);
        btnpinkprint = findViewById(R.id.btnpinkprint);
        btnpink = findViewById(R.id.btnpink);
        btnpurpleprint = findViewById(R.id.btnpurpleprint);
        btnprinted = findViewById(R.id.btnprinted);

        btnpinkprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProductToCart("Pink Print", "Printed");
            }
        });

        btnpink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProductToCart("Pink", "Regular");
            }
        });

        btnpurpleprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProductToCart("Purple Print", "Printed");
            }
        });

        btnprinted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProductToCart("Printed", "Regular");
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(coord.this, mainpage.class);
                startActivity(i);
            }
        });
        btncart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(coord.this,CartActivity.class);
                startActivity(i);
            }
        });
    }

    private void addProductToCart(String itemName, String itemType) {
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
        saveUserDataInSharedPreferences(itemName, itemType);

        // Display a message or perform any other action as needed
        Toast.makeText(coord.this, "Item added to cart", Toast.LENGTH_SHORT).show();
    }

    private void saveUserDataInSharedPreferences(String itemName, String itemType) {
        SharedPreferences pref = getSharedPreferences("UsersData", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("CartItemName", itemName);
        editor.putString("CartItemType", itemType);
        editor.apply();
    }
}
