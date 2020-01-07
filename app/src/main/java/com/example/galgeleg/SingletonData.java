package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.gson.Gson;

import java.util.Calendar;

public class SingletonData extends Application {

    // Data
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor preferencesEditor;
    private String userKey = "userPrefKey";

    // Galgelogik
    private Galgelogik galgelogik;

    @Override
    public void onCreate()
    {
        super.onCreate();
        init();
    }

    public void init(){

        // Initialisér galgelogik
        galgelogik = new Galgelogik();

        // Initialisér sharedpreferences
        sharedPreferences = this.getSharedPreferences("com.example.galgeleg", Context.MODE_PRIVATE);
        preferencesEditor = sharedPreferences.edit();
    }

    // ** Der bruges GSON dependency til at læse og gemme brugere **

    public User readUser(int i){
        Gson gsonReadUser = new Gson();
        String jsonReadUser = sharedPreferences.getString("user" + i,"");
        User user = gsonReadUser.fromJson(jsonReadUser, User.class);
        return user;
    }

    public void saveUser(User user){
        Gson gsonSaveUser = new Gson();
        String jsonSaveUser = gsonSaveUser.toJson(user);
        preferencesEditor.putString("user" + user.getUserID(), "");
        preferencesEditor.commit();
    }

    public Galgelogik getGalgelogik() {
        return galgelogik;
    }

    public void getWordsFromDR(){
        class GetWordsFromInternet extends AsyncTask {
            @Override
            protected Object doInBackground(Object[] objects) {
                try {
                    galgelogik.hentOrdFraDr();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
        new GetWordsFromInternet().execute();
    }
}
