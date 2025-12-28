package com.examly.springapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    private List<Object> doctors = new ArrayList<>();

    public Object createDoctor(Object doctor) {
        doctors.add(doctor);
        return doctor;
    }

    public List<Object> getAllDoctors() {
        return doctors;
    }

    public Object getDoctorById(int id) {
        if (id <= 0 || id > doctors.size()) {
            return null;
        }
        return doctors.get(id - 1);
    }

    public Object updateDoctor(int id, Object doctor) {
        if (id <= 0 || id > doctors.size()) {
            return null;
        }
        doctors.set(id - 1, doctor);
        return doctor;
    }

    public boolean deleteDoctor(int id) {
        if (id <= 0 || id > doctors.size()) {
            return false;
        }
        doctors.remove(id - 1);
        return true;
    }

    public Page<Object> getDoctorsWithPagination(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return new PageImpl<>(doctors, pageable, doctors.size());
    }
}
