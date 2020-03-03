package com.apap.tu04.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.tu04.model.FlightModel;
import com.apap.tu04.model.PilotModel;

/**
 * FlightDb
 */
@Repository
public interface FlightDb extends JpaRepository<FlightModel, Long>{
	void deleteByFlightNumber(String flightNumber);
	void deleteByPilotAndFlightNumber(PilotModel pilot, String flightNumber);
	FlightModel findByPilotAndFlightNumber(PilotModel pilot, String flightNUmber);
	FlightModel findById(long id);
	FlightModel findByFlightNumber(String flightNumber);

}
