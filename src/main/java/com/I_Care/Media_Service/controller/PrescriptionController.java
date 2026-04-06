package com.I_Care.Media_Service.controller;

import com.I_Care.Media_Service.dto.PrescriptionDTO;
import com.I_Care.Media_Service.entity.MediaFile;
import com.I_Care.Media_Service.exception.MediaException;
import com.I_Care.Media_Service.service.PrescriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/media/prescriptions")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    public PrescriptionController(PrescriptionService prescriptionService ) {
        this.prescriptionService =prescriptionService ;
    }

    Logger logger = LoggerFactory.getLogger(PrescriptionController.class);

    @PostMapping("/create")
    public ResponseEntity<PrescriptionDTO> uploadPrescription(
            @RequestParam("file") MultipartFile file,
            @RequestParam Long doctorId,
            @RequestParam String doctorName,
            @RequestParam Long patientId,
            @RequestParam String patientName
    ) throws MediaException, IOException {
        logger.info("Controller called for prescription");
        return new ResponseEntity<>(prescriptionService.savePrescription(file, doctorId, doctorName, patientId, patientName),HttpStatus.OK);
    }

    //by doctorid
    @GetMapping("/getByDoctorId/{id}")
    public ResponseEntity<List<PrescriptionDTO>> etPrescriptionDetailsByDoctorId(@PathVariable Long id) {
        return new ResponseEntity<>(prescriptionService.getPrescriptionDetailsByDoctorId(id), HttpStatus.OK);
    }
    //by patientid
    @GetMapping("/getByPatientId/{id}")
    public ResponseEntity<List<PrescriptionDTO>> etPrescriptionDetailsByPatientId(@PathVariable Long id) {
        return new ResponseEntity<>(prescriptionService.getPrescriptionDetailsByPatientId(id), HttpStatus.OK);
    }
    //get all
    @GetMapping("/getAll")
    public ResponseEntity<List<PrescriptionDTO>> getAllPrescriptions() {
        return new ResponseEntity<>(prescriptionService.getAllPrescriptions(),HttpStatus.OK);
    }
}

