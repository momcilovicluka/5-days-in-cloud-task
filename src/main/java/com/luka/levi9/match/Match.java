package com.luka.levi9.match;

import com.luka.levi9.team.Team;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Match {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	@ManyToOne(optional = false)
	private Team team1;

	@ManyToOne(optional = false)
	private Team team2;

	@ManyToOne
	private Team winningTeam;

	private int duration;

	public Team getOpponentTeam(Team team) {
		return team1.equals(team) ? team2 : team1;
	}
}
