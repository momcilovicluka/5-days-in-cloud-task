package com.luka.levi9.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luka.levi9.dto.TeamCreationRequest;
import com.luka.levi9.model.Team;
import com.luka.levi9.service.TeamService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/teams")
public class TeamController {

	private final TeamService teamService;

	@PostMapping()
	public Team createTeam(@RequestBody TeamCreationRequest request) {
		return teamService.createTeam(request);
	}

	}
}