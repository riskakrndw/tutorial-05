package com.apap.tu04.service;

import java.util.List;

import com.apap.tu04.model.PilotModel;

/**
 * PilotService
 */
public interface PilotService {
	PilotModel getPilotDetailByLicenseNumber(String licenseNumber);
	void addPilot(PilotModel pilot);
	void deletePilot(PilotModel pilot);
	List<PilotModel> getAllPilot();
}
