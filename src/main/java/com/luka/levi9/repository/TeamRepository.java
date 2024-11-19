package com.luka.levi9.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luka.levi9.model.Team;

public interface TeamRepository extends JpaRepository<Team, String> {
}