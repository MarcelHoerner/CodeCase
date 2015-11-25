package logic;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//sends request to openweathermap
public class ConnectToWeather {

    private static String BASE = "http://api.openweathermap.org/data/2.5/weather?lat=";

    public String getWeatherData(String lat, String lon) {
        HttpURLConnection connection = null ;
        InputStream input = null;
        //build the request
        String url = BASE + lat + "&lon=" + lon + "&appid=147449b6c5fface1b3287d395e7e8f39";

        try {
            //opens get connection
            connection = (HttpURLConnection) (new URL(url)).openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.connect();


            //read in the response
            StringBuffer buffer = new StringBuffer();
            input = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(input));
            String line = null;
            while (  (line = br.readLine()) != null )
                buffer.append(line + "\r\n");

            input.close();
            connection.disconnect();
            return buffer.toString();
        }
        catch(Throwable t) {
            t.printStackTrace();
        }
        finally {
            try { 
                    input.close(); 
                } catch(Throwable t) {}
            
            try { 
                    connection.disconnect(); 
                } catch(Throwable t) {}
        }

        return null;

    }

}
