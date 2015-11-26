package marcelhoerner.codecase;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.json.JSONException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import logic.GPS;
import logic.ConnectToWeather;
import logic.ParseWeather;
import logic.SearchCities;
import logic.Weather;

public class MainActivity extends AppCompatActivity {

    Button showLocation;
    Button showCities;
    GPS gps;
    String[] latlon = new String[2];
    String[] finalValues;

    private TextView name;
    private TextView temp;
    private TextView type;
    private TextView press;
    private TextView speed;
    private TextView humi;

    private ArrayList<String> nameA = new ArrayList<String>();
    private ArrayList<String> tempA = new ArrayList<String>();
    private ArrayList<String> typeA = new ArrayList<String>();
    private ArrayList<String> humiA = new ArrayList<String>();
    private ArrayList<String> speedA = new ArrayList<String>();
    private ArrayList<String> pressA = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //assign text views
        name = (TextView) findViewById(R.id.name);
        temp = (TextView) findViewById(R.id.temp);
        type = (TextView) findViewById(R.id.type);
        press = (TextView) findViewById(R.id.press);
        speed = (TextView) findViewById(R.id.speed);
        humi = (TextView) findViewById(R.id.humi);

        //hide button
        showCities = (Button) findViewById(R.id.buttonCities);
        showCities.setVisibility(View.INVISIBLE);

        //on button click start the whole process
        showLocation = (Button) findViewById(R.id.getLocation);
        showLocation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                gps = new GPS(MainActivity.this);

                //get GPS data and prepare it for the search process
                if (gps.canGetLocation()) {
                    latlon[0] = gps.getLatitude();
                    latlon[1] = gps.getLongitude();

                    AsyncWeatherTask wtask = new AsyncWeatherTask();
                    wtask.execute(latlon);

                    //read city data
                    InputStream input = null;
                    try{
                        input = getAssets().open("DE.txt");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                    //get cities nearby
                    SearchCities Sc = new SearchCities();
                    finalValues = Sc.getLatLonValues(latlon[0], latlon[1], input);
                    //get weather info about cities nearby
                    for( int i = 0; i < finalValues.length; i = i+2){
                        AsyncWeatherTaskList wtask2 = new AsyncWeatherTaskList();
                        wtask2.execute(new String[]{finalValues[i],finalValues[i+1]});

                        //show button when calculation is finish
                        if((i+2)==finalValues.length){
                            showCities.setVisibility(View.VISIBLE);
                        }
                    }

            }

            else

            {
                gps.showSettingsAlert();
            }
        }
        });

    }

    private class AsyncWeatherTask extends AsyncTask<String, Void, Weather> {


        //start the search process
        protected Weather doInBackground(String... params) {
            Weather w = new Weather();
            String data = (new ConnectToWeather()).getWeatherData(params[0], params[1]);

            try {
                w = (new ParseWeather()).getWeatherData(data);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return w;
        }

        //update the UI
        protected void onPostExecute(Weather w) {
            super.onPostExecute(w);

            name.setText(w.getName());
            temp.setText(w.getTemp());
            type.setText(w.getType());
            press.setText(w.getPress());
            speed.setText(w.getSpeed());
            humi.setText(w.getHumi());
        }
    }

    private class AsyncWeatherTaskList extends AsyncTask<String, Void, Weather> {


        //start the search process
        protected Weather doInBackground(String... params) {
            Weather w = new Weather();
            String data = (new ConnectToWeather()).getWeatherData(params[0], params[1]);

            try {
                w = (new ParseWeather()).getWeatherData(data);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return w;
        }

        //update the UI
        protected void onPostExecute(Weather w) {
            super.onPostExecute(w);

            nameA.add(w.getName());
            tempA.add(w.getTemp());
            typeA.add(w.getType());
            pressA.add(w.getPress());
            speedA.add(w.getSpeed());
            humiA.add(w.getHumi());
        }
    }


    public void openList(View view){
        Intent intent = new Intent(this, CitiesListActivity.class);
        intent.putExtra("lat",latlon[0]);
        intent.putExtra("lon",latlon[1]);
        intent.putExtra("name", nameA.toArray(new String[nameA.size()]));
        intent.putExtra("type", typeA.toArray(new String[typeA.size()]));
        intent.putExtra("temp", tempA.toArray(new String[tempA.size()]));
        intent.putExtra("speed", speedA.toArray(new String[speedA.size()]));
        intent.putExtra("humi", humiA.toArray(new String[humiA.size()]));
        intent.putExtra("press", pressA.toArray(new String[pressA.size()]));
        startActivity(intent);
    }

}
