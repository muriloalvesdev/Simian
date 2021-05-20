package br.com.simian.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import br.com.simian.domain.model.Statistic;
import br.com.simian.domain.model.entity.DNA;
import br.com.simian.domain.repository.DNARepository;
import br.com.simian.providers.DNAEntityProviderForTests;
import br.com.simian.service.SimianService;

class SimianServiceImplTest {

	private DNARepository repository;
	private SimianService service;

	@BeforeEach
	void setUp() {
		this.repository = mock(DNARepository.class);
		this.service = new SimianServiceImpl(this.repository);
	}

	@ParameterizedTest
	@ArgumentsSource(DNAEntityProviderForTests.class)
	void shouldGetDnaSaved(DNA dnaSimian) {
		// given
		BDDMockito.given(this.repository.findByDna(dnaSimian.getDna())).willReturn(Optional.of(dnaSimian));

		// then
		boolean isSimian = this.service.isSimian(dnaSimian.getDna());

		// when
		verify(this.repository, times(1)).findByDna(dnaSimian.getDna());
		assertTrue(isSimian);
	}

	@ParameterizedTest
	@ArgumentsSource(DNAEntityProviderForTests.class)
	void shouldSaveDna(DNA dnaSimian) {
		// given
		BDDMockito.given(this.repository.findByDna(dnaSimian.getDna())).willReturn(Optional.empty());
		BDDMockito.given(this.repository.save(dnaSimian)).willReturn(dnaSimian);
		
		// then
		boolean isSimian = this.service.isSimian(dnaSimian.getDna());

		// when
		verify(this.repository, times(1)).findByDna(Mockito.any());
		verify(this.repository, times(1)).save(Mockito.any());

		assertTrue(isSimian);
	}

	@ParameterizedTest
	@ArgumentsSource(DNAEntityProviderForTests.class)
	void shouldFindStatistic(DNA dnaSimian) {
		// given
		String[] dnaHumanValid = { "ATGCGA", "CAGTGC", "TTATCT", "AGATGG", "CGCCTA", "TCACTG" };
		DNA dnaHuman = new DNA();
		dnaHuman.setDna(dnaHumanValid);
		dnaHuman.setSimian(false);
		dnaHuman.setUuid(UUID.randomUUID());

		List<DNA> dnas = new ArrayList<>();
		dnas.add(dnaHuman);
		dnas.add(dnaSimian);

		BDDMockito.given(this.repository.findAll()).willReturn(dnas);

		// when
		Statistic result = this.service.statistic();

		// then
		verify(this.repository, times(1)).findAll();
		assertEquals(1, result.getCountHumanDna());
		assertEquals(1, result.getCountMutantDna());
		assertEquals(String.valueOf(1.0), result.getRatio().toString());
	}
}
