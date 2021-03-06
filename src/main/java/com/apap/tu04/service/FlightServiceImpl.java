package com.apap.tu04.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.apap.tu04.model.FlightModel;
import com.apap.tu04.model.PilotModel;
import com.apap.tu04.repository.FlightDb;

/**
 * @author ASUS
 *
 */
@Service
@Transactional
public class FlightServiceImpl implements FlightService{
	@Autowired
	private FlightDb flightDb;
	
	@Override
	public void addFlight(FlightModel flight) {
		flightDb.save(flight);
	}
	
	@Override
	public List<FlightModel> getAllFlight(){
		return flightDb.findAll();
	}

	@Override
	public void deleteFlight(String flightNumber) {
		flightDb.delete(this.getFlightDetailByFlightNumber(flightNumber));
	}

	@Override
	public void deleteFlightById(long id) {
		flightDb.delete(this.getFlightById(id));
	}

	@Override
	public FlightModel getFlightById(long id) {
		return flightDb.findById(id);
	}

	@Override
	public FlightModel getFlight(PilotModel pilot, String flightNumber) {
		// TODO Auto-generated method stub
		return flightDb.findByPilotAndFlightNumber(pilot, flightNumber);
	}

	@Override
	public FlightModel getFlightDetailByFlightNumber(String flightNumber) {
		return flightDb.findByFlightNumber(flightNumber);
	}

	@Override
	public void updateFlight(FlightModel flight) {
		flightDb.save(flight);
		
	}
	
}
