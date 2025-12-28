package com.examly.springapp.controller;

import java.util.List;
import java.util.Map;

import com.examly.springapp.service.MedicalRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicalrecords")
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    @PostMapping
    public ResponseEntity<Object> addMedicalRecord(
            @RequestBody(required = false) Map<String, Object> record) {

        if (record == null) {
            return ResponseEntity.badRequest().build();
        }

        return new ResponseEntity<>(
                medicalRecordService.addMedicalRecord(record),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getMedicalRecordById(@PathVariable int id) {
        Map<String, Object> record = medicalRecordService.getMedicalRecordById(id);

        if (record == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(record);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<Object> getRecordsByPatient(@PathVariable Long patientId) {

        List<Map<String, Object>> result =
                medicalRecordService.getRecordsByPatientId(patientId);

        if (result.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body("No medical records found");
        }

        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMedicalRecord(
            @PathVariable int id,
            @RequestBody Map<String, Object> record) {

        Map<String, Object> updated =
                medicalRecordService.updateMedicalRecord(id, record);

        if (updated == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updated);
    }
}
