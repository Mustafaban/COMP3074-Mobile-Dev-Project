package com.example.comp3074_mobile_dev_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.example.comp3074_mobile_dev_project.models.Restaurant;
import com.example.comp3074_mobile_dev_project.models.WeatherData;
import com.example.comp3074_mobile_dev_project.services.RestaurantService;
import com.example.comp3074_mobile_dev_project.services.WeatherService;
import com.example.comp3074_mobile_dev_project.R;
import com.example.comp3074_mobile_dev_project.views.search.SearchActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ImageView weatherIcon;
    private TextView weatherDescription;
    private RestaurantService restaurantService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Set up views for weather data
        weatherIcon = findViewById(R.id.weather_icon);
        weatherDescription = findViewById(R.id.weather_description);

        // Initialize RestaurantService
        restaurantService = new RestaurantService();

        // Obtain the SupportMapFragment and set up the map
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Default location (e.g., Toronto)
        LatLng defaultLocation = new LatLng(43.65107, -79.347015); // Toronto, CA
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 12));

        // Add markers for restaurants
        addRestaurantMarkers();

        // Fetch and display weather data for the default location
        fetchWeather(defaultLocation);

        // Update weather dynamically as the map camera moves
        mMap.setOnCameraIdleListener(() -> {
            LatLng currentLocation = mMap.getCameraPosition().target;
            fetchWeather(currentLocation);
        });
    }

    private void addRestaurantMarkers() {
        List<Restaurant> restaurants = restaurantService.getRestaurants();
        for (Restaurant restaurant : restaurants) {
            LatLng location = new LatLng(restaurant.getLatitude(), restaurant.getLongitude());
            mMap.addMarker(new MarkerOptions().position(location).title(restaurant.getName()));
        }
    }

    private void fetchWeather(LatLng location) {
        double latitude = location.latitude;
        double longitude = location.longitude;

        WeatherService.getWeatherAsync(latitude, longitude, new WeatherService.WeatherCallback() {
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
                runOnUiThread(() -> weatherDescription.setText("Failed to fetch weather data."));
            }
        });
    }
    public void toHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void toAbout(View view) {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    public void toSearch(View view) {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }
    public void toProfile(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
}
}
