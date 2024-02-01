// CartActivity.java
package com.example.fashioncharm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    RecyclerView recyclerViewCart;
    Button btnConfirmOrder;
    TextView tvremoveall;
    DatabaseReference cartRef;
    CartAdapter cartAdapter;
    List<CartItem> cartItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerViewCart = findViewById(R.id.recyclerViewCart);
        btnConfirmOrder = findViewById(R.id.btnConfirmOrder);
        tvremoveall=findViewById(R.id.tvremoveAll);

        String username = getSharedPreferences("UsersData", MODE_PRIVATE).getString("Username", "");
        cartRef = FirebaseDatabase.getInstance().getReference().child("user_cart").child(username);

        cartItemList = new ArrayList<>();
        cartAdapter = new CartAdapter(cartItemList);
        recyclerViewCart.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCart.setAdapter(cartAdapter);

        displayCartItems();

        btnConfirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmOrder();
                Intent i=new Intent(CartActivity.this,confirmActivity.class);
                startActivity(i);
            }
        });
        tvremoveall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartRef.removeValue();
            }
        });
    }

    private void displayCartItems() {
        // Fetch and display items from Firebase
        cartRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                cartItemList.clear();

                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {
                    String itemName = itemSnapshot.child("ItemName").getValue(String.class);
                    String itemType = itemSnapshot.child("ItemType").getValue(String.class);
                    String itemPrice=itemSnapshot.child("ItemPrice").getValue(String.class);
                    CartItem cartItem = new CartItem(itemName, itemType,itemPrice);
                    cartItemList.add(cartItem);
                }

                cartAdapter.notifyDataSetChanged(); // Notify the adapter that data has changed
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error if needed
            }
        });
    }

    private void confirmOrder() {

        cartRef.removeValue();
    }
}
