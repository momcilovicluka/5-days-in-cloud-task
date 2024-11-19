package com.luka.levi9.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luka.levi9.model.Player;

public interface PlayerRepository extends JpaRepository<Player, String> {

	Optional<Player> findByNickname(String nickname);
}