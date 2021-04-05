package com.shrirajsinh.webcontentbeta;

import androidx.appcompat.app.AppCompatActivity;


import android.os.AsyncTask;
import android.os.Bundle;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity
{
    public class DownloadTask extends AsyncTask<String, Void, String>
    {
        String result = "";
        URL url = null;
        HttpURLConnection urlConnection;

        @Override
        protected String doInBackground(String... urls)
        {
            try
            {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection)url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while (data != -1)
                {
                    char current = (char) data;
                    result = result+current;
                    data = reader.read();

                }

                return result;


            }
            catch (Exception e)
            {
                e.printStackTrace();
                return String.valueOf(e);

            }

        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String result = null;
        DownloadTask task = new DownloadTask();

        try {
            result = task.execute("https://wallpapercave.com/asta-demon-wallpapers").get();
        } catch (Exception e)
        {
            e.printStackTrace();
            System.out.println(e);
        }

        System.out.println(result);
    }
}