package br.com.simian.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.simian.controller.handler.Handler;
import br.com.simian.domain.model.Statistic;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@RequestMapping(StatisticController.PATH)
@RestController
class StatisticController {

	private final Handler handler;
	static final String PATH = "/stats";

	@GetMapping
	public ResponseEntity<Statistic> statistic() {
		return ResponseEntity.ok(this.handler.statistic());
	}

}
