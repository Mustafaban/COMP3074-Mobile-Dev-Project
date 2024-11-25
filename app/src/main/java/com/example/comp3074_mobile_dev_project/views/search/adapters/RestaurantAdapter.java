package com.example.comp3074_mobile_dev_project.views.search.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.example.comp3074_mobile_dev_project.R;
import com.example.comp3074_mobile_dev_project.models.Restaurant;

import java.util.List;

public class RestaurantAdapter extends BaseAdapter {
    private List<Restaurant> restaurantList;
    private Context context;

    public RestaurantAdapter(List<Restaurant> restaurantList, Context context) {
        this.restaurantList = restaurantList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return restaurantList.size();
    }

    @Override
    public Object getItem(int position) {
        return restaurantList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.search_item_restaurant, parent, false);
        }
        AssetManager assetManager = context.getAssets();
        Restaurant restaurant = restaurantList.get(position);
        ImageView restaurantImage = convertView.findViewById(R.id.restaurant_image);
        TextView restaurantName = convertView.findViewById(R.id.restaurant_name);
        TextView restaurantRating = convertView.findViewById(R.id.restaurant_rating);
        Button directionsButton = convertView.findViewById(R.id.directions_button);
        restaurantName.setText(restaurant.getName());
        restaurantRating.setText("Rating: " + restaurant.getRating());  // Load image from assets (assuming images are stored in assets)

        InputStream inputStream = null;
        try {
            inputStream = assetManager.open(restaurant.getImage());

            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            restaurantImage.setImageBitmap(bitmap);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        directionsButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.google.com/maps/search/?api=1&query=" + restaurant.getAddress()));
            context.startActivity(intent);
        });
        return convertView;
    }
}