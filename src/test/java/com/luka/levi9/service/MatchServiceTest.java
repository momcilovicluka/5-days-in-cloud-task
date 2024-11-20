package com.luka.levi9.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.luka.levi9.common.exception.NotFoundException;
import com.luka.levi9.common.exception.ValidationException;
import com.luka.levi9.match.MatchRepository;
import com.luka.levi9.match.MatchRequest;
import com.luka.levi9.match.MatchService;
import com.luka.levi9.player.PlayerRepository;
import com.luka.levi9.team.TeamRepository;

import jakarta.persistence.EntityManager;

@SpringJUnitConfig
public class MatchServiceTest {

	@Mock
	private MatchRepository matchRepository;

	@Mock
	private TeamRepository teamRepository;

	@Mock
	private PlayerRepository playerRepository;

	@Mock
	private EntityManager entityManager;

	@InjectMocks
	private MatchService matchService;

	public MatchServiceTest() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testCreateMatch_SameTeams() {
		MatchRequest request = new MatchRequest();
		request.setTeam1Id("team1Id");
		request.setTeam2Id("team1Id");

		ValidationException exception = assertThrows(ValidationException.class, () -> {
			matchService.createMatch(request);
		});

		assertEquals("Team 1 and Team 2 must be different", exception.getMessage());
	}

	@Test
	void testCreateMatch_InvalidWinningTeam() {
		MatchRequest request = new MatchRequest();
		request.setTeam1Id("team1Id");
		request.setTeam2Id("team2Id");
		request.setDuration(60);
		request.setWinningTeamId("team3Id");

		ValidationException exception = assertThrows(ValidationException.class, () -> {
			matchService.createMatch(request);
		});

		assertEquals("Winning team must be one of the teams in the match", exception.getMessage());
	}

}
