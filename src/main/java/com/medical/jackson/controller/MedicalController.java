package com.medical.jackson.controller;

import java.util.ArrayList;
import java.util.Collection;
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
	@RequestMapping("/addAllPatientsGeneric")
	public <T> T addAllPatientsGeneric(@RequestParam int n) {
		List<T> t = new ArrayList<T>();
		T patient = null;
		for (int i = 0; i < n; i++) {
			patient = (T) new Patient();
			((Patient) patient).setAadhaar("Adhaar");
			((Patient) patient).setCreatedDate(new Date());
			((Patient) patient).setEmail("abc@mail.com");
			((Patient) patient).setGender("Male");
			((Patient) patient).setId(String.valueOf(i));
			((Patient) patient).setMobile("8524585450");
			((Patient) patient).setName("Aviral");
			((Patient) patient).setPassword("Hello");
			((Patient) patient).setProfilePicPath("path");
			t.add(patient);
		}
		return medicalService.addAllPatientsGeneric(t);
	}

	// normal method of get Patient
	@RequestMapping("/getAllPatients")
	public List<Patient> getAllMedicals() {
		return medicalService.getAllPatients();
	}

	// generic method of get Patient
	@RequestMapping("/getAllMedicalsGeneric")
	public Patient[] getAllMedicalsGeneric() {
		return medicalService.getAllMedicalsGeneric();
	}
}

// link to commit data using git
// bash----------https://help.github.com/articles/adding-an-existing-project-to-github-using-the-command-line/
// link to create a git branch and commit using git
// bash--------https://confluence.atlassian.com/bitbucket/branching-a-repository-223217999.html