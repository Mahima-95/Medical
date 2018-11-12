package com.medical.jackson.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medical.jackson.model.Patient;
import com.medical.jackson.repository.MedicalRepository;
import com.mysql.fabric.xmlrpc.base.Array;

@Service
public class MedicalService {

	private static final Logger LOG = Logger.getLogger(MedicalService.class);

	private static final String FILE_NAME = "patient3.json";

	@Autowired
	private MedicalRepository medicalRepository;

	public String addDoctor(Patient patient) {
		return medicalRepository.addMedical(patient);
	}

	// normal method of add Patient
	public List<Patient> addAllPatients(List<Patient> patients) {

		return medicalRepository.addAllPatients(patients);
	}

	public List<Patient> addAllPatientsInAppendMode(List<Patient> patients) {

		List<Patient> patientNewList = new ArrayList<>();
		patientNewList.addAll(Arrays.stream(getAllMedicalsGeneric()).parallel().collect(Collectors.toList()));
		patientNewList.addAll(patients);
		return medicalRepository.addAllPatients(patientNewList);
	}

	// generic method of add Patient
	public List<Patient> addAllPatientsGeneric(List<Patient> patients) {
		return medicalRepository.addAllPatientsGeneric(patients);
	}

	// normal method of get Patient
	public List<Patient> getAllPatients() {

		return medicalRepository.getAllMedicals();
	}

	// generic method of get Patient
	public Patient[] getAllMedicalsGeneric() {
		return medicalRepository.getAllMedicalsGeneric(Patient[].class, FILE_NAME);
	}

	// generic method of delete Patient
	public List<Patient> deleteAllPatientsGeneric() {
		return (List<Patient>) medicalRepository.deleteAllPatientsGeneric(addAllPatientsGeneric(new ArrayList<>()));
	}

	public List<Patient> deletePatientByIdGeneric(int n) {
		return medicalRepository.deletePatientByIdGeneric(n);
	}

}
