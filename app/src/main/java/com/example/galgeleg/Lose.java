package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Lose extends AppCompatActivity implements View.OnClickListener {

    Button newGame;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lose);

        newGame = findViewById(R.id.button3);
        newGame.setOnClickListener(this);

        TextView word = findViewById(R.id.textLose3);
        Intent i = getIntent();
        String str = i.getStringExtra("word");
        word.setText(str);
        mediaPlayer = MediaPlayer.create(this, R.raw.lose);
        mediaPlayer.start();
    }

    @Override
    public void onClick(View view) {
        if (view == newGame){
            Intent i = new Intent(this, Game.class);
            startActivity(i);
        }
    }
}
