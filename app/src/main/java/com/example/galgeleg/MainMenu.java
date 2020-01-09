package com.example.galgeleg;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
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
    SingletonData singleton;
    LottieAnimationView loading;
    ArrayList<User> userArrayList = new ArrayList<>();
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor preferencesEditor;
    String userKey = "userPrefKey";
    Context context;
    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        singleton = (SingletonData)getApplication();
        context = getApplicationContext();
        sharedPreferences = context.getSharedPreferences(userKey, Context.MODE_PRIVATE);

        /**
         * Udkommentér for at nulstille SharedPreferences
         */
//        preferencesEditor = sharedPreferences.edit();
//        preferencesEditor.clear();
//        preferencesEditor.commit();


        // Knapper.
        playButton = findViewById(R.id.button);
        helpButton = findViewById(R.id.button2);
        playButton.setOnClickListener(this);
        helpButton.setOnClickListener(this);


        User user = new User();
        user.setUserName("Bob");
        user.setUserID(singleton.getCurrentID());
        user.setUserScore(6);
        user.setUserWord("kartoffel");
        saveUser(user);

        User user2 = new User();
        user2.setUserName("Kenned");
        user2.setUserID(singleton.getCurrentID());
        user2.setUserScore(12);
        user2.setUserWord("kage");
        saveUser(user2);

        User user3 = new User();
        user3.setUserName("Jack");
        user3.setUserID(singleton.getCurrentID());
        user3.setUserScore(2);
        user3.setUserWord("skipperlabskovs");
        saveUser(user3);


        int i = 1;
        while (readUser(i) != null){
            User userLoad = readUser(i);
            userArrayList.add(userLoad);
            i++;
        }

        // Hent ord fra DR

        Context context = getApplicationContext();
        String text = "Henter ord fra DR";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.CENTER,0, 0);
        toast.show();

        singleton.getWordsFromDR();

        // Sortér highscores.
        Collections.sort(userArrayList, new Sort());

        // ListView
        listView = findViewById(R.id.listView);

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.list_rank, R.id.name, userArrayList){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                if (position == 0){
                    ImageView image = view.findViewById(R.id.img);
                    image.setImageResource(R.drawable.one);
                } else if (position == 1){
                    ImageView image = view.findViewById(R.id.img);
                    image.setImageResource(R.drawable.two);
                } else if (position == 2){
                    ImageView image = view.findViewById(R.id.img);
                    image.setImageResource(R.drawable.three);
                }

                TextView name = view.findViewById(R.id.name);
                name.setText(userArrayList.get(position).getUserName());
                TextView score = view.findViewById(R.id.score);
                score.setText(Integer.toString(userArrayList.get(position).getUserScore()));
                TextView word = view.findViewById(R.id.word);
                word.setText(userArrayList.get(position).getUserWord());

                return view;
            }

            @Override
            public int getCount() {
                if (userArrayList != null){
                    return Math.min(userArrayList.size(), 3);
                } else {
                    return 0;
                }
            }
        };

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

    class Sort implements Comparator<User> {
        @Override
        public int compare(User s1, User s2) {
            return s1.getUserScore() - s2.getUserScore();
        }
    }

    @Override
    public void onClick(View view) {
        if (view == playButton){
            final MediaPlayer playSound = MediaPlayer.create(this, R.raw.play);
            playSound.start();
            Intent i = new Intent(this, Game.class);
            startActivity(i);
        }

        if (view == helpButton){
            Intent i = new Intent(this, Help.class);
            startActivity(i);
        }
    }

    public User readUser(int i){
        Gson gsonReadUser = new Gson();
        String jsonReadUser = sharedPreferences.getString("" + i,"");
        if (jsonReadUser.length() == 0){
            return null;
        }
        User user = gsonReadUser.fromJson(jsonReadUser, User.class);
        return user;
    }

    public void saveUser(User user){
        preferencesEditor = sharedPreferences.edit();
        Gson gsonSaveUser = new Gson();
        String jsonSaveUser = gsonSaveUser.toJson(user);
        preferencesEditor.putString("" + user.getUserID(), jsonSaveUser);
        preferencesEditor.commit();
        singleton.setCurrentID(singleton.getCurrentID() + 1);
    }

    public void onDRFinish(){
        Context context = getApplicationContext();
        String text = "Færdig";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.CENTER,0, 0);
        toast.show();

        loading.animate().alpha(0f).setDuration(3000).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                loading.setVisibility(View.GONE);
            }
        });
    }
}
