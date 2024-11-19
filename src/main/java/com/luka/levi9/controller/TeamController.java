package com.luka.levi9.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luka.levi9.model.Team;
import com.luka.levi9.service.TeamService;

@RestController
@RequestMapping("/teams")
public class TeamController {

	private final TeamService teamService;

	public TeamController(TeamService teamService) {
		this.teamService = teamService;
	}

	@PostMapping()
	public Team createTeam(@RequestBody Team team) {
		return teamService.createTeam(team);
	}
}