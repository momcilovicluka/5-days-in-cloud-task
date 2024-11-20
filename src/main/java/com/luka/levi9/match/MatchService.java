package com.luka.levi9.match;

import org.springframework.stereotype.Service;

import com.luka.levi9.common.exception.NotFoundException;
import com.luka.levi9.common.exception.ValidationException;
import com.luka.levi9.player.Player;
import com.luka.levi9.player.PlayerRepository;
import com.luka.levi9.team.Team;
import com.luka.levi9.team.TeamRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MatchService {

	private final MatchRepository matchRepository;
	private final TeamRepository teamRepository;
	private final PlayerRepository playerRepository;
	@PersistenceContext
	private EntityManager entityManager;

	public Match createMatch(MatchRequest request) {
		validateMatchRequest(request);

		Team team1 = findTeamById(request.getTeam1Id());
		Team team2 = findTeamById(request.getTeam2Id());

		Match match = createMatchFromRequest(request, team1, team2);

		updateTeamPlayersStats(team1, team2, match);

		return saveMatch(team1, team2, match);
	}

	private void validateMatchRequest(MatchRequest request) {
		if (request.getTeam1Id().equals(request.getTeam2Id()))
			throw new ValidationException("Team 1 and Team 2 must be different");

		if (request.getDuration() < 0)
			throw new ValidationException("Duration must be a positive number");

		if (request.getWinningTeamId() != null && !request.getWinningTeamId().equals(request.getTeam1Id())
				&& !request.getWinningTeamId().equals(request.getTeam2Id()))
			throw new ValidationException("Winning team must be one of the teams in the match");
	}

	private Match saveMatch(Team team1, Team team2, Match match) {
		teamRepository.save(team1);
		teamRepository.save(team2);

		return matchRepository.save(match);
	}

	private void updateTeamPlayersStats(Team team1, Team team2, Match match) {
		Team originalTeam = getUnmodifiedTeam(team1);

		updatePlayersStats(team1, match);

		match.setTeam1(originalTeam);

		updatePlayersStats(team2, match);

		match.setTeam1(team1);
	}

	private void updatePlayersStats(Team team1, Match match) {
		for (Player player : team1.getPlayers()) {
			player.updateStats(match);
			playerRepository.save(player);
		}
	}

	private Team getUnmodifiedTeam(Team team1) {
		entityManager.clear();
		Team originalTeam = entityManager.find(Team.class, team1.getId());
		entityManager.detach(originalTeam);
		entityManager.clear();
		return originalTeam;
	}

	private Team findTeamById(String teamId) {
		return teamRepository.findById(teamId)
				.orElseThrow(() -> new NotFoundException("Team with id " + teamId + " not found"));
	}

	private Match createMatchFromRequest(MatchRequest request, Team team1, Team team2) {
		return Match.builder().team1(team1).team2(team2).duration(request.getDuration())
				.winningTeam(determineWinningTeam(request, team1, team2)).build();
	}

	private Team determineWinningTeam(MatchRequest request, Team team1, Team team2) {
		if (request.getWinningTeamId() == null)
			return null;

		return request.getWinningTeamId().equals(team1.getId()) ? team1 : team2;
	}
}