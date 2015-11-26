package logic;

import android.location.Location;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


//searches the list of german cities for near ones
public class SearchCities {

    public String[] getLatLonValues(String lat, String lon, InputStream input){

        Location locationA = new Location("A");
        locationA.setLatitude(Double.parseDouble(lat));
        locationA.setLongitude(Double.parseDouble(lon));

        ArrayList<String> finalValues = new ArrayList<String>();

        BufferedReader bff;
        String line;

        try{
            bff = new BufferedReader(new InputStreamReader(input));
            line = bff.readLine();
            while(line != null){
                //trim down
                String[] helper = line.split("\\s+");
                //find nearby
                Location locationB = new Location("B");
                locationB.setLatitude(Double.parseDouble(helper[helper.length-2]));
                locationB.setLongitude(Double.parseDouble(helper[helper.length-1]));
                //calculate the distance
                if(locationA.distanceTo(locationB) <= 10000) {
                    finalValues.add(helper[helper.length-2]);
                    finalValues.add(helper[helper.length-1]);
                }
                line = bff.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            finalValues = null;
        } catch (IOException e){
            e.printStackTrace();
            finalValues = null;
        }

        return finalValues.toArray(new String[finalValues.size()]);
    }


}
