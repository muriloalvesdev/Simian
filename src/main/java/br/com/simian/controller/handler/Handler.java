package br.com.simian.controller.handler;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import br.com.simian.domain.model.Statistic;
import br.com.simian.exception.DNAInvalidException;
import br.com.simian.exception.DNANotSimianException;
import br.com.simian.service.SimianService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Component
public class Handler {
	private static final String DNA_INVALID_MSG = "DNA must contain the following letters: A, T, C, G - Check that all letters are being entered.";

	private final SimianService service;

	public void validate(String[] dna) {
		Arrays.asList(dna).forEach(value -> {
			if (!isValid(value)) {
				throw new DNAInvalidException(DNA_INVALID_MSG);
			}
		});
		if (!this.service.isSimian(dna)) {
			throw new DNANotSimianException("Not recognized as simian.");
		}
	}

	public Statistic statistic() {
		return this.service.statistic();
	}

	private static boolean isValid(String dna) {
		if (!dna.contains("A") && !dna.contains("T") && !dna.contains("C") && !dna.contains("G")) {
			return false;
		}
		return (dna.contains("A") || dna.contains("T") || dna.contains("C") || dna.contains("G"));
	}

}
