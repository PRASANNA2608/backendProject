package com.examly.springapp.controller;

import java.util.List;

import com.examly.springapp.service.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    // ADD
    @PostMapping
    public ResponseEntity<Object> addAppointment(
            @RequestBody(required = false) Object appointment) {

        if (appointment == null) {
            return ResponseEntity.badRequest().build();
        }

        return new ResponseEntity<>(
                appointmentService.addAppointment(appointment),
                HttpStatus.CREATED
        );
    }

    // GET BY STATUS
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Object>> getAppointmentsByStatus(
            @PathVariable String status) {

        List<Object> result =
                appointmentService.getAppointmentsByStatus(status);

        if (result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok(result);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getAppointmentById(@PathVariable int id) {

        Object appointment =
                appointmentService.getAppointmentById(id);

        if (appointment == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(appointment);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAppointment(
            @PathVariable int id,
            @RequestBody Object appointment) {

        Object updated =
                appointmentService.updateAppointment(id, appointment);

        if (updated == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updated);
    }
}
