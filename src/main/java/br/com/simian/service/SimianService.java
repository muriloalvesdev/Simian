package br.com.simian.service;

import br.com.simian.domain.model.Statistic;

public interface SimianService {
	boolean isSimian (String[] dna);
	Statistic statistic();
}
