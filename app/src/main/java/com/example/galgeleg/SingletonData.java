package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import java.util.Calendar;

public class SingletonData extends Application{

    // Galgelogik
    private static Galgelogik galgelogik;

    public SingletonData(){

        // Initialisér galgelogik
        galgelogik = new Galgelogik();
    }


//    public User readUser(int i){
//        Gson gsonReadUser = new Gson();
//        String jsonReadUser = sharedPreferences.getString("" + i,"");
//        User user = gsonReadUser.fromJson(jsonReadUser, User.class);
//        return user;
//    }
//
//    public void saveUser(User user){
//        preferencesEditor = sharedPreferences.edit();
//        User userTemp = new User();
//        userTemp = user;
//        Gson gsonSaveUser = new Gson();
//        String jsonSaveUser = gsonSaveUser.toJson(userTemp);
//        preferencesEditor.putString("" + userTemp.getUserID(), jsonSaveUser);
//        preferencesEditor.commit();
//    }

    public static Galgelogik getGalgelogik() {
        return galgelogik;
    }

    public static void getWordsFromDR(){
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
