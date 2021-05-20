package br.com.simian.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.simian.controller.handler.Handler;
import br.com.simian.domain.model.Statistic;
import br.com.simian.dto.DNA;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@RequestMapping(SimianController.PATH)
@RestController
class SimianController {

	private final Handler handler;
	static final String PATH = "/simian";

	@PostMapping
	public ResponseEntity<Object> isSimian(@RequestBody DNA dna) {
		this.handler.validate(dna.getDna());
		return ResponseEntity.ok().build();
	}

	@GetMapping("/stats")
	public ResponseEntity<Statistic> statistic() {
		return ResponseEntity.ok(this.handler.statistic());
	}

}
