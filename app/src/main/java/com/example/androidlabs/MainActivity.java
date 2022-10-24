package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import android.widget.ProgressBar;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;

import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;

import java.net.URL;
import java.nio.charset.StandardCharsets;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyMainActivity";
    private static final String BASE_CAT_URL = "https://cataas.com";

    class CatImages extends AsyncTask<String, Integer, String> {
        private Bitmap currentImage;

        @Override
        protected String doInBackground(String... strings) {
            while (true) {
                HttpURLConnection urlConnection = null;
                String catBaseURL = strings[0];
                try {
                    URL url = new URL(catBaseURL + "/cat?json=true");
                    urlConnection = (HttpURLConnection) url.openConnection();

                    InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());

                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,
                            StandardCharsets.UTF_8), 8);
                    StringBuilder sb = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        sb.append(line).append("\n");
                    }

                    String json = sb.toString();
                    JSONObject jsonObject = new JSONObject(json);
                    String catImageURL = (String) jsonObject.get("url");
                    String catImageId = (String) jsonObject.get("_id");
                    String catImageFileName = catImageId + ".png";
                    String catImageFolder = getApplicationContext().getFilesDir() + "/";

                    Log.i(TAG, "Cat image Id:" + catImageId);

                    File file = new File(catImageFolder, catImageFileName);

                    if(!file.exists()){
                        Log.i(TAG, "Loading cat image from Internet.");
                        URL catURL = new URL(catBaseURL + catImageURL);

                        urlConnection = (HttpURLConnection) catURL.openConnection();
                        urlConnection.setDoInput(true);
                        urlConnection.connect();

                        int responseCode = urlConnection.getResponseCode();

                        if (responseCode == HttpURLConnection.HTTP_OK) {
                            inputStream = urlConnection.getInputStream();
                            FileOutputStream outputStream = openFileOutput(
                                    catImageFileName,
                                    Context.MODE_PRIVATE);

                            currentImage = BitmapFactory.decodeStream(inputStream);
                            currentImage.compress(Bitmap.CompressFormat.PNG, 80, outputStream);

                            outputStream.flush();
                            outputStream.close();
                            inputStream.close();
                        }
                    } else {
                        Log.i(TAG, "Loading cat image from the local file.");
                        BitmapFactory.decodeFile(catImageFolder + catImageFileName);
                    }

                    for (int i = 0; i < 25; i++) {
                        try {
                            publishProgress(i);
                            Thread.sleep(20);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if(urlConnection != null){
                        urlConnection.disconnect();
                    }
                }
            }
        }

        public void onProgressUpdate(Integer ... args) {
            ProgressBar progressBar = findViewById(R.id.progressBar);
            progressBar.setProgress(args[0]);

            ImageView imageView = findViewById(R.id.imageView);
            imageView.setImageBitmap( currentImage);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CatImages catImages = new CatImages();
        catImages.execute(BASE_CAT_URL);
    }
}