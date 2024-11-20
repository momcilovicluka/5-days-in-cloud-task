package com.luka.levi9.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.luka.levi9.match.Match;
import com.luka.levi9.player.Player;
import com.luka.levi9.team.Team;

class PlayerTest {

	private Player player;
	private Match matchMock;
	private Team teamMock;
	private Team opponentMock;

	@BeforeEach
	void setUp() {
		player = new Player();
		player.setNickname("TestPlayer");
		player.setElo(1500);
		player.setHoursPlayed(0);
		player.setWins(0);
		player.setLosses(0);

		matchMock = mock(Match.class);
		teamMock = mock(Team.class);
		opponentMock = mock(Team.class);

		when(matchMock.getDuration()).thenReturn(60);
		when(matchMock.getWinningTeam()).thenReturn(teamMock);
		when(matchMock.getOpponentTeam(teamMock)).thenReturn(opponentMock);

		when(opponentMock.getAverageElo()).thenReturn(1400.0);

		player.setTeam(teamMock);
	}

	@Test
	void testUpdateStats_WinningTeam() {
		player.setTeam(teamMock);

		player.updateStats(matchMock);

		assertEquals(1, player.getWins());
		assertEquals(0, player.getLosses());
		assertEquals(60, player.getHoursPlayed());
		assertEquals(50, player.getRatingAdjustment());
		assertEquals(1500 + 50 * (1 - 0.64), player.getElo(), 0.01);
	}

	@Test
	void testUpdateStats_LosingTeam() {
		when(matchMock.getWinningTeam()).thenReturn(opponentMock);
		when(matchMock.getDuration()).thenReturn(60);
		when(matchMock.getOpponentTeam(teamMock)).thenReturn(opponentMock);
		when(opponentMock.getAverageElo()).thenReturn(1400.0);

		player.updateStats(matchMock);

		assertEquals(1, player.getLosses());
		assertEquals(0, player.getWins());
		assertEquals(60, player.getHoursPlayed());
		verify(matchMock, times(2)).getWinningTeam();
	}

	@Test
	void testUpdateElo_IndirectValidation() {
		player.setTeam(teamMock);

		player.updateStats(matchMock);

		double expectedEloIncrease = 50 * (1 - 0.64);
		assertEquals(1500 + expectedEloIncrease, player.getElo(), 0.01);
	}

	@Test
	void testHoursPlayedAdjustment() {
		player.setTeam(teamMock);

		player.updateStats(matchMock);
		assertEquals(50, player.getRatingAdjustment());

		player.setHoursPlayed(600);
		player.updateStats(matchMock);
		assertEquals(40, player.getRatingAdjustment());

		player.setHoursPlayed(2000);
		player.updateStats(matchMock);
		assertEquals(30, player.getRatingAdjustment());
	}
}
