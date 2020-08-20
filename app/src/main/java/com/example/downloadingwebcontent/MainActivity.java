package com.example.downloadingwebcontent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public class DownloadTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... urls) {
            String result="";
            URL url;
            HttpURLConnection urlConnection;
            try {                       //cannot turn any string into URL ex:-"4"
                url=new URL(urls[0]); //convert string into url
                urlConnection=(HttpURLConnection) url.openConnection();
                InputStream in=urlConnection.getInputStream();
                InputStreamReader reader=new InputStreamReader(in);
                int data=reader.read();
                while(data!=-1){
                    char current=(char) data;
                    result = result + current;
                    data=reader.read(); //read next html code one by one
                }
                return result;

            } catch (Exception e) {
                e.printStackTrace();
                return "failed";
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String result=null;
        DownloadTask Task=new DownloadTask();
        try {
            result=Task.execute("https://www.zappycode.com").get();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        Log.i("Result",result);
    }

}