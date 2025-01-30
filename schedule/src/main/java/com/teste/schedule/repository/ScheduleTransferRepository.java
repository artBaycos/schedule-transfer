package com.teste.schedule.repository;


import com.teste.schedule.dto.TransferDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleTransferRepository extends JpaRepository<TransferDto, Long> {
}
