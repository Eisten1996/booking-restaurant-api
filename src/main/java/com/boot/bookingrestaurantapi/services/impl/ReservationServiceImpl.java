package com.boot.bookingrestaurantapi.services.impl;

/*import java.util.List;
import java.util.stream.Collectors;*/

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.boot.bookingrestaurantapi.entities.Reservation;
import com.boot.bookingrestaurantapi.execptions.BookingException;
import com.boot.bookingrestaurantapi.execptions.NotFountException;
import com.boot.bookingrestaurantapi.jsons.CreateReservationRest;
import com.boot.bookingrestaurantapi.jsons.ReservationRest;
import com.boot.bookingrestaurantapi.repositories.ReservationRepository;
import com.boot.bookingrestaurantapi.services.ReservationService;

public class ReservationServiceImpl implements ReservationService {

	@Autowired
	ReservationRepository reservationRepository;

	public static final ModelMapper modelMapper = new ModelMapper();

	public ReservationRest getReservation(Long reservationId) throws BookingException {
		return modelMapper.map(getReservationEntity(reservationId), ReservationRest.class);
	}

	/*
	 * public List<ReservationRest> getReservations() throws BookingException {
	 * final List<Reservation> reservationsEntity = reservationRepository.findAll();
	 * return reservationsEntity.stream().map(service -> modelMapper.map(service,
	 * ReservationRest.class)) .collect(Collectors.toList()); }
	 */

	@Override
	public String createReservation(CreateReservationRest createReservationRest) throws BookingException {
		// TODO Auto-generated method stub
		return null;
	}

	private Reservation getReservationEntity(Long reservationId) throws BookingException {
		return reservationRepository.findById(reservationId)
				.orElseThrow(() -> new NotFountException("SNOT-404-1", "RESERVATION_NOT_FOUND"));

	}


}
