package com.example.comp3074_mobile_dev_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.comp3074_mobile_dev_project.views.search.SearchActivity;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


        LinearLayout developersContainer = findViewById(R.id.developers_container);

        //  Data of devs
        String[][] developersData = {
                {"Daniel De Mesa-Borrett", "101440281"},
                {"Zach Keatings", "101422808"},
                {"Mustafa Bandukda", "101203879"},
                {"Geysiane Lopes Bezerra", "101403935"},
                {"Gabriel Pais", "101271055"},
                {"Hamzah Hafez", "101429091"}
        };

        for (String[] dev : developersData) {
            View developerCard = LayoutInflater.from(this).inflate(R.layout.developer_list, developersContainer, false);

            // Populate the developer card with name and ID
            TextView nameTextView = developerCard.findViewById(R.id.developer_name);
            TextView idTextView = developerCard.findViewById(R.id.developer_id);

            nameTextView.setText(dev[0]);
            idTextView.setText("ID: " + dev[1]);

            developersContainer.addView(developerCard);
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
}