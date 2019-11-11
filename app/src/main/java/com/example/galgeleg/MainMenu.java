package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainMenu extends AppCompatActivity implements View.OnClickListener{

    Button playButton;
    Button helpButton;
    ListView listView;
    ArrayList<String> highscores = new ArrayList<>();
    ArrayList<String> scores = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        // Knapper.
        playButton = findViewById(R.id.button);
        helpButton = findViewById(R.id.button2);
        playButton.setOnClickListener(this);
        helpButton.setOnClickListener(this);

        highscores.add("6 Mr. Crumb");
        highscores.add("3 Bob");
        highscores.add("4 Robert");

        // Initialiser SharedPreferences.
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("myPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        // Gem SharedPreferences.
        Gson gson = new Gson();
        String str = gson.toJson(highscores);
        editor.putString("init", str);
        editor.apply();

        highscores.clear();

        // Hent SharedPreferences.
        Gson gson2 = new Gson();
        String str2 = preferences.getString("init", null);
        Type type = new TypeToken<ArrayList<String>>(){}.getType();
        highscores = gson2.fromJson(str2, type);

        Gson gson3 = new Gson();
        String str3 = preferences.getString("scores", null);
        if (str3 != null) {
            scores = gson3.fromJson(str3, type);
        }
        highscores.addAll(scores);

        // Sortér highscores.
        Collections.sort(highscores, new Sort());

        // ListView
        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.activity_listview, highscores);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

    class Sort implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            int i = Integer.parseInt(s1.substring(0, 1));
            int j = Integer.parseInt(s2.substring(0, 1));
            return i - j;
        }
    }

    @Override
    public void onClick(View view) {
        if (view == playButton){
            Intent i = new Intent(this, Game.class);
            startActivity(i);
        }

        if (view == helpButton){
            Intent i = new Intent(this, Help.class);
            startActivity(i);
        }
    }
}
