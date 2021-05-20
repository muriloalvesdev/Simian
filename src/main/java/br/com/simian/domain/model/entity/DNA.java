package br.com.simian.domain.model.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "dna")
public class DNA {

	@Id
	@GeneratedValue
	private UUID uuid;

	@Column(name = "dna", unique = true)
	private String[] dna;

	@Column(name = "simian")
	private boolean isSimian;

	DNA(String[] dna, boolean isSimian) {
		this.dna = dna;
		this.isSimian = isSimian;
	}

	public boolean isSimian() {
		return this.isSimian == true;
	}

	public boolean isNotSimian() {
		return !this.isSimian();
	}

	public static final DNA build(String[] dna, boolean isSimian) {
		return new DNA(dna, isSimian);
	}
}
