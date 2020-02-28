package com.apap.tu04.service;

import java.util.List;

import com.apap.tu04.model.FlightModel;
import com.apap.tu04.model.PilotModel;

/**
 * FlightService
 */
public interface FlightService {
	void addFlight(FlightModel flight);
	List<FlightModel> getAllFlight();
	void deleteFlight(PilotModel pilot, String flightNumber);
	FlightModel getFlight(PilotModel pilot, String flightNumber);
	void updateFlight(FlightModel flight);
}
