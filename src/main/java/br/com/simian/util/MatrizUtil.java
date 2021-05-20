package br.com.simian.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MatrizUtil {

	public static final boolean validateDiagonalFromLeftToRight(String[] dna) {
		int count = 0;
		for (int i = 1; i < dna.length; i++) {
			int lastIndex = dna.length - i;
			for (int x = 0; x < dna.length; x++) {
				if (dna.length == x + 1) {
					break;
				}

				if (count > 3) {
					break;
				}

				if (x >= dna.length) {
					break;
				}
				
				
				int previousLastIndex = lastIndex - 1;

				int previousDnaIndex = x + 1;
				if(lastIndex < 0 || previousLastIndex < 0) {
					break;
				}
				char lastValue = dna[x].charAt(lastIndex);
				char previousLast = dna[previousDnaIndex].charAt(previousLastIndex);
				if (lastValue == previousLast) {
					if (x == 0) {
						count += 2;
					} else {
						count += 1;
					}
				} else if (count != 0) {
					count = 0;
				}
				lastIndex -= 1;
			}
		}
		return count > 3;
	}

	public static final boolean validateDiagonalFromRightToLeft(String[] dna) {
		int count = 0;
		for (int x = 0; x < dna.length; x++) {
			if (count > 3) {
				break;
			}
			int nextIndex = x != dna.length ? x + 1 : dna.length - 1;
			if (nextIndex >= dna.length) {
				break;
			}
			char actual = dna[x].charAt(x);
			char nextPosition = dna[nextIndex].charAt(nextIndex);

			if (actual == nextPosition) {
				if (x == 0) {
					count = 2;
				} else {
					count += 1;
				}
			} else if (count != 0) {
				count = 0;
			}
		}
		return count > 3;
	}

	public static final boolean validateColumns(String[] dna, int columnIndex) {
		int count = 0;
		for (int x = 0; x < dna.length; x++) {
			if (count > 3) {
				break;
			}
			char actual = dna[x].charAt(columnIndex);
			int nextPosition = 1;
			if (x != 0) {
				nextPosition += 1;
				if(x == nextPosition) {
					nextPosition += 1;
				}
				if(nextPosition == dna.length) {
					break;
				}
			}
			char next = dna[nextPosition].charAt(columnIndex);

			if (next == actual) {
				if (x == 0) {
					count = 2;
				} else {
					count += 1;
				}
			} else if (count != 0) {
				count = 0;
			}
		}

		return count > 3;
	}

	public static final boolean validateLine(String line) {
		int count = 0;
		for (int x = 1; x < line.length(); x++) {
			if (count > 3) {
				break;
			}
			int position = x - 1;
			char previous = line.charAt(position);
			char actual = line.charAt(x);
			if (previous == actual) {
				if (position == 0 || count == 0) {
					count = 2;
				} else {
					count += 1;
				}
			} else if (count != 0) {
				count = 0;
			}
		}
		return count > 3;
	}

}
