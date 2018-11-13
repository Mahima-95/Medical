package com.medical.jackson.constants;

import java.util.HashMap;
import java.util.Map;

import com.medical.jackson.model.Patient;

public class Constants {

	public static final Map<String, Object> idMap = new HashMap<String, Object>() {
		{
			put("pId", Patient.class);
		}
	};
}
