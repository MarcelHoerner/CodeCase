package logic;


import org.json.JSONException;
import org.json.JSONObject;

public class Weather {

    private String name;
    private String temp;
    private String type;
    private String humi;
    private String speed;
    private String press;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp + "°C";
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHumi() {
        return humi;
    }

    public void setHumi(String humi) {
        this.humi = humi + "%";
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed + " m/h";
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press + " hpa";
    }

}
