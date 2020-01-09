package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ChooseWord extends AppCompatActivity implements View.OnClickListener {

    SingletonData singleton;
    Galgelogik galgelogik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_word);

        singleton = (SingletonData)getApplication();
        galgelogik = singleton.getGalgelogik();

        final ListView listView = findViewById(R.id.choose_word_list);
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.list_choose_word, R.id.choose_word, galgelogik.muligeOrd);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Object object = ((ListView)view).getItemAtPosition(i);
                Object object = listView.getItemAtPosition(i);
                String str = object.toString();
                galgelogik.nulstil();
                galgelogik.setOrdet(str);
                startGame();
            }
        });
    }

    @Override
    public void onClick(View view) {

    }

    public void startGame(){
        Intent i = new Intent(this, Game.class);
        startActivity(i);
    }
}
