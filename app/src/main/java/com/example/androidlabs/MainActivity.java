package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

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
    private static final String TAG = "ToDoActivity";
    private final ArrayList<StarWarItem> starWarItems = new ArrayList<>();
    private ItemListAdapter adapter;
    private static final String SW_BASE_URL = "https://swapi.dev";

    class CatImages extends AsyncTask<String, Integer, String> {
        private Bitmap currentImage;

        @Override
        protected String doInBackground(String... strings) {
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
                    JSONObject jsonObject = new JSONObject(json);
                    String swName = (String) jsonObject.get("name");
                    String swHeight = (String) jsonObject.get("height");
                    String swMass = (String) jsonObject.get("mass");

                    publishProgress();
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

        }
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        CatImages catImages = new CatImages();
//        catImages.execute(BASE_CAT_URL);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);


//        button.setOnClickListener((click) -> {
//            int urgent = switchUrgent.isChecked() ? 1 : 0;
//            int id = sqlHelper.add(editText.getText().toString(), urgent);
//            ToDoItem toDoItem = new ToDoItem(id, editText.getText().toString(), switchUrgent.isChecked());
//            toDoItemList.add(toDoItem);
//            editText.setText("");
//            adapter.notifyDataSetChanged();
//        });

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter = new ItemListAdapter(this, starWarItems));
        listView.setOnItemClickListener((parent, view, position, id) -> {
//            AlertDialog.Builder alertDiBuilder = new AlertDialog.Builder(this);
//            alertDiBuilder.setTitle(R.string.alert_title)
//                    .setMessage(getString(R.string.alert_message) + (position + 1))
//                    .setPositiveButton(R.string.alert_yes, (dialog, arg) -> {
//                        sqlHelper.delete(toDoItemList.get(position).getId());
//                        toDoItemList.remove(position);
//                        adapter.notifyDataSetChanged();
//                        dialog.dismiss();
//                    })
//                    .setNegativeButton(R.string.alert_no, (dialog, arg) -> {
//                    });
//            alertDiBuilder.create().show();
        });
    }
}