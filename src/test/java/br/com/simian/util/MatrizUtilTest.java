package br.com.simian.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

class MatrizUtilTest {

	@Test
	void shouldValidateDiagonalFromLeftToRightWithReturnTrue() {
		String[] dna = { "ATGCGA", "CAGTAC", "TTAACT", "AGATGG", "CGCCTA", "TCACTG" };
		assertTrue(MatrizUtil.validateDiagonalFromLeftToRight(dna));
	}

	@Test
	void shouldValidateDiagonalFromLeftToRightWithReturnFalse() {
		String[] dna = { "ATGCGA", "CAGTGC", "TTATCT", "AGAAGG", "CGCCTA", "TCACTG" };
		assertFalse(MatrizUtil.validateDiagonalFromLeftToRight(dna));
	}

	@Test
	void shouldValidateDiagonalFromRightToLeftWithReturnTrue() {
		String[] dna = { "ATGCGA", "CAGTGC", "TTATCT", "AGAAGG", "CGCCTA", "TCACTG" };
		assertTrue((MatrizUtil.validateDiagonalFromRightToLeft(dna)));
	}

	@Test
	void shouldValidateDiagonalFromRightToLeftWithReturnFalse() {
		String[] dna = { "ATGCGA", "CAGTGC", "TTATCT", "AGATGG", "CGCCTA", "TCACTG" };
		assertFalse((MatrizUtil.validateDiagonalFromRightToLeft(dna)));
	}

	@Test
	void shouldValidateLinesWithReturnTrue() {
		boolean isSimian = false;
		String[] dna = { "AAGGGG", "CGCCTA", "TCACTG" };
		for (int i = 0; i < dna.length; i++) {
			if (MatrizUtil.validateLine(dna[i])) {
				isSimian = true;
				break;
			}
		}
		assertTrue(isSimian);
	}

	@Test
	void shouldValidateLinesWithReturnFalse() {
		boolean isSimian = false;
		String[] dna = { "AAGTGG", "CGCCTA", "TCACTG" };
		for (int i = 0; i < dna.length; i++) {
			if (MatrizUtil.validateLine(dna[i])) {
				isSimian = true;
				break;
			}
		}
		assertFalse(isSimian);
	}

	@Test
	void shouldValidateColumnsWithReturnTrue() {
		boolean isSimian = false;
		String[] dna = { "ATGCAAGATGCAAG", "CGGCGCGATGCAAG", "GTCACTGATGCAAG", "AATTGGGATGCTGG", "CCGCTATATGCCAG",
				"TCACTGAATGCACG" };
		for (int i = 0; i <= dna.length; i++) {
			if (MatrizUtil.validateColumns(dna, i)) {
				isSimian = true;
				break;
			}
		}
		assertTrue(isSimian);
	}

	@Test
	void shouldValidateColumnsWithReturnFalse() {
		boolean isSimian = false;
		String[] dna = { "ATGCGA", "CAGTGC", "TTATCT", "AGAGGG", "CGCCTA", "TCACTG" };
		for (int i = 0; i < dna.length; i++) {
			if (MatrizUtil.validateColumns(dna, i)) {
				isSimian = true;
				break;
			}
		}
		assertFalse(isSimian);
	}

}
