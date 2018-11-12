package com.medical.jackson.repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.medical.jackson.model.Patient;

@Repository
public class MedicalRepository {

	ObjectMapper mapper = new ObjectMapper();
	private static final String PATH = "E:\\\\Java_Git_repositories\\\\Medical-master\\\\Medical-master\\\\";
	private static final String PATH1 = "D:\\Mahima\\My Dev Space\\workspace\\Medi-Dev\\";

	public String addMedical(Patient patient) {
		try {
			mapper.writeValue(
					new File(
							"E:\\Java_Git_repositories\\Medical-master\\Medical-master\\patient2.json"),
					patient);
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
			mapper.writeValue(
					new File(
							"E:\\Java_Git_repositories\\Medical-master\\Medical-master\\patient3.json"),
					patient);
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
	public <T> T addAllPatientsGeneric(List<T> t) {

		try {
			mapper.writeValue(
					new File(
							"D:\\Mahima\\My Dev Space\\workspace\\Medi-Dev\\patient2.json"),
					t);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (T) t;
	}

	// normal method of get Patient
	public List<Patient> getAllMedicals() {
		Patient[] patient = null;
		try {
			patient = mapper
					.readValue(
							new File(
									"E:\\Java_Git_repositories\\Medical-master\\Medical-master\\patient3.json"),
							Patient[].class);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return Arrays.stream(patient).parallel().collect(Collectors.toList());
	}

	// generic method of get Patient
	public <T> T getAllMedicalsGeneric(Class<T> clazz, String fileName) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(new File(PATH1 + fileName), clazz);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// generic method of delete patient
	public <T> T deleteAllPatientsGeneric(List<Patient> list) {
		return  (T) list;
	}

}
