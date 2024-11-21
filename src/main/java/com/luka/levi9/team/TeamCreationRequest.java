package com.luka.levi9.team;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamCreationRequest {
	@NotBlank(message = "Team name is mandatory")
	private String teamName;
	@JsonAlias("players")
	@NotNull(message = "Team must have players")
	private List<String> playerIds;
}