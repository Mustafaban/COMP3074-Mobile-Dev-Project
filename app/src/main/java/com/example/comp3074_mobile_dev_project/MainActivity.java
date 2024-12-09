package com.example.comp3074_mobile_dev_project;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.comp3074_mobile_dev_project.models.WeatherData;
import com.example.comp3074_mobile_dev_project.services.WeatherService;
import com.example.comp3074_mobile_dev_project.database.DatabaseHelper;
import com.example.comp3074_mobile_dev_project.views.search.SearchActivity;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout container = findViewById(R.id.container);

        dbHelper = new DatabaseHelper(this);

        if (dbHelper.getRestaurantCount() == 0) {
            addSampleData();
        }

        loadRestaurants(container);

    }

    private void addSampleData() {
        dbHelper.insertRestaurant(
                "Jacobs & Co. Steakhouse",
                "12 Brant St, Toronto, ON M5V 2M1, Canada",
                "Toronto's premier destination for fine dining and premium steaks.",
                R.drawable.restaurant1,
                4.8f
        );
        dbHelper.insertRestaurant(
                "The Keg Steakhouse & Bar",
                "165 York St, Toronto, ON M5H 3R8, Canada",
                "Beloved Canadian chain known for top-quality steaks and classic steakhouse atmosphere.",
                R.drawable.restaurant2,
                4.5f
        );
        dbHelper.insertRestaurant(
                "Canoe Restaurant & Bar",
                "66 Wellington St W, Toronto, ON M5K 1H6, Canada",
                "Canoe offers stunning city views and modern Canadian cuisine crafted with the finest ingredients.",
                R.drawable.restaurant3,
                4.9f
        );
    }

    private void loadRestaurants(LinearLayout container) {
        Cursor cursor = dbHelper.getRestaurants();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String address = cursor.getString(cursor.getColumnIndex("address"));
                String description = cursor.getString(cursor.getColumnIndex("description"));
                int imageResource = cursor.getInt(cursor.getColumnIndex("image_resource"));
                float rating = cursor.getFloat(cursor.getColumnIndex("rating"));

                View restaurantCard = LayoutInflater.from(this).inflate(R.layout.restaurant_list, container, false);

                TextView nameTextView = restaurantCard.findViewById(R.id.restaurant_name);
                TextView addressTextView = restaurantCard.findViewById(R.id.restaurant_address);
                TextView descriptionTextView = restaurantCard.findViewById(R.id.restaurant_description);
                ImageView imageView = restaurantCard.findViewById(R.id.restaurant_image);
                TextView ratingTextView = restaurantCard.findViewById(R.id.restaurant_rating);

                nameTextView.setText(name);
                addressTextView.setText(address);
                descriptionTextView.setText(description);
                imageView.setImageResource(imageResource);
                ratingTextView.setText("Rating: " + rating);

                container.addView(restaurantCard);
            } while (cursor.moveToNext());

            cursor.close();
        } else {
            Toast.makeText(this, "No restaurant data available.", Toast.LENGTH_SHORT).show();
        }
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

//    public void toMap(View view) {
//        // Open Google Maps using the browser-friendly URL
//        Uri mapsUri = Uri.parse("https://www.google.com/maps/search/Restaurants/@43.6732228,-79.4366746,15z/data=!3m1!4b1?entry=ttu&g_ep=EgoyMDI0MTExOS4yIKXMDSoASAFQAw%3D%3D");
//        Intent intent = new Intent(Intent.ACTION_VIEW, mapsUri);
//
//        try {
//            startActivity(intent);
//        } catch (Exception e) {
//            // Notify the user if no application is available
//            Toast.makeText(this, "No maps application or browser available.", Toast.LENGTH_SHORT).show();
//        }
//    }

    public void toProfile(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
    public void toWeather(View view) {
        Intent intent = new Intent(this, WeatherActivity.class);
        startActivity(intent);
    }

    public void toMap(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }




}
