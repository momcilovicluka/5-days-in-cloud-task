package com.luka.levi9.service;

import org.springframework.stereotype.Service;

import com.luka.levi9.repository.TeamRepository;

@Service
public class TeamService {

	private final TeamRepository teamRepository;

	public TeamService(TeamRepository teamRepository) {
		this.teamRepository = teamRepository;
	}
}