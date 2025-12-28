package com.examly.springapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class MedicalRecord {

    @Id
    private Long recordId;
    private String diagnosis;
    private String prescription;

    @ManyToOne
    private Patient patientId;

    public MedicalRecord() {
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public Patient getPatientId() {
        return patientId;
    }

    public void setPatientId(Patient patientId) {
        this.patientId = patientId;
    }

    public MedicalRecord(Long recordId, String diagnosis, String prescription, Patient patientId) {
        this.recordId = recordId;
        this.diagnosis = diagnosis;
        this.prescription = prescription;
        this.patientId = patientId;
    }
    
}
