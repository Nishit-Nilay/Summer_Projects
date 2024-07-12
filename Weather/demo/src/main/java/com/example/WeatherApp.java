package com.example;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Scanner;

public class WeatherApp {

    private static final String API_KEY = "75c0e5b5021b445eae8141419241107";
    private static final String API_URL = "http://api.weatherapi.com/v1/current.json";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter a city (or type 'quit' to exit): ");
            String city = scanner.nextLine().trim();

            if (city.equalsIgnoreCase("quit")) {
                break;
            }

            try {
                String weatherData = getWeatherData(city);
                if (weatherData != null) {
                    parseAndDisplayWeather(weatherData);
                } else {
                    System.out.println("Could not retrieve weather data.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Program exited.");
    }

    private static String getWeatherData(String city) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String url = API_URL + "?key=" + API_KEY + "&q=" + city;

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            return response.body().string();
        }
    }

    private static void parseAndDisplayWeather(String responseBody) {
        JSONObject json = new JSONObject(responseBody);
        JSONObject location = json.getJSONObject("location");
        String city = location.getString("name");
        JSONObject current = json.getJSONObject("current");
        double temperature = current.getDouble("temp_c");
        int humidity = current.getInt("humidity");
        String description = current.getJSONObject("condition").getString("text");

        System.out.println("Weather in " + city + ":");
        System.out.println("Temperature: " + temperature + " °C");
        System.out.println("Humidity: " + humidity + "%");
        System.out.println("Description: " + description);

        displayWeatherArt(description);
    }

    private static void displayWeatherArt(String description) {
        String art;

        // Convert description to lowercase for case-insensitive matching
        String lowerCaseDescription = description.toLowerCase();

        // Check for keywords in the description and assign corresponding ASCII art
        if (lowerCaseDescription.contains("rain") || lowerCaseDescription.contains("mist")) {
            art = getRainyArt();
        } else if (lowerCaseDescription.contains("sunny") || lowerCaseDescription.contains("dry") || lowerCaseDescription.contains("hot")) {
            art = getSunnyArt();
        } else if (lowerCaseDescription.contains("cloudy")) {
            art = getCloudyArt();
        } else {
            art = "No ASCII art available for this weather condition.";
        }

        System.out.println(art);
    }

    private static String getSunnyArt() {
        return "   \\   / \n" +
               "    .-.    \n" +
               " ― (   ) ― \n" +
               "    `-’    \n" +
               "   /   \\";
    }

    private static String getRainyArt() {
        return "     .-.      \n" +
               "    (   ).    \n" +
               "   (___(__)   \n" +
               "  ‘ ‘ ‘ ‘ ‘ ‘ \n" +
               "  ‘ ‘ ‘ ‘ ‘ ‘";
    }

    private static String getCloudyArt() {
        return "     .--.    \n" +
               "  .-(    ).  \n" +
               " (___.__)__)";
    }
}
