package com.luka.levi9.team;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/teams")
public class TeamController {

	private final TeamService teamService;

	@PostMapping()
	public Team createTeam(@Valid @RequestBody TeamCreationRequest request) {
		return teamService.createTeam(request);
	}

	@GetMapping("/{id}")
	public Team getTeam(@PathVariable String id) {
		return teamService.getTeam(id);
	}

}