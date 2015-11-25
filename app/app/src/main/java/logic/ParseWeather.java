package logic;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//parses the return values
public class ParseWeather {

    public static Weather getWeatherData(String data) throws JSONException {
        Weather w = new Weather();
        JSONObject jObjct = new JSONObject(data);

        //set city name
        w.setName(jObjct.getString("name"));

        //set temperature
        JSONObject mainObjct = jObjct.getJSONObject("main");
        double rounding = mainObjct.getDouble("temp");
        rounding = Math.round(10* rounding)/10;
        w.setTemp(String.valueOf(rounding - 273));
        //set humidity
        w.setHumi(String.valueOf(mainObjct.getInt("humidity")));
        //set pressure
        w.setPress(String.valueOf(mainObjct.getInt("pressure")));

        //set wind speed
        JSONObject windObjct = jObjct.getJSONObject("wind");
        w.setSpeed(String.valueOf(windObjct.getDouble("speed")));

        //set weather type
        JSONArray jArr = jObjct.getJSONArray("weather");
        JSONObject typeObjct = jArr.getJSONObject(0);
        w.setType(typeObjct.getString("main"));

        return w;
    }
}
