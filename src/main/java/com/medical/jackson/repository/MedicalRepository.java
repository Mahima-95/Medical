package com.medical.jackson.repository;

import static com.medical.jackson.constants.Constants.idMap;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.medical.jackson.model.Patient;

@Repository
public class MedicalRepository extends AbstractRepository {

	public MedicalRepository() {
		constructAllMedicalMap();
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
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> addAllPatientsGeneric(int n) {
		return (List<T>) addPatientsInList(n);
	}
	
	public <T> List<T> addPatient(List<T> t) {
		try {
			mapper.writeValue(new File(PATH + FILE_NAME), t);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return t;
		
	}

	// generic method of get Patient
	public <T> T getAllMedicalsGeneric(Class<T> clazz) {

		File file = new File(PATH + FILE_NAME);
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
	@Override
	public <T> List<T> deletePatientByIdGeneric(int n) {

		Object removed = map.remove(map.containsKey(String.valueOf(n)) ? String.valueOf(n) : null);
		if (removed != null) {
			List<Patient> patients = new ArrayList<>();
			for (String key : map.keySet()) {
				patients.add((Patient) map.get(key));
			}
			setPatientID(removed);
			
			return  (List<T>) addPatient(patients);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public <T> T deleteAllPatientsGeneric() {
		return (T) addPatient(new ArrayList<>());
	}

	@Override
	public <T> List<T> addAllPatientsGeneric(List<T> t) {
		// TODO Auto-generated method stub
		return null;
	}

	

}