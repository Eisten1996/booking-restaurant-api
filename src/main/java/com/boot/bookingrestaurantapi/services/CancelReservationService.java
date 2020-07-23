package com.boot.bookingrestaurantapi.services;

import com.boot.bookingrestaurantapi.execptions.BookingException;

public interface CancelReservationService {
	public String deleteReservation(String locator) throws BookingException;
}
