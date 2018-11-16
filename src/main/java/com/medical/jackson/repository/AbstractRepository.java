package com.medical.jackson.repository;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractRepository {

	protected ObjectMapper mapper = new ObjectMapper();
	protected static final String PATH = "src/main/resources/";
	protected static final String FILE_NAME = "patient.json";
}
