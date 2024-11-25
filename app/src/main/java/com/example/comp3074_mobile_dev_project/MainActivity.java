package com.example.comp3074_mobile_dev_project;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
                        String.valueOf(R.drawable.restaurant1) // Replace with your drawable resource
                },
                {
                        "The Keg Steakhouse & Bar",
                        "165 York St, Toronto, ON M5H 3R8, Canada",
                        "Beloved Canadian chain known for top-quality steaks and classic steakhouse atmosphere.",
                        String.valueOf(R.drawable.restaurant2) // Replace with your drawable resource
                },
                {
                        "Canoe Restaurant & Bar",
                        "66 Wellington St W, Toronto, ON M5K 1H6, Canada",
                        "Canoe offers stunning city views and modern Canadian cuisine crafted with the finest ingredients.",
                        String.valueOf(R.drawable.restaurant3) // Replace with your drawable resource
                }
        };

        for (String[] data : restaurantData) {
            View restaurantCard = LayoutInflater.from(this).inflate(R.layout.restaurant_list, container, false);

            TextView nameTextView = restaurantCard.findViewById(R.id.restaurant_name);
            TextView addressTextView = restaurantCard.findViewById(R.id.restaurant_address);
            TextView descriptionTextView = restaurantCard.findViewById(R.id.restaurant_description);
            ImageView imageView = restaurantCard.findViewById(R.id.restaurant_image);

            nameTextView.setText(data[0]);
            addressTextView.setText(data[1]);
            descriptionTextView.setText(data[2]);
            imageView.setImageResource(Integer.parseInt(data[3]));

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
