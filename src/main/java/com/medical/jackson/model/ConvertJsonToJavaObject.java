package com.medical.jackson.model;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertJsonToJavaObject {
	public static void main(String[] args) {
		ConvertJsonToJavaObject convertJsonToJavaObject = new ConvertJsonToJavaObject();
		convertJsonToJavaObject.run();
	}

	private void run() {
		ObjectMapper mapper = new ObjectMapper();

		try {

			// Convert JSON string from file to Object
			Patient patient = mapper.readValue(new File("E:\\\\sts 3.9.1\\\\Medical-Jackson-practice\\\\patient.json"),
					Patient.class);
			System.out.println(patient);

			// Convert JSON string to Object
			String jsonInString = "{\"name\":\"mkyong\",\"salary\":7500,\"skills\":[\"java\",\"python\"]}";
			Patient patient1 = mapper.readValue(jsonInString, Patient.class);
			System.out.println(patient1);

			// Pretty print
			String prettyStaff1 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(patient1);
			System.out.println(prettyStaff1);

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
