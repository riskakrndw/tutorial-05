package com.apap.tu04.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import com.apap.tu04.model.PilotModel;
import com.apap.tu04.service.PilotService;

/**
 * PilotController
 */
@Controller
public class PilotController {
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping("/")
	private String home(Model model) {
		List<PilotModel> pilot = pilotService.getAllPilot();
		String navigation = "HOME";
		model.addAttribute("navigation", navigation);
		model.addAttribute("pilot", pilot);
		return "home";
	}
	
	@RequestMapping(value = "/pilot/add", method = RequestMethod.GET)
	private String add(Model model) {
		model.addAttribute("pilot", new PilotModel());
		String navigation = "ADD PILOT";
		model.addAttribute("navigation", navigation);
		return "addPilot";
	}
	
	@RequestMapping(value = "/pilot/add", method = RequestMethod.POST)
	private String addPilotSubmit(@ModelAttribute PilotModel pilot, Model model) {
		pilotService.addPilot(pilot);
		model.addAttribute("errMesssage", "Data Berhasil Ditambahkan");
		String navigation = "NOTIF ADD PILOT";
		model.addAttribute("navigation", navigation);
		return "add";
	}
	
	@RequestMapping(value = "/pilot/view", method = RequestMethod.GET)
	public String viewPilot(@RequestParam("licenseNumber") String licenseNumber, Model model) {
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		if(pilot==null) {
			model.addAttribute("errMesssage", "Flight number tidak ditemukan");
			String navigation = "NOTIF VIEW PILOT";
			model.addAttribute("navigation", navigation);
			return "errPage";
		}else {
			String navigation = "VIEW PILOT";
			model.addAttribute("navigation", navigation);
			model.addAttribute("pilot", pilot);
			return "view-pilot";
		}
	}
	
	@RequestMapping(value= "/pilot/delete/{licenseNumber}", method = RequestMethod.GET)
	public String deletePilot(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
			try {
			PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
			pilotService.deletePilot(pilot);
			String navigation = "DELETE PILOT";
			model.addAttribute("navigation", navigation);
			return "delete.html";	
			} catch (Exception e) {
				model.addAttribute("errMesssage", "Data Berhasil Dihapus");
				String navigation = "NOTIF DELETE PILOT";
				model.addAttribute("navigation", navigation);
				return "errPage.html";
			}	
	}

	@RequestMapping(value= "/pilot/update/{licenseNumber}", method = RequestMethod.GET)
	public String updatePilot(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		model.addAttribute("pilot", pilot);
		String navigation = "UPDATE PILOT";
		model.addAttribute("navigation", navigation);
		return "updatePilot.html";	
	}
	
	@RequestMapping(value = "/pilot/update", method = RequestMethod.POST)
	private String updatePilotSubmit(@ModelAttribute PilotModel pilot, Long id, Model model) {
		pilotService.addPilot(pilot);
		String navigation = "NOTIF UPDATE PILOT";
		model.addAttribute("navigation", navigation);
		return "updateInfo.html";
	}
}
