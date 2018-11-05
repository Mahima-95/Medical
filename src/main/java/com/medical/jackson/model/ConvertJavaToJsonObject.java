/*package com.medical.jackson.model;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ConvertJavaToJsonObject {

	public static void main(String[] args) {
		ConvertJavaToJsonObject convertJavaToJsonObject = new ConvertJavaToJsonObject();
		convertJavaToJsonObject.run();
	}

	private void run() {
		ObjectMapper mapper = new ObjectMapper();

		Patient patient = createDummyObject();

		try {
			// Convert object to JSON string and save into a file directly
			mapper.writeValue(new File("E:\\sts 3.9.1\\Medical-Jackson-practice\\patient.json"), patient);
			// Convert object to JSON string
			String jsonInString = mapper.writeValueAsString(patient);
			System.out.println(jsonInString);

			// Convert object to JSON string and pretty print
			jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(patient);
			System.out.println(jsonInString);

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Patient createDummyObject() {
		Patient patient = new Patient();
		patient.setAadhaar("484056751687845");
		patient.setAllergies("sul");
		patient.setEmail("agg.mahima@gmail.com");
		return patient;
	}
}
*/