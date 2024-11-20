package com.luka.levi9.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luka.levi9.model.Team;

public interface TeamRepository extends JpaRepository<Team, String> {

	Optional<Team> findByTeamName(String teamName);
}