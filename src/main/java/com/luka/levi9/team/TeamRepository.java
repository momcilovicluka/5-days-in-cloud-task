package com.luka.levi9.team;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, String> {

	Optional<Team> findByTeamName(String teamName);
}