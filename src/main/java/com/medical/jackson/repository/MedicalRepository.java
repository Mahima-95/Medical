package com.medical.jackson.repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.medical.jackson.model.Patient;
import com.medical.jackson.model.common.Persistable;

;

@Repository
public class MedicalRepository {

	ObjectMapper mapper = new ObjectMapper();
	// File file = new File(
	// "D:\\Mahima\\My Dev Space\\workspace\\Medical-Sol-master\\Medical-Sol-master\\patient1.json");
	Files files;

	// private static final Logger LOG =
	// Logger.getLogger(MedicalRepository.class);

	// @Autowired
	// private ConvertJavaToJsonObject convertJavaToJsonObject;

	/*
	 * public <T extends Persistable> boolean getAllTables(T t, Class<T> clazz)
	 * { String tableName = MEDICAL_SOLUTION_TABLES.get(clazz); return
	 * MySQLTemplate.createTableIfNotExist(t, tableName, clazz);
	 * 
	 * }
	 */

	public String addMedical(Patient patient) {

		try {
			String res = mapper.writeValueAsString(patient);
			Files.write(
					Paths.get("D:\\Mahima\\My Dev Space\\workspace\\Medical-Sol-master\\Medical-Sol-master\\patient1.json"),
					res.getBytes(), StandardOpenOption.APPEND);
			// mapper.writeValue(file, patient);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "Successfully created";
	}

	public Patient getAllMedicals() {
		ObjectMapper mapper = new ObjectMapper();
		Patient patient = null;
		try {
			patient = mapper
					.readValue(
							new File(
									"D:\\Mahima\\My Dev Space\\workspace\\Medical-Sol-master\\Medical-Sol-master\\patient1.json"),
							Patient.class);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return patient;
	}

	public <T>  Patient[] getAllMedicals1(Class<T> clazz) {
		ObjectMapper mapper = new ObjectMapper();
		Patient[] patient = null;
		try {
			patient = (Patient[]) mapper.readValue(new File("D:\\Mahima\\My Dev Space\\workspace\\Medical-Sol-master\\Medical-Sol-master\\patient1.json"),
					clazz);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return patient;
	}
	
	/*
	 * public <T extends Persistable> List<T> getAllMedicals(Class<T> clazz) {
	 * 
	 * LOG.info("getAllMedicals method called"); String tableName =
	 * Constants.MEDICAL_SOLUTION_TABLES.get(clazz); return
	 * MySQLTemplate.getAllRecords(tableName, clazz); }
	 * 
	 * public <T extends Persistable> T getMedical(String primaryKeyValue,
	 * Class<T> clazz) {
	 * 
	 * LOG.info("getMedical method called"); String tableName =
	 * Constants.MEDICAL_SOLUTION_TABLES.get(clazz); return
	 * MySQLTemplate.getRecordOnBasisOfId(primaryKeyValue, tableName, clazz); }
	 */
}
