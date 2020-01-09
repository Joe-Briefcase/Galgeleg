package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Game extends AppCompatActivity implements View.OnClickListener {

    Galgelogik galgelogik;
    SingletonData singleton;
    TextView textInstructions;
    TextView textWord;
    TextView textUsedLetters;
    Button buttonGuess;
    Button buttonNewGame;
    Button buttonChooseNewWord;
    EditText textGuess;
    ImageView imageGalge;
    ListView listWord;

    int [] textureArrayWin = {
            R.drawable.galge,
            R.drawable.forkert1,
            R.drawable.forkert2,
            R.drawable.forkert3,
            R.drawable.forkert4,
            R.drawable.forkert5,
            R.drawable.forkert6
    };

    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        singleton = (SingletonData)getApplication();

        galgelogik = singleton.getGalgelogik();

        textInstructions = findViewById(R.id.textInstructions);
        textWord = findViewById(R.id.textWord);
        textUsedLetters = findViewById(R.id.textUsedLetters);

        textGuess = findViewById(R.id.guessText);
        buttonGuess = findViewById(R.id.guessButton);
        buttonNewGame = findViewById(R.id.buttonNewGame);
        buttonChooseNewWord = findViewById(R.id.buttonChooseNewWord);
        imageGalge = findViewById(R.id.imageGalge);

        buttonGuess.setOnClickListener(this);
        buttonNewGame.setOnClickListener(this);
        buttonChooseNewWord.setOnClickListener(this);

        textWord.setText(galgelogik.getSynligtOrd());

    }

    @Override
    public void onClick(View view) {

        if (view == buttonGuess){

            if (textGuess.getText().length() == 0)
                return;

            galgelogik.gætBogstav(textGuess.getText().toString());

            if (!galgelogik.erSidsteBogstavKorrekt() && counter < 7) {
                imageGalge.setImageResource(textureArrayWin[counter]);
                counter++;
            }

            textGuess.getText().clear();
            textUsedLetters.setText(galgelogik.getBrugteBogstaver().toString());
            textWord.setText(galgelogik.getSynligtOrd());
            galgelogik.logStatus();
        }

        if (galgelogik.erSpilletVundet()){
            Intent i = new Intent(this, Win.class);
            i.putExtra("guesses", galgelogik.getAntalForkerteBogstaver());
            startActivity(i);
        }

        if (galgelogik.erSpilletTabt()){
            Intent i = new Intent(this, Lose.class);
            i.putExtra("word", galgelogik.getOrdet());
            startActivity(i);
        }

        if (view == buttonNewGame){
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
        }

        if (view == buttonChooseNewWord){
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.layout_word_dialog);
            dialog.setTitle("Vælg ord:");
            listWord = dialog.findViewById(R.id.dialogList);
            ArrayAdapter adapter = new ArrayAdapter(this, R.layout.list_word, galgelogik.muligeOrd);
            listWord.setAdapter(adapter);
            dialog.show();

//            galgelogik.nulstil();
//            textWord.setText(galgelogik.getSynligtOrd());
        }
    }
}
