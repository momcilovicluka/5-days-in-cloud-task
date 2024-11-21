package com.luka.levi9.match;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchRequest {

	@NotBlank(message = "Team1 ID cannot be null")
	private String team1Id;

	@NotBlank(message = "Team2 ID cannot be null")
	private String team2Id;

	@NotBlank(message = "Winning team ID cannot be null")
	private String winningTeamId;

	@Min(value = 1, message = "Duration must be at least 1 hour")
	private int duration;
}
