package com.luka.levi9.team;

import java.util.List;

import com.luka.levi9.player.Player;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@NamedQuery(name = "Team.findAll", query = "SELECT t FROM Team t")
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id", updatable = false, nullable = false)
	private String id;

	private String teamName;

	@JoinColumn(name = "Player_id")
	@OneToMany(fetch = FetchType.EAGER)
	private List<Player> players;

	public double getAverageElo() {
		return players.stream().mapToDouble(Player::getElo).average().orElse(0);
	}
}