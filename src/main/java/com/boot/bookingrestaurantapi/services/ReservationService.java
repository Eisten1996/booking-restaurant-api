package com.boot.bookingrestaurantapi.services;

import com.boot.bookingrestaurantapi.execptions.BookingException;
import com.boot.bookingrestaurantapi.jsons.CreateReservationRest;
import com.boot.bookingrestaurantapi.jsons.ReservationRest;

public interface ReservationService {
	ReservationRest getReservation(Long reservationId) throws BookingException;

	String createReservation(CreateReservationRest createReservationRest) throws BookingException;

}
