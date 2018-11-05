package com.medical.jackson.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medical.jackson.model.Patient;
import com.medical.jackson.repository.MedicalRepository;

@Service
public class MedicalService {

	private static final Logger LOG = Logger.getLogger(MedicalService.class);

	@Autowired
	private MedicalRepository medicalRepository;

	public String addDoctor(Patient patient) {
		return medicalRepository.addMedical(patient);
	}

	public Patient getAllPatients() {
		
		return (Patient) medicalRepository.getAllMedicals();
	}

	/*
	 * public Doctor getDoctor(String doctorId) {
	 * LOG.info("-------------getDoctor method called---------------"); return
	 * medicalRepository.getMedical(doctorId, Doctor.class); }
	 */
}
