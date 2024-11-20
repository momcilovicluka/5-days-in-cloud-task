package com.luka.levi9.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luka.levi9.dto.TeamCreationRequest;
import com.luka.levi9.exception.TeamValidationException;
import com.luka.levi9.model.Player;
import com.luka.levi9.model.Team;
import com.luka.levi9.repository.PlayerRepository;
import com.luka.levi9.repository.TeamRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TeamService {

	private final TeamRepository teamRepository;
	private final PlayerRepository playerRepository;

	@Transactional
	public Team createTeam(TeamCreationRequest teamDto) {
		if (teamRepository.findByTeamName(teamDto.getTeamName()).isPresent())
			throw new TeamValidationException("Team with name " + teamDto.getTeamName() + " already exists.");

		List<Player> players = playerRepository.findAllById(teamDto.getPlayerIds());

		validatePlayers(teamDto, players);

		return saveTeam(teamDto, players);
	}

	private void validatePlayers(TeamCreationRequest teamDto, List<Player> players) {
		if (players.size() != teamDto.getPlayerIds().size())
			throw new TeamValidationException("Some players were not found.");

		if (players.size() != 5)
			throw new TeamValidationException("A team must have exactly 5 players.");

		players.stream().filter(player -> player.getTeam() != null).findAny().ifPresent(player -> {
			throw new TeamValidationException(
					"Player with ID " + player.getId() + " is already in a team and cannot join another.");
		});
	}

	private Team saveTeam(TeamCreationRequest teamDto, List<Player> players) {
		Team newTeam = new Team();
		newTeam.setTeamName(teamDto.getTeamName());
		newTeam.setPlayers(players);

		players.forEach(player -> player.setTeam(newTeam));

		return teamRepository.save(newTeam);
	}
}