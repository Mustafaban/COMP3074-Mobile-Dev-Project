package com.example.comp3074_mobile_dev_project;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar; // Added import for RatingBar
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.comp3074_mobile_dev_project.database.DatabaseHelper;

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
                Button reviewButton = restaurantCard.findViewById(R.id.review_button);
                Button shareButton = restaurantCard.findViewById(R.id.share_button);

                nameTextView.setText(name);
                addressTextView.setText(address);
                descriptionTextView.setText(description);
                imageView.setImageResource(imageResource);
                ratingTextView.setText("Rating: " + rating);

                reviewButton.setOnClickListener(v -> showReviewDialog(name));

                shareButton.setOnClickListener(v -> shareRestaurantDetails(name, address, description));

                container.addView(restaurantCard);
            } while (cursor.moveToNext());

            cursor.close();
        } else {
            Toast.makeText(this, "No restaurant data available.", Toast.LENGTH_SHORT).show();
        }
    }

    private void showReviewDialog(String restaurantName) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_review, null);
        builder.setView(dialogView);

        TextView title = dialogView.findViewById(R.id.dialog_title);
        RatingBar ratingBar = dialogView.findViewById(R.id.rating_bar); // Fixed issue here
        TextView reviewInput = dialogView.findViewById(R.id.review_input);
        Button submitButton = dialogView.findViewById(R.id.submit_button);

        title.setText("Review " + restaurantName);

        AlertDialog dialog = builder.create();
        submitButton.setOnClickListener(v -> {
            String review = reviewInput.getText().toString();
            float rating = ratingBar.getRating();

            if (dbHelper.updateRestaurantReviewAndRating(restaurantName, review, rating)) {
                Toast.makeText(this, "Review and rating submitted!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            } else {
                Toast.makeText(this, "Failed to submit review.", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }

    private void shareRestaurantDetails(String name, String address, String description) {
        String shareText = "Check out " + name + " located at " + address + ". " + description;

        // Create a chooser with options
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Share via")
                .setItems(new String[]{"Facebook", "Twitter", "Other Apps"}, (dialog, which) -> {
                    switch (which) {
                        case 0: // Facebook
                            shareOnFacebook(shareText);
                            break;
                        case 1: // Twitter
                            shareOnTwitter(shareText);
                            break;
                        case 2: // Other Apps
                            shareWithOtherApps(shareText);
                            break;
                    }
                })
                .show();
    }

    private void shareOnFacebook(String text) {
        Intent facebookIntent = new Intent(Intent.ACTION_SEND);
        facebookIntent.setType("text/plain");
        facebookIntent.setPackage("com.facebook.katana"); // Facebook app package

        facebookIntent.putExtra(Intent.EXTRA_TEXT, text);

        try {
            startActivity(facebookIntent);
        } catch (Exception e) {
            // Redirect to browser if Facebook app is not installed
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/sharer/sharer.php?u=" + Uri.encode(text)));
            startActivity(browserIntent);
        }
    }

    private void shareOnTwitter(String text) {
        Intent twitterIntent = new Intent(Intent.ACTION_SEND);
        twitterIntent.setType("text/plain");
        twitterIntent.setPackage("com.twitter.android"); // Twitter app package

        twitterIntent.putExtra(Intent.EXTRA_TEXT, text);

        try {
            startActivity(twitterIntent);
        } catch (Exception e) {
            // Redirect to browser if Twitter app is not installed
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/intent/tweet?text=" + Uri.encode(text)));
            startActivity(browserIntent);
        }
    }

    private void shareWithOtherApps(String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);

        startActivity(Intent.createChooser(intent, "Share via"));
    }
}
//da