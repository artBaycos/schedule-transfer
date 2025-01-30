package com.teste.schedule.service;

import org.junit.jupiter.api.Test;


import com.teste.schedule.dto.TransferDto;
import com.teste.schedule.exception.TaxInvalidException;
import com.teste.schedule.repository.ScheduleTransferRepository;
import org.junit.jupiter.api.BeforeEach;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class ScheduleTransferServiceTest {

	@InjectMocks
	private ScheduleTransferService transferService;

	@Mock
	private ScheduleTransferRepository transferRepository;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);  // Inicializa os mocks
	}

	@Test
	public void testTaxCalc_0to0() {
		LocalDate dataTransferencia = LocalDate.now().plusDays(0);
		BigDecimal valorTransferencia = new BigDecimal(1000);

		BigDecimal expectedTax = new BigDecimal(25);  // 2.5% de 1000
		BigDecimal result = transferService.TaxCalc(dataTransferencia, valorTransferencia);

		assertEquals(expectedTax, result);
	}

	@Test
	public void testTaxCalc_1to10() {
		LocalDate dataTransferencia = LocalDate.now().plusDays(5);  // Dentro do intervalo 1-10
		BigDecimal valorTransferencia = new BigDecimal(1000);

		BigDecimal expectedTax = BigDecimal.ZERO;  // Taxa 0% para o intervalo 1-10
		BigDecimal result = transferService.TaxCalc(dataTransferencia, valorTransferencia);

		assertEquals(expectedTax, result);
	}

	@Test
	public void testTaxCalc_11to20() {
		LocalDate dataTransferencia = LocalDate.now().plusDays(15);  // Dentro do intervalo 11-20
		BigDecimal valorTransferencia = new BigDecimal(1000);

		BigDecimal expectedTax = new BigDecimal(82);  // 8.2% de 1000
		BigDecimal result = transferService.TaxCalc(dataTransferencia, valorTransferencia);

		assertEquals(expectedTax, result);
	}

	@Test
	public void testTaxCalc_InvalidDateRange() {
		LocalDate dataTransferencia = LocalDate.now().plusDays(60);  // Fora do intervalo definido
		BigDecimal valorTransferencia = new BigDecimal(1000);

		assertThrows(TaxInvalidException.class, () -> {
			transferService.TaxCalc(dataTransferencia, valorTransferencia);
		});
	}

	@Test
	public void testScheduleTransfer() {
		// Preparar os dados de teste
		TransferDto transferencia = new TransferDto();
		transferencia.setTransferDate(LocalDate.now().plusDays(5));  // Data dentro do intervalo 1-10
		transferencia.setValue(new BigDecimal(1000));

		// Configurar o mock do repositório
		when(transferRepository.save(transferencia)).thenReturn(transferencia);

		// Executar o método
		TransferDto result = transferService.scheduleTransfer(transferencia);

		// Validar os resultados
		assertNotNull(result);
		assertEquals(transferencia.getValue(), result.getValue());
		assertEquals(transferencia.getTransferDate(), result.getTransferDate());
		assertEquals(BigDecimal.ZERO, result.getTax());  // Espera-se que a taxa seja 0 para esse intervalo
	}

	@Test
	public void testReturnAllScheduledTransfers() {
		// Mock do repositório
		when(transferRepository.findAll()).thenReturn(java.util.List.of(new TransferDto()));

		// Verifica se o retorno não é nulo e se é uma lista
		assertNotNull(transferService.returnAllScheduledTransfers());
		assertTrue(transferService.returnAllScheduledTransfers() instanceof java.util.List);
	}
}
