package com.luka.levi9.player;

import org.hibernate.annotations.NamedQuery;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.luka.levi9.match.Match;
import com.luka.levi9.team.Team;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@NamedQuery(name = "Player.findAll", query = "SELECT p FROM Player p")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "nickname" }))
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id", updatable = false, nullable = false)
	private String id;
	@NotBlank(message = "Nickname is mandatory")
	private String nickname;
	private int wins;
	private int losses;
	private double elo;
	private int hoursPlayed;
	private int ratingAdjustment = 50;
	@ManyToOne
	@JsonIgnore
	private Team team;

	@JsonProperty("teamId")
	public String getTeamId() {
		return team != null ? team.getId() : null;
	}

	public void updateStats(Match match) {
		this.hoursPlayed += match.getDuration();

		this.ratingAdjustment = calculateAdjustmentConstant();

		if (match.getWinningTeam().equals(this.getTeam()))
			this.wins++;
		else
			this.losses++;

		updateElo(match.getWinningTeam(), match.getOpponentTeam(this.getTeam()));
	}

	private void updateElo(Team winningTeam, Team opponentTeam) {
		double matchOutcomeCoefficient = calculateMatchOutcomeCoefficient(winningTeam);
		double expectedElo = calculateExpectedELO(opponentTeam);
		this.elo += ratingAdjustment * (matchOutcomeCoefficient - expectedElo);
	}

	private double calculateExpectedELO(Team opposingTeam) {
		double opposingTeamAverageElo = opposingTeam.getAverageElo();

		return 1 / (1 + Math.pow(10, (opposingTeamAverageElo - this.elo) / 400));
	}

	private double calculateMatchOutcomeCoefficient(Team winningTeam) {
		if (winningTeam == null)
			return 0.5;
		if (winningTeam.equals(this.getTeam()))
			return 1.0;
		return 0.0;
	}

	private int calculateAdjustmentConstant() {
		if (hoursPlayed < 500)
			return 50;
		if (hoursPlayed < 1000)
			return 40;
		if (hoursPlayed < 3000)
			return 30;
		if (hoursPlayed < 5000)
			return 20;
		return 10;
	}
}