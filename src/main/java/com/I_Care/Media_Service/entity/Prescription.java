package com.I_Care.Media_Service.entity;

import com.I_Care.Media_Service.dto.PrescriptionDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "media_id")
    private MediaFile mediaFile;
    private Long doctorId;
    private String doctorName;
    private Long patientId;
    private String patientName;
    @CreationTimestamp
    private LocalDateTime createdAt;

    public Prescription() {}

    public Prescription(MediaFile mediaFile, Long doctorId, String doctorName,
                        Long patientId, String patientName) {
        this.mediaFile = mediaFile;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.patientId = patientId;
        this.patientName = patientName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MediaFile getMediaFile() {
        return mediaFile;
    }

    public void setMediaFile(MediaFile mediaFile) {
        this.mediaFile = mediaFile;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public PrescriptionDTO toDTO() {
        return new PrescriptionDTO(
                this.id,
                this.doctorId,
                this.doctorName,
                this.patientId,
                this.patientName,
                this.mediaFile != null ? this.mediaFile.getId() : null,
                this.mediaFile != null ? this.mediaFile.getName() : null,
                this.mediaFile != null ? this.mediaFile.getType() : null,
                this.mediaFile != null ? this.mediaFile.getSize() : null,
                this.createdAt
        );
    }
}