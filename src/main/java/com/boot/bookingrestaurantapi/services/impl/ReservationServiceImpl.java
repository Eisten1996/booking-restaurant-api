package com.boot.bookingrestaurantapi.services.impl;

/*import java.util.List;
import java.util.stream.Collectors;*/

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.boot.bookingrestaurantapi.entities.Reservation;
import com.boot.bookingrestaurantapi.entities.Restaurant;
import com.boot.bookingrestaurantapi.entities.Turn;
import com.boot.bookingrestaurantapi.execptions.BookingException;
import com.boot.bookingrestaurantapi.execptions.InternalServerErrorException;
import com.boot.bookingrestaurantapi.execptions.NotFountException;
import com.boot.bookingrestaurantapi.jsons.CreateReservationRest;
import com.boot.bookingrestaurantapi.jsons.ReservationRest;
import com.boot.bookingrestaurantapi.repositories.ReservationRepository;
import com.boot.bookingrestaurantapi.repositories.RestaurantRepository;
import com.boot.bookingrestaurantapi.repositories.TurnRepository;
import com.boot.bookingrestaurantapi.services.ReservationService;

public class ReservationServiceImpl implements ReservationService {

	private static final Logger LOOGER = LoggerFactory.getLogger(ReservationServiceImpl.class);

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private TurnRepository turnRepository;

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

	public String createReservation(CreateReservationRest createReservationRest) throws BookingException {
		final Restaurant restaurantId = restaurantRepository.findById(createReservationRest.getRestaurantId())
				.orElseThrow(() -> new NotFountException("RESTAURANT_NOT_FOUND", "RESTAURANT_NOT_FOUND"));
		final Turn turn = turnRepository.findById(createReservationRest.getTurnId())
				.orElseThrow(() -> new NotFountException("TURN_NOT_FOUND", "TURN_NOT_FOUND"));
		String locator = generateLocator(restaurantId, createReservationRest);
		final Reservation reservation = new Reservation();
		reservation.setLocator(locator);
		reservation.setPerson(createReservationRest.getPerson());
		reservation.setDate(createReservationRest.getDate());
		reservation.setRestaurant(restaurantId);
		reservation.setTurn(turn.getName());

		try {
			reservationRepository.save(reservation);
		} catch (final Exception e) {
			LOOGER.error("INTERNAL_SERVER_ERROR");
			throw new InternalServerErrorException("INTERNAl_SERVER_ERROR", "INTERNAl_SERVER_ERROR");
		}
		return locator;
	}

	private Reservation getReservationEntity(Long reservationId) throws BookingException {
		return reservationRepository.findById(reservationId)
				.orElseThrow(() -> new NotFountException("SNOT-404-1", "RESERVATION_NOT_FOUND"));
	}

	private String generateLocator(Restaurant restaurantId, CreateReservationRest createReservationRest)
			throws BookingException {
		return restaurantId.getName() + createReservationRest.getTurnId();

	}

}
