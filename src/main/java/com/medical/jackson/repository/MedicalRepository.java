package com.medical.jackson.repository;

import static com.medical.jackson.constants.Constants.idMap;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.medical.jackson.model.Patient;

@Repository
public class MedicalRepository {

	private ObjectMapper mapper = new ObjectMapper();
	private static final String PATH = "src/main/resources/";
	private static final String FILE_NAME = "patient.json";

	Map<String, Object> map = null;

	public MedicalRepository() {
		constructAllMedicalMap();
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

	@SuppressWarnings("unchecked")
	public <T> T update(T t) {

		if (t != null) {
			Field[] fields = t.getClass().getDeclaredFields();
			for (Field field : fields) {
				if (idMap.containsKey(field.getName())) {
					try {
						field.setAccessible(true);
						String value = String.valueOf(field.get(t));
						if (value != null && map.containsKey(value)) {
							map.put(value, t);
							addAllPatientsGeneric(convertMapToList(map));
							return (T) map.get(value);
						}
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return null;
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

	@SuppressWarnings("unchecked")
	private <T> List<T> convertMapToList(Map<String, Object> map) {

		return (List<T>) map.values().stream().parallel().collect(Collectors.toList());
	}
}