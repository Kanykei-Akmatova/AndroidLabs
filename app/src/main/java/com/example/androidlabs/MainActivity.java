package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String SW_BASE_URL = "https://swapi.dev";
    private ListView listView;

    class StarWarsTask extends AsyncTask<String,String, ArrayList<StarWarObject>> {

        @Override
        protected ArrayList<StarWarObject> doInBackground(String... strings) {
            while (true) {
                HttpURLConnection urlConnection = null;
                String swURL = strings[0];
                try {
                    URL url = new URL(swURL + "/api/people/?format=json");
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
                    ArrayList<StarWarObject> starWarItems = new ArrayList<>();

                    JSONArray characters = new JSONObject(json).getJSONArray("results");
                    for (int index = 0; index < characters.length(); index++) {
                        JSONObject jsonObject = characters.getJSONObject(index);

                        String swName = (String) jsonObject.get(StarWarsConstant.NAME);
                        String swHeight = (String) jsonObject.get(StarWarsConstant.HEIGHT);
                        String swMass = (String) jsonObject.get(StarWarsConstant.MASS);

                        starWarItems.add(new StarWarObject(swName, swHeight, swMass));
                    }

                    return starWarItems;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if(urlConnection != null){
                        urlConnection.disconnect();
                    }
                }
            }
        }

        @Override
        protected void onPostExecute(ArrayList<StarWarObject> starWarObjects) {
            super.onPostExecute(starWarObjects);
            ItemListAdapter adapter = new ItemListAdapter(getApplicationContext(), starWarObjects);
            listView.setAdapter(adapter);
            boolean isTablet = findViewById(R.id.frameLayout) != null;
            listView.setOnItemClickListener((list, item, position, id) -> {
                Bundle dataToPass = new Bundle();
                dataToPass.putString(StarWarsConstant.NAME, starWarObjects.get(position).getName());
                dataToPass.putString(StarWarsConstant.HEIGHT, starWarObjects.get(position).getHeight());
                dataToPass.putString(StarWarsConstant.MASS, starWarObjects.get(position).getMass());

                if(isTablet)
                {
                    DetailsFragment dFragment = new DetailsFragment();
                    dFragment.setArguments(dataToPass);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frameLayout, dFragment)
                            .commit();
                }
                else //isPhone
                {
                    Intent nextActivity = new Intent(MainActivity.this, EmptyActivity.class);
                    nextActivity.putExtras(dataToPass);
                    startActivity(nextActivity);
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        StarWarsTask swData = new StarWarsTask();
        swData.execute(SW_BASE_URL);

        listView = findViewById(R.id.listView);
    }
}