package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainMenu extends AppCompatActivity implements View.OnClickListener{

    Button button1;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        button1 = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == button1){
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }

        if (view == button2){
            Intent i = new Intent(this, Help.class);
            startActivity(i);
        }
    }
}
