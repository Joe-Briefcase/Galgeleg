package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Win extends AppCompatActivity implements View.OnClickListener {

    EditText textSave;
    Button buttonSave;
    int attemptNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        TextView attemptView = findViewById(R.id.textAttemptNumber);
        buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(this);
        textSave = findViewById(R.id.textSave);
        Intent i = getIntent();
        attemptNumber = i.getIntExtra("guesses", 0);
        attemptView.setText("" + attemptNumber);
    }

    public void save(){
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("myPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String strSave = attemptNumber + " " + textSave.getText().toString();
        ArrayList<String> highscores = new ArrayList<>();
        highscores.add(strSave);
        String str = gson.toJson(highscores);
        editor.putString("scores", str);
        editor.apply();
    }

    @Override
    public void onClick(View view) {
        if (view == buttonSave){
            save();
            Intent i = new Intent(this, MainMenu.class);
            startActivity(i);
        }
    }
}
