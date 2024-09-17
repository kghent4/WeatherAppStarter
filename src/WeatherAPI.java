//This class talks to the OpenWeatherMap API to get real time weather

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class WeatherAPI {

    // Instance Variables
    private static final String API_KEY = "fe0e168e72580138a8cb819cec103ff3";
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather";

    public String getAllWeatherInfo(String location) {
        String urlString = BASE_URL + "?q=" + location.replaceAll(" ", "%20") + "&appid=" + API_KEY + "&units=imperial";

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                return content.toString();
            } else {
                System.out.println("Error: " + responseCode);
                return null;
            }
        }

        catch (Exception e) {
            System.out.println("Location not found. Please try again.");
        }

        return "Something went wrong";

    }

    // Function to get, format, and return weather data
    public String parseWeatherData(String blockOfText) {

        //TODO

    }

    //Returns the name of the city
    public String getCityName(String blockOfText){
        return new JSONObject(blockOfText).getString("name");
    }

    //Returns the current temperature in °F
    public String getTemp(String blockOfText){
        return new Double((new JSONObject(blockOfText)).getJSONObject("main").getDouble("temp")).toString();
    }

    //Returns the temperature that it currently feels like outside in °F
    public String getFeelsLike(String blockOfText){
        return (new Double ((new JSONObject(blockOfText)).getJSONObject("main").getDouble("feels_like"))).toString();
    }

    //Returns as a percentage out of 100%
    public String getHumidity(String blockOfText){
        return (new Integer((new JSONObject(blockOfText)).getJSONObject("main").getInt("humidity"))).toString();
    }

    //Returns the atmospheric pressure in hPa (hectopascals)
    public String getPressure(String blockOfText){
        return (new Integer((new JSONObject(blockOfText)).getJSONObject("main").getInt("pressure"))).toString();
    }

    //Returns wind speed in miles / hour (mph)
    public String getWindSpeed(String blockOfText){
        return (new Double((new JSONObject(blockOfText)).getJSONObject("wind").getDouble("speed"))).toString();
    }

    //Ex. Cloudy, sunny, etc.
    public String getDescription(String blockOfText){
        return (new JSONObject(blockOfText).getJSONArray("weather").getJSONObject(0)).getString("description");
    }

    //Returns how far you can see clearly, in meters
    public String getVisibility(String blockOfText){
        return (new Integer((new JSONObject(blockOfText)).getInt("visibility"))).toString();
    }



}
