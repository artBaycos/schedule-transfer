package com.teste.schedule.controller;

import com.teste.schedule.dto.TransferDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/v1/transfer")
public class ScheduleTransferController {

    @PostMapping
    public ResponseEntity<?> scheduleTransfer(@RequestBody TransferDto transferFile) {
        System.out.println(transferFile.toString());
        return new ResponseEntity<>(transferFile, HttpStatusCode.valueOf(200));
    }

    @GetMapping
    public ResponseEntity<Object> listTransfer() {
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }
}
