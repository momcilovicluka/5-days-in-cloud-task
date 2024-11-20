package com.luka.levi9.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamCreationRequest {
	private String teamName;
	@JsonAlias("players")
	private List<String> playerIds;
}