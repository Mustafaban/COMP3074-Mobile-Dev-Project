package com.example.comp3074_mobile_dev_project;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.comp3074_mobile_dev_project.database.DatabaseHelper;

public class ProfileActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        dbHelper = new DatabaseHelper(this);

        // Set User Details
        String userName = "John Doe";
        String userEmail = "john@example.com";
        ((TextView) findViewById(R.id.profile_name)).setText(userName);
        ((TextView) findViewById(R.id.profile_email)).setText(userEmail);
        ((ImageView) findViewById(R.id.profile_picture)).setImageResource(R.drawable.logo);

        // Populate Reviews
        LinearLayout reviewsContainer = findViewById(R.id.reviews_container);
        Cursor cursor = dbHelper.getRestaurants();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String restaurantName = cursor.getString(cursor.getColumnIndex("name"));
                String review = cursor.getString(cursor.getColumnIndex("review"));
                float rating = cursor.getFloat(cursor.getColumnIndex("rating"));

                TextView textView = new TextView(this);
                textView.setText(restaurantName + ": " + review + " (Rating: " + rating + ")");
                textView.setTextSize(16);
                reviewsContainer.addView(textView);
            } while (cursor.moveToNext());
            cursor.close();
        }
    }
}
