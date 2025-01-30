package com.teste.schedule.controller;


import com.teste.schedule.dto.TransferDto;
import com.teste.schedule.service.ScheduleTransferService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.util.List;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;


class ScheduleTransferControllerTest {

    @Mock
    private ScheduleTransferService transferService;

    @InjectMocks
    private ScheduleTransferController transferController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa os mocks
    }

    @Test
    void testScheduleTransfer_Success() {
        // Arrange
        TransferDto transferDto = new TransferDto();
        doNothing().when(transferService).scheduleTransfer(any(TransferDto.class));

        // Act
        ResponseEntity<?> response = transferController.scheduleTransfer(transferDto);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(transferService, times(1)).scheduleTransfer(any(TransferDto.class));
    }

    @Test
    void testScheduleTransfer_Exception() {
        // Arrange
        TransferDto transferDto = new TransferDto();
        doThrow(new RuntimeException("Erro no serviço")).when(transferService).scheduleTransfer(any(TransferDto.class));

        // Act
        ResponseEntity<?> response = transferController.scheduleTransfer(transferDto);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Erro ao processar a transferência", response.getBody());
        verify(transferService, times(1)).scheduleTransfer(any(TransferDto.class));
    }

    @Test
    void testListTransfer_Success() {
        // Arrange
        List<TransferDto> transfers = Collections.singletonList(new TransferDto());
        when(transferService.returnAllScheduledTransfers()).thenReturn(transfers);

        // Act
        ResponseEntity<Object> response = transferController.listTransfer();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(transfers, response.getBody());
        verify(transferService, times(1)).returnAllScheduledTransfers();
    }

    @Test
    void testListTransfer_Exception() {
        // Arrange
        when(transferService.returnAllScheduledTransfers()).thenThrow(new RuntimeException("Erro no serviço"));

        // Act
        ResponseEntity<Object> response = transferController.listTransfer();

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Erro ao processar a listagem de transferencia", response.getBody());
        verify(transferService, times(1)).returnAllScheduledTransfers();
    }
}