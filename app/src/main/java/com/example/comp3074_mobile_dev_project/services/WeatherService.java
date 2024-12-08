package com.example.comp3074_mobile_dev_project.services;

import com.example.comp3074_mobile_dev_project.models.WeatherData;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WeatherService {
    private static final String API_KEY = "554411f61ae24a0720dae5512dd798f0\n";
    private static final Gson gson = new GsonBuilder().create();
    private static final ExecutorService executorService = Executors.newFixedThreadPool(4);

    /* how to use

     WeatherService ws = new WeatherService();

        WeatherService.getWeatherAsync("toronto", new WeatherService.WeatherCallback() {
            @Override
            public void onSuccess(WeatherData weatherData) {
/// code here
            }

            @Override
            public void onError(Exception e) {

            }
        });

     */
    public static void getWeatherAsync(String city, WeatherCallback callback) {
        executorService.submit(() -> {
            try {
                WeatherData weatherData = getWeather(city);
                callback.onSuccess(weatherData);
            } catch (Exception e) {
                callback.onError(e);
            }
        });
    }

        /* how to use

     WeatherService ws = new WeatherService();

        WeatherService.getWeatherAsync(0.123712, 0.23789, new WeatherService.WeatherCallback() {
            @Override
            public void onSuccess(WeatherData weatherData) {
/// code here
            }

            @Override
            public void onError(Exception e) {

            }
        });

     */

    public static void getWeatherAsync(double lat, double lon, WeatherCallback callback) {
        executorService.submit(() -> {
            try {
                WeatherData weatherData = getWeather(lat, lon);
                callback.onSuccess(weatherData);
            } catch (Exception e) {
                callback.onError(e);
            }
        });
    }

    private static WeatherData getWeather(String city) throws Exception {
        String urlString = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric", city, API_KEY);
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        conn.disconnect();

        WeatherData weatherData = gson.fromJson(content.toString(), WeatherData.class);

        return weatherData;
    }

    private static WeatherData getWeather(double lat, double lon) throws Exception {
        String urlString = String.format("https://api.openweathermap.org/data/2.5/weather?lat=%.6f&lon=%.6f&appid=%s", lat, lon, API_KEY);
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        conn.disconnect();

        WeatherData weatherData = gson.fromJson(content.toString(), WeatherData.class);

        return weatherData;
    }


    public String GetWeatherImageUrl(WeatherData weatherData) {
        return String.format("https://openweathermap.org/img/wn/%s@2x.png", weatherData.getWeather().get(0).getIcon());
    }

    public interface WeatherCallback {
        void onSuccess(WeatherData weatherData);

        void onError(Exception e);
    }
}

