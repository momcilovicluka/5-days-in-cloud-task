package com.luka.levi9.match;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/matches")
public class MatchController {

	private final MatchService matchService;

	@PostMapping()
	@ResponseStatus(HttpStatus.OK)
	public void createMatch(@Valid @RequestBody MatchRequest matchRequest) {
		matchService.createMatch(matchRequest);
	}
}