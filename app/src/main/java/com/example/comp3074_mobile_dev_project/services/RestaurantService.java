package com.example.comp3074_mobile_dev_project.services;


import com.example.comp3074_mobile_dev_project.models.Restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RestaurantService {
    private List<Restaurant> restaurants;

    public RestaurantService() {
        restaurants = new ArrayList<>();
        restaurants.add(new Restaurant(UUID.randomUUID(), "The Gourmet Kitchen", 4.5, "placeholder_restaurant.jpg", "123 address, sample, CA", 0, 0));
        restaurants.add(new Restaurant(UUID.randomUUID(), "Sushi Paradise", 4.7, "placeholder_restaurant.jpg", "123 address, sample, CA", 0, 0));
        restaurants.add(new Restaurant(UUID.randomUUID(), "Pasta Palace", 4.3, "placeholder_restaurant.jpg", "123 address, sample, CA", 0, 0));
        restaurants.add(new Restaurant(UUID.randomUUID(), "Burger Haven", 4.6, "placeholder_restaurant.jpg", "123 address, sample, CA", 0, 0));
        restaurants.add(new Restaurant(UUID.randomUUID(), "Taco Town", 4.2, "placeholder_restaurant.jpg", "123 address, sample, CA", 0, 0));
        restaurants.add(new Restaurant(UUID.randomUUID(), "Pizza Planet", 5.0, "placeholder_restaurant.jpg", "123 address, sample, CA", 0, 0));
        restaurants.add(new Restaurant(UUID.randomUUID(), "Steakhouse Supreme", 4.9, "placeholder_restaurant.jpg", "123 address, sample, CA", 0, 0));
        restaurants.add(new Restaurant(UUID.randomUUID(), "Vegan Delight", 4.4, "placeholder_restaurant.jpg", "123 address, sample, CA", 0, 0));
        restaurants.add(new Restaurant(UUID.randomUUID(), "Seafood Sensation", 4.7, "placeholder_restaurant.jpg", "123 address, sample, CA", 0, 0));
        restaurants.add(new Restaurant(UUID.randomUUID(), "BBQ Bliss", 1.5, "placeholder_restaurant.jpg", "123 address, sample, CA", 0, 0));
        restaurants.add(new Restaurant(UUID.randomUUID(), "Dim Sum Dynasty", 4.6, "placeholder_restaurant.jpg", "123 address, sample, CA", 0, 0));
        restaurants.add(new Restaurant(UUID.randomUUID(), "Curry Corner", 2.3, "placeholder_restaurant.jpg", "123 address, sample, CA", 0, 0));
        restaurants.add(new Restaurant(UUID.randomUUID(), "Bakery Bliss", 4.8, "placeholder_restaurant.jpg", "123 address, sample, CA", 0, 0));
        restaurants.add(new Restaurant(UUID.randomUUID(), "Salad Sanctuary", 4.2, "placeholder_restaurant.jpg", "123 address, sample, CA", 0, 0));
        restaurants.add(new Restaurant(UUID.randomUUID(), "Noodle Nirvana", 4.5, "placeholder_restaurant.jpg", "123 address, sample, CA", 0, 0));
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public List<Restaurant> getRestaurants(String name) {

        List<Restaurant> filteredList = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getName().toLowerCase().contains(name.toLowerCase())) {
                filteredList.add(restaurant);
            }
        }
        return filteredList;
    }
    public Restaurant getRestaurantById(String id) {
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getId().equals(id)) {
                return restaurant;
            }
        }
        return null;
    }
}
