package com.medical.jackson.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medical.jackson.model.Patient;
import com.medical.jackson.service.MedicalService;

@RestController
public class MedicalController {

	@Autowired
	private MedicalService medicalService;

	@RequestMapping("/add")
	public String addDoctor() {
		Patient patient = new Patient();
		patient.setAadhaar("Adhaar");
		patient.setCreatedDate(new Date());
		patient.setEmail("abc@mail.com");
		patient.setGender("Male");
		patient.setId("3");
		patient.setMobile("8524585450");
		patient.setName("Aviral");
		patient.setPassword("Hello");
		patient.setProfilePicPath("path");
		return medicalService.addDoctor(patient);
	}

	@RequestMapping("/getAllPatients")
	public Patient getAllPatients() {
		return medicalService.getAllPatients();
	}

	@RequestMapping("/getAllPatients1")
	public Patient[] getAllPatients1() {
		return medicalService.getAllPatients1();
	}
	/*
	 * @RequestMapping("/getDoctor/{dId}") public Doctor getDoctor(@PathVariable
	 * String dId){ return doctorService.getDoctor(dId); }
	 */
}
