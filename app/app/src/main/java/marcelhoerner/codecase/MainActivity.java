package marcelhoerner.codecase;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import logic.GPS;
import logic.ConnectToWeather;
import logic.ParseWeather;
import logic.Weather;

public class MainActivity extends AppCompatActivity {

    Button showLocation;
    GPS gps;

    private TextView name;
    private TextView temp;
    private TextView type;
    private TextView press;
    private TextView speed;
    private TextView humi;


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

        //on button click start the whole process
        showLocation = (Button) findViewById(R.id.getLocation);
        showLocation.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                gps = new GPS(MainActivity.this);

                //get GPS data and prepare it for the search process
                if(gps.canGetLocation()) {
                    String[] latlon = {gps.getLatitude(), gps.getLongitude()};
                    String latitude = gps.getLatitude();
                    String longitude = gps.getLongitude();

                    AsyncWeatherTask wtask = new AsyncWeatherTask();
                    wtask.execute(latlon);

                }else{
                    gps.showSettingsAlert();
                }
            }
        });

    }

    private class AsyncWeatherTask extends AsyncTask<String, Void, Weather>{


        //start the search process
        protected Weather doInBackground(String... params) {
            Weather w = new Weather();
            String data = (new ConnectToWeather()).getWeatherData(params[0],params[1]);

            try{
                w = (new ParseWeather()).getWeatherData(data);
            } catch (JSONException e){
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




    public void openList(View view){
        Intent intent = new Intent(this, CitiesListActivity.class);
        startActivity(intent);
    }

}
