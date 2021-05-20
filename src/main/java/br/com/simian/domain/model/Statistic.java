package br.com.simian.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Statistic {

	@JsonProperty("count_mutant_dna")
	private int countMutantDna;

	@JsonProperty("count_human_dna")
	private int countHumanDna;

	@JsonProperty("ratio")
	private Double ratio;

}
