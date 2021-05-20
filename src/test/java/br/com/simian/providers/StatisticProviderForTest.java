package br.com.simian.providers;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import br.com.simian.domain.model.Statistic;

public class StatisticProviderForTest implements ArgumentsProvider {

	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
		Statistic statistic = new Statistic(1, 1, 1.0);
		return Stream.of(statistic).map(Arguments::of);
	}

}
