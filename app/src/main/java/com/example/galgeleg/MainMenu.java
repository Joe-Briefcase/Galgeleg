package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainMenu extends AppCompatActivity implements View.OnClickListener{

    Button button1;
    Button button2;
    ListView listView;
    String[] highscores = new String[]{"1. William", "2. Elliott", "3. Matt"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        // Knapper.
        button1 = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

        // ListView
        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.activity_listview, highscores);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
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
