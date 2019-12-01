package com.mohit.varma.okhttppractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyBackgroundWork myBackgroundWork = new MyBackgroundWork();
        myBackgroundWork.execute("https://itunes.apple.com/search?term=jack+johnson");
    }
}

class MyBackgroundWork extends AsyncTask<String,Void,String>{

    @Override
    protected String doInBackground(String... strings) {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://priaid-symptom-checker-v1.p.rapidapi.com/symptoms?format=json&language=en-gb")
                    .get()
                    .addHeader("x-rapidapi-host", "priaid-symptom-checker-v1.p.rapidapi.com")
                    .addHeader("x-rapidapi-key", "d3bbfd2d44mshc315095e53452aap1027d5jsn01fd5ccccb30")
                    .build();
            Response responses = null;

            try {
                responses = client.newCall(request).execute();
                return responses.message();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
       return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.d("Value", "onPostExecute: "+ s);
    }
}
