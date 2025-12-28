package com.examly.springapp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class PatientService {

    private List<Map<String, Object>> patients = new ArrayList<>();
    private long idCounter = 1L;

    // ADD
    public Map<String, Object> addPatient(Map<String, Object> patient) {
        Map<String, Object> stored = new HashMap<>(patient);
        stored.put("id", idCounter);
        patients.add(stored);
        idCounter++;
        return stored;
    }

    // GET BY ID
    public Map<String, Object> getPatientById(int id) {
        if (id <= 0 || id > patients.size()) {
            return null;
        }
        return patients.get(id - 1);
    }

    // UPDATE
    public Map<String, Object> updatePatient(int id, Map<String, Object> patient) {
        if (id <= 0 || id > patients.size()) {
            return null;
        }

        Map<String, Object> existing = patients.get(id - 1);
        Object existingId = existing.get("id");

        Map<String, Object> updated = new HashMap<>(patient);
        updated.put("id", existingId);

        patients.set(id - 1, updated);
        return updated;
    }
}
