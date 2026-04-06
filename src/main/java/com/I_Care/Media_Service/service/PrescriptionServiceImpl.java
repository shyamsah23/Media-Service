package com.I_Care.Media_Service.service;

import com.I_Care.Media_Service.dto.PrescriptionDTO;
import com.I_Care.Media_Service.entity.MediaFile;
import com.I_Care.Media_Service.entity.Prescription;
import com.I_Care.Media_Service.enums.Storage;
import com.I_Care.Media_Service.exception.MediaException;
import com.I_Care.Media_Service.repository.MediaFileRepository;
import com.I_Care.Media_Service.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class PrescriptionServiceImpl implements PrescriptionService{
    @Autowired
    private PrescriptionRepository prescriptionRepository;
    @Autowired
    private MediaFileRepository mediaFileRepository;

    public PrescriptionDTO savePrescription(MultipartFile file,
                                            Long doctorId,
                                            String doctorName,
                                            Long patientId,
                                            String patientName) throws MediaException, IOException {

        MediaFile media = MediaFile.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .size(file.getSize())
                .data(file.getBytes())
                .storage(Storage.DB)
                .build();

        media = mediaFileRepository.save(media);

        Prescription prescription = new Prescription(
                media,
                doctorId,
                doctorName,
                patientId,
                patientName
        );

        Prescription prescriptionEntity=prescriptionRepository.save(prescription);
        return prescriptionEntity.toDTO();
    }

    @Override
    public List<PrescriptionDTO> getPrescriptionDetailsByDoctorId(Long id) throws MediaException {
        return prescriptionRepository.findByDoctorId(id).stream().map(Prescription::toDTO).toList();
    }

    @Override
    public List<PrescriptionDTO> getPrescriptionDetailsByPatientId(Long id) throws MediaException {
        return prescriptionRepository.findByPatientId(id).stream().map(Prescription::toDTO).toList();
    }

    @Override
    public List<PrescriptionDTO> getAllPrescriptions() throws MediaException {
        return prescriptionRepository.findAll().stream().map(Prescription::toDTO).toList();
    }
}
