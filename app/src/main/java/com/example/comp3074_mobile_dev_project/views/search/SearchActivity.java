package com.example.comp3074_mobile_dev_project.views.search;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.comp3074_mobile_dev_project.AboutActivity;
import com.example.comp3074_mobile_dev_project.MainActivity;
import com.example.comp3074_mobile_dev_project.ProfileActivity;
import com.example.comp3074_mobile_dev_project.R;
import com.example.comp3074_mobile_dev_project.models.Restaurant;
import com.example.comp3074_mobile_dev_project.services.RestaurantService;
import com.example.comp3074_mobile_dev_project.views.search.adapters.RestaurantAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private final RestaurantService restaurantService = new RestaurantService();
    private RestaurantAdapter restaurantAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        EditText searchBar = findViewById(R.id.search_bar);
        listView = findViewById(R.id.list_view);
        List<Restaurant> restaurantList = restaurantService.getRestaurants();
        restaurantAdapter = new RestaurantAdapter(restaurantList, this);
        listView.setAdapter(restaurantAdapter);
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void filter(String name) {
        restaurantAdapter = new RestaurantAdapter(restaurantService.getRestaurants(name), this);
        listView.setAdapter(restaurantAdapter);
    }

    // Navigation bar methods
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