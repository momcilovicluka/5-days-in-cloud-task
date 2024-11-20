package com.luka.levi9.match;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchRequest {

	@NotNull(message = "Team1 ID cannot be null")
	private String team1Id;

	@NotNull(message = "Team2 ID cannot be null")
	private String team2Id;

	private String winningTeamId;

	@Min(value = 1, message = "Duration must be at least 1 hour")
	private int duration;
}
