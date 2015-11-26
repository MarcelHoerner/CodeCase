package marcelhoerner.codecase;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

import logic.CitiesList;
import logic.ConnectToWeather;
import logic.ParseWeather;
import logic.SearchCities;
import logic.Weather;

public class CitiesListActivity extends AppCompatActivity {

    ListView list;
    String lat;
    String lon;

    private String[] name;
    private String[] temp;
    private String[] type;
    private String[] humi;
    private String[] speed;
    private String[] press;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities_list);

        //get putExtra - GPS position
        lat = getIntent().getExtras().getString("lat");
        lon = getIntent().getExtras().getString("lon");
        name = getIntent().getExtras().getStringArray("name");
        type = getIntent().getExtras().getStringArray("type");
        temp = getIntent().getExtras().getStringArray("temp");
        speed = getIntent().getExtras().getStringArray("speed");
        humi = getIntent().getExtras().getStringArray("humi");
        press = getIntent().getExtras().getStringArray("press");

     //   Toast.makeText(getApplicationContext(), "2222" + finalValues[0]+ finalValues[1], Toast.LENGTH_SHORT).show();


        //fills the fragment list
        CitiesList adapter = new
                CitiesList(CitiesListActivity.this, name, temp, type, humi, speed, press);
        list=(ListView)findViewById(R.id.listview_cities);
        list.setAdapter(adapter);
    }

}
