package br.com.simian.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.simian.domain.model.Statistic;
import br.com.simian.domain.model.entity.DNA;
import br.com.simian.domain.repository.DNARepository;
import br.com.simian.service.SimianService;
import br.com.simian.util.MatrizUtil;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Service
class SimianServiceImpl implements SimianService {

	private final DNARepository repository;

	@Override
	public boolean isSimian(String[] dna) {
		boolean isSimian = checkIsSimianByColumns(dna) || checkIsSimianByLines(dna) || checkIsSimianByDiagonal(dna);
		return this.saveResult(dna, isSimian);
	}

	@Override
	public Statistic statistic() {
		Statistic statistic = new Statistic();
		List<DNA> DNAs = this.repository.findAll();
		int countSimian = DNAs.stream().filter(DNA::isSimian).collect(Collectors.toList()).size();
		int countHuman = DNAs.stream().filter(DNA::isNotSimian).collect(Collectors.toList()).size();
		if (countHuman < countSimian) {
			statistic.setRatio(Double.valueOf((double) countHuman / (double) countSimian));
		} else {
			statistic.setRatio(Double.valueOf((double) countSimian / (double) countHuman));
		}
		statistic.setCountHumanDna(countHuman);
		statistic.setCountMutantDna(countSimian);
		return statistic;
	}

	private boolean saveResult(String[] dna, boolean isSimian) {
		this.repository.findByDna(dna).orElseGet(() -> this.repository.save(DNA.build(dna, isSimian)));
		return isSimian;
	}

	private boolean checkIsSimianByDiagonal(String[] dna) {
		if (MatrizUtil.validateDiagonalFromRightToLeft(dna)) {
			return true;
		}

		if (MatrizUtil.validateDiagonalFromLeftToRight(dna)) {
			return true;
		}
		return false;
	}

	private boolean checkIsSimianByLines(String[] dna) {
		boolean isSimian = false;
		for (int i = 0; i < dna.length; i++) {
			if (MatrizUtil.validateLine(dna[i])) {
				isSimian = true;
				break;
			}
		}
		return isSimian;
	}

	private boolean checkIsSimianByColumns(String[] dna) {
		boolean isSimian = false;
		for (int i = 0; i < dna.length; i++) {
			if (MatrizUtil.validateColumns(dna, i)) {
				isSimian = true;
			}
		}
		return isSimian;
	}

}
