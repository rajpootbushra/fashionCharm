package com.example.fashioncharm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class mainpage extends AppCompatActivity {
Button btntop,btnbottom,btnparty,btncoord;
ImageView btnCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        btntop=findViewById(R.id.btntop);
        btnbottom=findViewById(R.id.btnbottom);
        btnparty=findViewById(R.id.btnparty);
        btncoord=findViewById(R.id.btncoord);
        btnCart=findViewById(R.id.btnCart);
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mainpage.this, CartActivity.class);
                startActivity(i);
            }
        });

        btnbottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(mainpage.this,bottom.class);
                startActivity(i);
            }
        });
        btncoord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(mainpage.this,coord.class);
                startActivity(i);
            }
        });
    }
}