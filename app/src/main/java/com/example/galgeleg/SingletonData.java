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
    private static int currentID;

    public SingletonData(){
        currentID = 1;
        galgelogik = new Galgelogik();
    }

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

    public static int getCurrentID() {
        return currentID;
    }

    public static void setCurrentID(int i) {
        currentID = i;
    }
}
