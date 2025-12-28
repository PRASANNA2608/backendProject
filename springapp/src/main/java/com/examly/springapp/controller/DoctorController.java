package com.examly.springapp.controller;

import java.util.List;

import com.examly.springapp.service.DoctorService;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }
    

    @PostMapping
    public ResponseEntity<Object> createDoctor(@RequestBody(required = false) Object doctor) {
        if (doctor == null) {
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(
                doctorService.createDoctor(doctor),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<Object>> getAllDoctors() {
        List<Object> doctors = doctorService.getAllDoctors();

        if (doctors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(doctors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getDoctorById(@PathVariable int id) {
        Object doctor = doctorService.getDoctorById(id);

        if (doctor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(doctor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateDoctor(
            @PathVariable int id,
            @RequestBody Object doctor) {

        Object updated = doctorService.updateDoctor(id, doctor);

        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable int id) {
        boolean deleted = doctorService.deleteDoctor(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/page/{pageNumber}/{pageSize}")
    public Page<Object> getDoctorsWithPagination(
            @PathVariable int pageNumber,
            @PathVariable int pageSize) {

        return doctorService.getDoctorsWithPagination(pageNumber, pageSize);
    }
}
