package com.I_Care.Media_Service.dto;

import com.I_Care.Media_Service.entity.MediaFile;
import com.I_Care.Media_Service.entity.Prescription;

import java.time.LocalDateTime;

public class PrescriptionDTO {

    private Long id;
    private Long doctorId;
    private String doctorName;
    private Long patientId;
    private String patientName;
    private Long mediaId;
    private String fileName;
    private String fileType;
    private Long fileSize;
    private LocalDateTime createdAt;

    public PrescriptionDTO() {}

    public PrescriptionDTO(Long id,Long doctorId,String doctorName,Long patientId,String patientName,Long mediaId,String fileName,String fileType,Long fileSize,LocalDateTime createdAt) {
        this.id = id;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.patientId = patientId;
        this.patientName = patientName;
        this.mediaId = mediaId;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getMediaId() {
        return mediaId;
    }

    public void setMediaId(Long mediaId) {
        this.mediaId = mediaId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Prescription toEntity() {
        MediaFile media = null;
        if (this.mediaId != null) {
            media = new MediaFile();
            media.setId(this.mediaId);
        }
        Prescription prescription = new Prescription();
        prescription.setId(this.id);
        prescription.setMediaFile(media);
        prescription.setDoctorId(this.doctorId);
        prescription.setDoctorName(this.doctorName);
        prescription.setPatientId(this.patientId);
        prescription.setPatientName(this.patientName);

        return prescription;
    }
}
