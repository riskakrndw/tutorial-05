package com.apap.tu04.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

import com.apap.tu04.model.FlightModel;
import com.apap.tu04.model.PilotModel;
import com.apap.tu04.service.FlightService;
import com.apap.tu04.service.PilotService;

/**
 * FlightController
 */
@Controller
public class FlightController {
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private PilotService pilotService;
	
	// @RequestMapping(value = "/flight/add", method = RequestMethod.POST)
	// private String addFlightSubmit(@ModelAttribute FlightModel flight, Model model) {
	// 	flightService.addFlight(flight);
	// 	String navigation = "NOTIF ADD FLIGHT";
	// 	model.addAttribute("navigation", navigation);
	// 	return "add";
	// }

	@RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.GET)
	private String add(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
		FlightModel flight = new FlightModel();
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		ArrayList<FlightModel> pilotFlight = new ArrayList<>();
		pilotFlight.add(flight);
		pilot.setPilotFlight(pilotFlight);
		flight.setPilot(pilot);
		model.addAttribute("flight", flight);
		model.addAttribute("pilot", pilot);
		String navigation = "ADD FLIGHT";
		model.addAttribute("navigation", navigation);
		return "addFlight";
	}

	@RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.POST, params = { "moreFlight" })
	public String moreFlight(@ModelAttribute PilotModel pilot, BindingResult bindingResult, Model model) {
		if (pilot.getPilotFlight() == null) {
			pilot.setPilotFlight(new ArrayList<FlightModel>());
		}
		FlightModel newFlight = new FlightModel();
		pilot.getPilotFlight().add(newFlight);
		model.addAttribute("pilot", pilot);
		String navigation = "ADD FLIGHT";
		model.addAttribute("navigation", navigation);
		return "addFlight";
	}

	@RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.POST, params = { "submit" })
	private String addFlightSubmit(@ModelAttribute PilotModel pilot, Model model) {
		PilotModel currPilot = pilotService.getPilotDetailByLicenseNumber(pilot.getLicenseNumber());
		for (FlightModel flight : pilot.getPilotFlight()) {
			flight.setPilot(currPilot);
			flightService.addFlight(flight);
		}
		String navigation = "NOTIF ADD FLIGHT";
		model.addAttribute("navigation", navigation);
		return "add";
	}
	
	@RequestMapping(value= "/flight/delete", method = RequestMethod.POST)
	private String deleteFlightById(@ModelAttribute PilotModel pilot, Model model) {
		for(FlightModel flight : pilot.getPilotFlight()){
			flightService.deleteFlightById(flight.getId());
		}
		String navigation = "NOTIF DELETE FLIGHT";
		model.addAttribute("navigation", navigation);
		return "delete";
	}

	@RequestMapping(value= "/flight/delete/{flightNumber}", method = RequestMethod.GET)
	public String deleteFlight(@PathVariable(value = "flightNumber") String flightNumber, Model model) {
		flightService.deleteFlight(flightNumber);
		String navigation = "NOTIF DELETE FLIGHT";
		model.addAttribute("navigation", navigation);
		return "delete";
	}

	@RequestMapping(value= "/flight/update/{licenseNumber}/{flightNumber}", method = RequestMethod.GET)
	public String updateFlight(@PathVariable(value = "licenseNumber") String licenseNumber, @PathVariable(value = "flightNumber") String flightNumber, Model model) {
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		FlightModel flight = flightService.getFlight(pilot, flightNumber);
		model.addAttribute("flight", flight);
		model.addAttribute("pilot", pilot);
		String navigation = "UPDATE FLIGHT";
		model.addAttribute("navigation", navigation);
		return "updateFlight.html";	
	}
	
	@RequestMapping(value = "/flight/update", method = RequestMethod.POST)
	private String updateFlightSubmit(@ModelAttribute FlightModel flight, Long id, Model model) {
		flightService.updateFlight(flight);
		String navigation = "NOTIF UPDATE FLIGHT";
		model.addAttribute("navigation", navigation);
		return "updateInfo.html";
	}

	@RequestMapping(value = "/flight/viewall", method = RequestMethod.GET)
	private String viewFlights(Model model) {
		List<FlightModel> terbang = flightService.getAllFlight();
		model.addAttribute("flight", terbang);
		String navigation = "VIEW ALL";
		model.addAttribute("navigation", navigation);
		return "viewAll";
	}
	
}
