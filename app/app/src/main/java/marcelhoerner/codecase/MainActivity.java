package marcelhoerner.codecase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONObject;

import logic.GPS;
import logic.WeatherInfo;

public class MainActivity extends AppCompatActivity {

    Button showLocation;

    GPS gps;

    WeatherInfo w = new WeatherInfo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showLocation = (Button) findViewById(R.id.getLocation);
        showLocation.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                gps = new GPS(MainActivity.this);

                if(gps.canGetLocation()) {
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    String data = w.getWeatherData(String.valueOf(latitude), String.valueOf(longitude));

                    Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude + "\nCity: " + data, Toast.LENGTH_LONG).show();
                }else{
                    gps.showSettingsAlert();
                }
            }
        });

    }

}
