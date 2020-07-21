package com.boot.bookingrestaurantapi.services;

import com.boot.bookingrestaurantapi.execptions.BookingException;
import com.boot.bookingrestaurantapi.jsons.RestaurantRest;

public interface RestaurantService {

	RestaurantRest getRestaurantById(Long restaurantId) throws BookingException;

}
