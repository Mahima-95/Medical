package com.medical.jackson.repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.medical.jackson.model.Patient;

@Repository
public class MedicalRepository {

	private ObjectMapper mapper = new ObjectMapper();
	private static final String PATH = "src/main/resources/";
	private static final String FILE_NAME = "patient3.json";

	Map<String, Object> map = null;

	public MedicalRepository() {
		constructAllMedicalMap();
	}

	public String addMedical(Patient patient) {
		try {
			mapper.writeValue(new File(PATH + FILE_NAME), patient);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "Successfully created";
	}

	// normal method of add Patient
	public List<Patient> addAllPatients(List<Patient> patient) {
		try {
			mapper.writeValue(new File(PATH + FILE_NAME), patient);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return patient;
	}

	// generic method of patient
	public <T> List<T> addAllPatientsGeneric(List<T> t) {

		try {
			mapper.writeValue(new File(PATH + FILE_NAME), t);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (List<T>) t;
	}

	// normal method of get Patient
	public List<Patient> getAllMedicals() {
		Patient[] patient = null;
		try {
			patient = mapper.readValue(new File(PATH + FILE_NAME), Patient[].class);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return Arrays.stream(patient).parallel().collect(Collectors.toList());
	}

	// generic method of get Patient
	public <T> T getAllMedicalsGeneric(Class<T> clazz) {
		
		File file = new File(PATH + FILE_NAME);
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(file, clazz);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void constructAllMedicalMap() {

		Patient[] patients = getAllMedicalsGeneric(Patient[].class);
		if (patients.length == 0) {
			addAllPatientsGeneric(new ArrayList<>());
		} else {
			this.map = new HashMap<>();
			for (Patient patient : patients) {
				map.put(patient.getId(), patient);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> deletePatientByIdGeneric(int n) {

		Object removed = map.remove(map.containsKey(String.valueOf(n)) ? String.valueOf(n) : null);
		if (removed != null) {
			List<Patient> patients = new ArrayList<>();
			for (String key : map.keySet()) {
				patients.add((Patient) map.get(key));
			}
			return (List<T>) addAllPatientsGeneric(patients);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public <T> T deleteAllPatientsGeneric() {
		return (T) addAllPatientsGeneric(new ArrayList<>());
	}
}