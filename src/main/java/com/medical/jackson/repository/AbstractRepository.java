package com.medical.jackson.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medical.jackson.model.Patient;

public abstract class AbstractRepository {

	protected Map<String, Object> map = null;
	protected TreeSet<Integer> GetIDSet = new TreeSet<>();
	protected ObjectMapper mapper = new ObjectMapper();
	protected static final String PATH = "src/main/resources/";
	protected static final String FILE_NAME = "patient.json";

	protected abstract <T> List<T> deletePatientByIdGeneric(int n);

	protected abstract List<Patient> addAllPatients(List<Patient> patient);

	protected abstract <T> List<T> addAllPatientsGeneric(List<T> t);

	protected abstract <T> T getAllMedicalsGeneric(Class<T> clazz);
	
	@SuppressWarnings("unchecked")
	protected <T> List<T> convertMapToList(Map<String, Object> map) {
		return (List<T>) map.values().stream().parallel().collect(Collectors.toList());
	}

	protected void setPatientID(Object object) {

		if (object != null) {
			Patient patient = (Patient) object;
			GetIDSet.add(Integer.valueOf(patient.getId()));
		}
	}

	protected void constructAllMedicalMap() {

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
}
