package com.example.comp3074_mobile_dev_project;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.comp3074_mobile_dev_project.views.search.SearchActivity;

import java.util.Arrays;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private boolean isLikedRestaurantsVisible = false;
    private boolean isReviewsVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Mock Data
        String userName = "John Doe";
        String userEmail = "john@example.com";
        List<String> likedRestaurants = Arrays.asList("Sushi Place", "Burger Corner", "Pasta Palace");
        List<String[]> reviews = Arrays.asList(
                new String[]{"Sushi Place", "Amazing sushi!"},
                new String[]{"Burger Corner", "Great burgers!"},
                new String[]{"Pasta Palace", "Loved the Alfredo sauce!"}
        );

        // Set User Details
        ((TextView) findViewById(R.id.profile_name)).setText(userName);
        ((TextView) findViewById(R.id.profile_email)).setText(userEmail);
        ((ImageView) findViewById(R.id.profile_picture)).setImageResource(R.drawable.logo);

        // Populate Liked Restaurants
        LinearLayout likedRestaurantsContainer = findViewById(R.id.liked_restaurants_container);
        for (String restaurant : likedRestaurants) {
            TextView textView = new TextView(this);
            textView.setText(restaurant);
            textView.setTextSize(16);
            likedRestaurantsContainer.addView(textView);
        }

        // Populate Reviews
        LinearLayout reviewsContainer = findViewById(R.id.reviews_container);
        for (String[] review : reviews) {
            TextView textView = new TextView(this);
            textView.setText(review[0] + ": " + review[1]);
            textView.setTextSize(16);
            reviewsContainer.addView(textView);
        }

        // Set Logout Button
        Button logoutButton = findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(v -> {
            Toast.makeText(this, "Logged out successfully!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    // Toggle Sections
    public void toggleLikedRestaurants(View view) {
        LinearLayout likedRestaurantsContainer = findViewById(R.id.liked_restaurants_container);
        isLikedRestaurantsVisible = !isLikedRestaurantsVisible;
        likedRestaurantsContainer.setVisibility(isLikedRestaurantsVisible ? View.VISIBLE : View.GONE);
    }

    public void toggleReviews(View view) {
        LinearLayout reviewsContainer = findViewById(R.id.reviews_container);
        isReviewsVisible = !isReviewsVisible;
        reviewsContainer.setVisibility(isReviewsVisible ? View.VISIBLE : View.GONE);
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
}
