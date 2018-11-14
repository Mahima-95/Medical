package com.medical.jackson.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medical.jackson.model.Patient;
import com.medical.jackson.service.MedicalService;

@RestController
public class MedicalController {

	@Autowired
	private MedicalService medicalService;

	// normal method of add Patient
	@RequestMapping("/addAllPatients")
	public List<Patient> addAllPatients(@RequestParam int n) {
		List<Patient> patientList = new ArrayList<Patient>();
		Patient patient = null;
		for (int i = 0; i < n; i++) {
			patient = new Patient();
			patient.setAadhaar("Adhaar");
			patient.setCreatedDate(new Date());
			patient.setEmail("abc@mail.com");
			patient.setGender("Male");
			patient.setId(String.valueOf(i));
			patient.setMobile("8524585450");
			patient.setName("Aviral");
			patient.setPassword("Hello");
			patient.setProfilePicPath("path");
			patientList.add(patient);
		}
		return medicalService.addAllPatientsInAppendMode(patientList);
	}

	// generic method of get Patient
	@RequestMapping("/addPatient")
	public List<Patient> addAllPatientsGeneric(@RequestParam int n) {
		return medicalService.addAllPatientsGeneric(n);
	}

	// generic method of get Patient
	@RequestMapping("/getPatient")
	public Patient[] getAllMedicalsGeneric() {
		return medicalService.getAllMedicalsGeneric();
	}
	
	@RequestMapping(value ="/updatePatient", method = RequestMethod.POST)
	public Patient updatePatient(@RequestBody Patient patient) {
		return medicalService.updatePatient(patient);
	}

	// generic method of delete Patient
	@RequestMapping("/deleteAllPatient")
	public List<Patient> deleteAllPatientsGeneric() {
		return medicalService.deleteAllPatientsGeneric();
	}

	// generic method of delete Patient by Id
	@RequestMapping("/deletePatientById")
	public List<Patient> deletePatientByIdGeneric(@RequestParam int n) {
		return medicalService.deletePatientByIdGeneric(n);
	}
}
