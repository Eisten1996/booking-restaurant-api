package com.boot.bookingrestaurantapi.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.boot.bookingrestaurantapi.execptions.BookingException;
import com.boot.bookingrestaurantapi.execptions.InternalServerErrorException;
import com.boot.bookingrestaurantapi.execptions.NotFountException;
import com.boot.bookingrestaurantapi.repositories.ReservationRepository;
import com.boot.bookingrestaurantapi.services.CancelReservationService;

public class CancelReservationServiceImpl implements CancelReservationService {

	private static final Logger LOOGER = LoggerFactory.getLogger(CancelReservationServiceImpl.class);

	@Autowired
	private ReservationRepository reservationRepository;

	public String deleteReservation(String locator) throws BookingException {
		reservationRepository.findByLocator(locator)
				.orElseThrow(() -> new NotFountException("LOCATOR_NOT_FOUND", "LOCATOR_NOT_FOUND"));
		try {
			reservationRepository.deleteByLocator(locator);
		} catch (final Exception e) {
			LOOGER.error("INTERNAL_SERVER_ERROR");
			throw new InternalServerErrorException("INTERNAl_SERVER_ERROR", "INTERNAl_SERVER_ERROR");
		}
		return "LOCATOR_DELETED";
	}

}
