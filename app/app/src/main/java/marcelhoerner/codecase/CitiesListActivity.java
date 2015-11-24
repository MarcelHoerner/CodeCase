package marcelhoerner.codecase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import logic.CitiesList;

public class CitiesListActivity extends AppCompatActivity {

    ListView list;

    private String[] name = {"Berlin", "Mannheim", "Frankfurt"};
    private String[] temp = {"10°C", "20°C", "30°C"};
    private String[] type = {"cloudy", "foggy", "sunny"};
    private String[] humi = {"40%", "50%", "60%"};
    private String[] wind = {"10 m/s", "3.5 m/s", "4 m/s"};
    private String[] press= {"1018 hpa", "900 hpa", "2000 hpa"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities_list);
        CitiesList adapter = new
                CitiesList(CitiesListActivity.this, name, temp, type, humi, wind, press);
        list=(ListView)findViewById(R.id.listview_cities);
        list.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cities_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
