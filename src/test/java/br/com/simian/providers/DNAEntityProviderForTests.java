package br.com.simian.providers;

import java.util.UUID;
import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import br.com.simian.domain.model.entity.DNA;

public class DNAEntityProviderForTests implements ArgumentsProvider {

	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
		String[] dnaValid = { "ATGCGA", "CAGTAC", "TTAACT", "AGATGG", "CGCCTA", "TCACTG" };
		DNA dna = new DNA();
		dna.setDna(dnaValid);
		dna.setSimian(true);
		dna.setUuid(UUID.randomUUID());
		return Stream.of(dna).map(Arguments::of);
	}

}
