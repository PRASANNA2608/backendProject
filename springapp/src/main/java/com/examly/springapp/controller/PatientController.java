package com.examly.springapp.controller;

import java.util.Map;

import com.examly.springapp.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // ADD
    @PostMapping
    public ResponseEntity<Object> addPatient(
            @RequestBody(required = false) Map<String, Object> patient) {

        if (patient == null) {
            return ResponseEntity.badRequest().build();
        }

        return new ResponseEntity<>(
                patientService.addPatient(patient),
                HttpStatus.CREATED
        );
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getPatientById(@PathVariable int id) {
        Map<String, Object> patient = patientService.getPatientById(id);

        if (patient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patient);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePatient(
            @PathVariable int id,
            @RequestBody Map<String, Object> patient) {

        Map<String, Object> updated =
                patientService.updatePatient(id, patient);

        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }
}
