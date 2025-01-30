package com.teste.schedule.service;

import com.teste.schedule.dto.TransferDto;
import com.teste.schedule.exception.TaxInvalidException;
import com.teste.schedule.repository.ScheduleTransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.function.Function;


@Service
public class ScheduleTransferService {
    @Autowired
    private ScheduleTransferRepository transferRepository;

    private static final Map<String, Function<BigDecimal, BigDecimal>> taxInterval = Map.of(
            "0-0", value -> value.multiply(new BigDecimal(0.025)),  // 2,5%
            "1-10", value -> BigDecimal.ZERO,  // 0%
            "11-20", value -> value.multiply(new BigDecimal(0.082)), // 8,2%
            "31-40", value -> value.multiply(new BigDecimal(0.069)), // 6,9%
            "41-50", value -> value.multiply(new BigDecimal(0.017))  // 1,7%
    );

    public BigDecimal TaxCalc(LocalDate dataTransferencia, BigDecimal valor) {
        return taxInterval.entrySet().stream()
                .filter(entry -> isWithinRange((ChronoUnit.DAYS.between(LocalDate.now(), dataTransferencia)), entry.getKey()))
                .findFirst()
                .map(entry -> entry.getValue().apply(valor))
                .orElseThrow(() -> new TaxInvalidException("Data inválida para o cálculo da taxa"));
    }

    private boolean isWithinRange(long diasEntre, String range) {
        String[] parts = range.split("-");
        long start = Long.parseLong(parts[0]);
        long end = Long.parseLong(parts[1]);
        return diasEntre >= start && diasEntre <= end;
    }

    public TransferDto scheduleTransfer(TransferDto transferencia) {
        transferencia.setTax(TaxCalc(transferencia.getTransferDate(), transferencia.getValue()));
        transferencia.setScheduleDate(LocalDate.now());
        return transferRepository.save(transferencia);
    }

    public Object returnAllScheduledTransfers() {
        return transferRepository.findAll();
    }
}
