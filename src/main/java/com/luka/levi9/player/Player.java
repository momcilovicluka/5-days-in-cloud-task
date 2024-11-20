package com.luka.levi9.player;

import org.hibernate.annotations.NamedQuery;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.luka.levi9.team.Team;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
	private String nickname;
	private int wins;
	private int losses;
	private int elo;
	private int hoursPlayed;
	@ManyToOne
	@JsonIgnore
	private Team team;

	@JsonProperty("teamId")
	public String getTeamId() {
		return team != null ? team.getId() : null;
	}
}