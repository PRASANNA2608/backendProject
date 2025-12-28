package com.examly.springapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class MedicalRecordService {

    private List<Map<String, Object>> records = new ArrayList<>();

    public Map<String, Object> addMedicalRecord(Map<String, Object> record) {
        records.add(record);
        return record;
    }

    public Map<String, Object> getMedicalRecordById(int id) {
        if (id <= 0 || id > records.size()) {
            return null;
        }
        return records.get(id - 1);
    }

    public List<Map<String, Object>> getRecordsByPatientId(Long patientId) {
        List<Map<String, Object>> result = new ArrayList<>();

        for (Map<String, Object> record : records) {
            Object patientObj = record.get("patient");
            if (patientObj instanceof Map) {
                Map<?, ?> patientMap = (Map<?, ?>) patientObj;
                Object idObj = patientMap.get("id");
                if (idObj != null) {
                    try {
                        Long recPatientId = Long.valueOf(idObj.toString());
                        if (recPatientId.equals(patientId)) {
                            result.add(record);
                        }
                    } catch (NumberFormatException e) {
                        // ignore invalid id
                    }
                }
            }
        }
        return result;
    }

    public Map<String, Object> updateMedicalRecord(int id, Map<String, Object> record) {
        if (id <= 0 || id > records.size()) {
            return null;
        }
        records.set(id - 1, record);
        return record;
    }
}
