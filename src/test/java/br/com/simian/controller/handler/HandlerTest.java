package br.com.simian.controller.handler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import br.com.simian.domain.model.Statistic;
import br.com.simian.exception.DNAInvalidException;
import br.com.simian.providers.StatisticProviderForTest;
import br.com.simian.service.SimianService;

class HandlerTest {

	private Handler handler;
	private SimianService service;

	@BeforeEach
	void setUp() {
		this.service = mock(SimianService.class);
		this.handler = new Handler(this.service);
	}

	@ParameterizedTest
	@ArgumentsSource(StatisticProviderForTest.class)
	void shouldGetStatistic(Statistic statistic) {
		// given
		BDDMockito.given(this.service.statistic()).willReturn(statistic);

		// then
		Statistic result = this.service.statistic();

		// when
		verify(this.service, times(1)).statistic();
		assertEquals(statistic.getCountHumanDna(), result.getCountHumanDna());
		assertEquals(statistic.getCountMutantDna(), result.getCountMutantDna());
		assertEquals(statistic.getRatio(), result.getRatio());
	}

	@Test
	void validateWithExpectedDNAInvalidException() {
		// given
		String[] dna = { "LLLLLL", "CAGTGC", "TTATCT", "AGAAGG", "CGCCTA", "TCACTG" };

		// then
		RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> {
			this.handler.validate(dna);
		});

		assertEquals("DNA must contain the following letters: A, T, C, G - Check that all letters are being entered.",
				runtimeException.getMessage());
		assertTrue(runtimeException instanceof DNAInvalidException);
	}

	@Test
	void validateWithSuccess() {
		// given
		String[] dna = { "ATGCGA", "CAGTGC", "TTATCT", "AGAAGG", "CGCCTA", "TCACTG" };

		BDDMockito.given(this.service.isSimian(dna)).willReturn(Boolean.TRUE);

		// when
		this.handler.validate(dna);

		// then
		verify(this.service, times(1)).isSimian(Mockito.any());
	}

}
