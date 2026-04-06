package com.I_Care.Media_Service.service;

import com.I_Care.Media_Service.dto.PrescriptionDTO;
import com.I_Care.Media_Service.exception.MediaException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PrescriptionService {
    public PrescriptionDTO savePrescription(MultipartFile file,Long doctorId,String doctorName,Long patientId,String patientName) throws MediaException, IOException;
    public List<PrescriptionDTO> getPrescriptionDetailsByDoctorId(Long id) throws MediaException;
    public List<PrescriptionDTO> getPrescriptionDetailsByPatientId(Long id) throws MediaException;
    public List<PrescriptionDTO> getAllPrescriptions() throws MediaException;
}


