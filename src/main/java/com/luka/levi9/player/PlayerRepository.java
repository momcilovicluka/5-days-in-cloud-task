package com.luka.levi9.player;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, String> {

	Optional<Player> findByNickname(String nickname);
}