package com.example.comp3074_mobile_dev_project;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.comp3074_mobile_dev_project.models.WeatherData;
import com.example.comp3074_mobile_dev_project.services.WeatherService;
import com.squareup.picasso.Picasso;

public class WeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        ImageView weatherIcon = findViewById(R.id.weather_icon);
        TextView weatherDescription = findViewById(R.id.weather_description);

        // Fetch weather for a specific city
        WeatherService.getWeatherAsync("Toronto", new WeatherService.WeatherCallback() {
            @Override
            public void onSuccess(WeatherData weatherData) {
                runOnUiThread(() -> {
                    String iconUrl = WeatherService.GetWeatherImageUrl(weatherData);
                    Picasso.get().load(iconUrl).into(weatherIcon);
                    weatherDescription.setText(weatherData.getWeather().get(0).getDescription());
                });
            }

            @Override
            public void onError(Exception e) {
                runOnUiThread(() -> Toast.makeText(WeatherActivity.this, "Failed to fetch weather data.", Toast.LENGTH_SHORT).show());
            }
        });
    }
}
