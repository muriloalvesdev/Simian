package br.com.simian.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiException {

	private String message;
	private int status;
	
}
