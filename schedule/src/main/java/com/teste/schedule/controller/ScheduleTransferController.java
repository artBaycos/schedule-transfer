package com.teste.schedule.controller;

import com.teste.schedule.dto.TransferDto;
import com.teste.schedule.service.ScheduleTransferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/v1/transfer")
public class ScheduleTransferController {

    @Autowired
    private ScheduleTransferService transferService;

    @PostMapping
    public ResponseEntity<?> scheduleTransfer(@RequestBody TransferDto transferFile) {
        System.out.println(transferFile.toString());
        try {
            transferService.scheduleTransfer(transferFile);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Erro ao processar a transferência", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<Object> listTransfer() {
        try {
            return ResponseEntity.ok(transferService.returnAllScheduledTransfers());
        }catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Erro ao processar a listagem de transferencia", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
