package com.example.comp3074_mobile_dev_project;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.comp3074_mobile_dev_project.views.search.SearchActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout container = findViewById(R.id.container);

        // Dummy data
        String[][] restaurantData = {
                {
                        "Jacobs & Co. Steakhouse",
                        "12 Brant St, Toronto, ON M5V 2M1, Canada",
                        "Toronto's premier destination for fine dining and premium steaks.",
                        String.valueOf(R.drawable.restaurant1),
                        "4.0"
                },
                {
                        "The Keg Steakhouse & Bar",
                        "165 York St, Toronto, ON M5H 3R8, Canada",
                        "Beloved Canadian chain known for top-quality steaks and classic steakhouse atmosphere.",
                        String.valueOf(R.drawable.restaurant2),
                        "3.5"
                },
                {
                        "Canoe Restaurant & Bar",
                        "66 Wellington St W, Toronto, ON M5K 1H6, Canada",
                        "Canoe offers stunning city views and modern Canadian cuisine crafted with the finest ingredients.",
                        String.valueOf(R.drawable.restaurant3),
                        "5.0"
                }
        };

        for (String[] data : restaurantData) {
            View restaurantCard = LayoutInflater.from(this).inflate(R.layout.restaurant_list, container, false);

            // Initialize views
            TextView nameTextView = restaurantCard.findViewById(R.id.restaurant_name);
            TextView addressTextView = restaurantCard.findViewById(R.id.restaurant_address);
            TextView descriptionTextView = restaurantCard.findViewById(R.id.restaurant_description);
            ImageView imageView = restaurantCard.findViewById(R.id.restaurant_image);
            TextView tagsTextView = restaurantCard.findViewById(R.id.restaurant_tags);
            RatingBar ratingBar = restaurantCard.findViewById(R.id.restaurant_rating);
            Button directionsButton = restaurantCard.findViewById(R.id.directions_button);

            // Populate data
            nameTextView.setText(data[0]);
            addressTextView.setText(data[1]);
            descriptionTextView.setText(data[2]);
            imageView.setImageResource(Integer.parseInt(data[3]));
            tagsTextView.setText("Tags: Fine Dining, Steakhouse");

            // Set the initial rating from dummy data
            float initialRating = Float.parseFloat(data[4]);
            ratingBar.setRating(initialRating);

            // Handle rating changes
            ratingBar.setOnRatingBarChangeListener((ratingBar1, rating, fromUser) -> {
                if (fromUser) {
                    Toast.makeText(MainActivity.this, data[0] + " rated " + rating + " stars", Toast.LENGTH_SHORT).show();

                    // Update the dummy data in memory
                    data[4] = String.valueOf(rating);
                }
            });

            // Directions button
            directionsButton.setOnClickListener(v -> {
                Uri gmmIntentUri = Uri.parse("google.navigation:q=" + Uri.encode(data[1]));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                try {
                    startActivity(mapIntent);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Google Maps is not installed.", Toast.LENGTH_SHORT).show();
                }
            });

            // Add the card to the container
            container.addView(restaurantCard);
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

    public void toMap(View view) {
        // Open Google Maps using the browser-friendly URL
        Uri mapsUri = Uri.parse("https://www.google.com/maps/search/Restaurants/@43.6732228,-79.4366746,15z/data=!3m1!4b1?entry=ttu&g_ep=EgoyMDI0MTExOS4yIKXMDSoASAFQAw%3D%3D");
        Intent intent = new Intent(Intent.ACTION_VIEW, mapsUri);

        try {
            startActivity(intent);
        } catch (Exception e) {
            // Notify the user if no application is available
            Toast.makeText(this, "No maps application or browser available.", Toast.LENGTH_SHORT).show();
        }
    }

    public void toProfile(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }


}


