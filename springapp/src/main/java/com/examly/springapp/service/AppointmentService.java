package com.examly.springapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    // In-memory storage (required for Examly)
    private List<Object> appointments = new ArrayList<>();

    // ADD
    public Object addAppointment(Object appointment) {
        appointments.add(appointment);
        return appointment;
    }

    // GET BY STATUS
    public List<Object> getAppointmentsByStatus(String status) {

        List<Object> result = new ArrayList<>();

        for (Object appt : appointments) {
            try {
                Object apptStatus = appt.getClass()
                        .getMethod("getStatus")
                        .invoke(appt);

                if (apptStatus != null &&
                        apptStatus.toString().equalsIgnoreCase(status)) {
                    result.add(appt);
                }
            } catch (Exception e) {
                // Try map access as fallback
                if (appt instanceof java.util.Map) {
                    java.util.Map<?, ?> apptMap = (java.util.Map<?, ?>) appt;
                    Object statusObj = apptMap.get("status");
                    if (statusObj != null &&
                            statusObj.toString().equalsIgnoreCase(status)) {
                        result.add(appt);
                    }
                }
            }
        }

        return result;
    }

    // GET BY ID
    public Object getAppointmentById(int id) {
        if (id <= 0 || id > appointments.size()) {
            return null;
        }
        return appointments.get(id - 1);
    }

    // UPDATE
    public Object updateAppointment(int id, Object appointment) {
        if (id <= 0 || id > appointments.size()) {
            return null;
        }

        appointments.set(id - 1, appointment);
        return appointment;
    }
}
