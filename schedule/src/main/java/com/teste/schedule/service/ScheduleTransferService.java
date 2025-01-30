package com.teste.schedule.service;

import com.teste.schedule.dto.TransferDto;
import com.teste.schedule.repository.ScheduleTransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ScheduleTransferService {
    @Autowired
    private ScheduleTransferRepository transferRepository;

    public TransferDto scheduleTransfer(TransferDto transferencia) {

        TransferDto newTransfer = transferRepository.save(transferencia);

        return newTransfer;
    }

    public Object returnAllScheduledTransfers() {
        return transferRepository.findAll();
    }
}
