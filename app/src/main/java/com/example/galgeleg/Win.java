package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
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
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor preferencesEditor;
    String userKey = "userPrefKey";
    Context context;
    SingletonData singleton;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        singleton = (SingletonData)getApplication();

        context = getApplicationContext();
        sharedPreferences = context.getSharedPreferences(userKey, Context.MODE_PRIVATE);
        TextView attemptView = findViewById(R.id.textAttemptNumber);
        buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(this);
        textSave = findViewById(R.id.textSave);
        Intent i = getIntent();
        attemptNumber = i.getIntExtra("guesses", 0);
        attemptView.setText("" + attemptNumber);
        mediaPlayer = MediaPlayer.create(this, R.raw.win);
        mediaPlayer.start();
    }

    public void save(){
        User user = new User();
        user.setUserName(textSave.getText().toString());
        user.setUserID(singleton.getCurrentID());
        user.setUserScore(singleton.getGalgelogik().getAntalForkerteBogstaver());
        user.setUserWord(singleton.getGalgelogik().getOrdet());

        preferencesEditor = sharedPreferences.edit();
        Gson gsonSaveUser = new Gson();
        String jsonSaveUser = gsonSaveUser.toJson(user);
        preferencesEditor.putString("" + user.getUserID(), jsonSaveUser);
        preferencesEditor.commit();
        singleton.setCurrentID(singleton.getCurrentID() + 1);
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
