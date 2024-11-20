package com.luka.levi9.service;

import org.springframework.stereotype.Service;

import com.luka.levi9.model.Team;
import com.luka.levi9.repository.TeamRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TeamService {

	private final TeamRepository teamRepository;

	}

	public Team createTeam(Team team) {
		return teamRepository.save(team);
	}
}