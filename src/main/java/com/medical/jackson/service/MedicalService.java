package com.medical.jackson.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Iterables;
import com.medical.jackson.model.Patient;
import com.medical.jackson.repository.MedicalRepository;

@Service
public class MedicalService {

	private MedicalRepository medicalRepository;

	@Autowired
	public MedicalService(MedicalRepository medicalRepository) {
		this.medicalRepository = medicalRepository;
	}

	public List<Patient> addAllPatientsInAppendMode(List<Patient> patients) {

		List<Patient> patientNewList = new ArrayList<>();
		patientNewList.addAll(Arrays.stream(getAllMedicalsGeneric()).parallel().collect(Collectors.toList()));
		patientNewList.addAll(patients);
		return medicalRepository.addAllPatients(patientNewList);
	}

	// generic method of add Patient
	public List<Patient> addAllPatientsGeneric(int n) {

		Patient[] patients = getAllMedicalsGeneric();
		List<Patient> patientList = Arrays.stream(patients).parallel().collect(Collectors.toList());
		Patient patient = null;
		for (int i = 1; i <= n; i++) {
			patient = new Patient();
			patient.setAadhaar("Adhaar");
			patient.setCreatedDate(new Date());
			patient.setEmail("abc@mail.com");
			patient.setGender("Male");
			patient.setId(String.valueOf(patients.length + i));
			patient.setMobile("8524585450");
			patient.setName("Aviral");
			patient.setPassword("Hello");
			patient.setProfilePicPath("path");
			patientList.add(patient);
		}
		return medicalRepository.addAllPatientsGeneric(patientList);
	}

	public Patient updatePatient(Patient patient) {

		return medicalRepository.update(patient);
	}

	// generic method of get Patient
	public Patient[] getAllMedicalsGeneric() {

		Patient[] patients = medicalRepository.getAllMedicalsGeneric(Patient[].class);
		List<Patient> patientList = Arrays.stream(patients).parallel().collect(Collectors.toList());
		Collections.sort(patientList, patientComparatorForId);
		return Iterables.toArray(patientList, Patient.class);
	}

	// generic method of delete Patient
	public List<Patient> deleteAllPatientsGeneric() {

		return medicalRepository.deleteAllPatientsGeneric();
	}

	public List<Patient> deletePatientByIdGeneric(int n) {
		return medicalRepository.deletePatientByIdGeneric(n);
	}

	private Comparator<Patient> patientComparatorForId = new Comparator<Patient>() {

		@Override
		public int compare(Patient p1, Patient p2) {
			return Integer.valueOf(p1.getId()) - Integer.valueOf(p2.getId());
		}
	};

}
