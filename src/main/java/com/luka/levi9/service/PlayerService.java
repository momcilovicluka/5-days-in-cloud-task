package com.luka.levi9.service;

import org.springframework.stereotype.Service;

import com.luka.levi9.exception.PlayerAlreadyExistsException;
import com.luka.levi9.exception.PlayerNotFoundException;
import com.luka.levi9.model.Player;
import com.luka.levi9.repository.PlayerRepository;

@Service
public class PlayerService {

	private final PlayerRepository playerRepository;

	public PlayerService(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}

	public Player createPlayer(Player player) {
		if (playerRepository.findByNickname(player.getNickname()).isPresent())
			throw new PlayerAlreadyExistsException("Player with nickname " + player.getNickname() + " already exists.");

		return playerRepository.save(player);
	}

	public Player getPlayer(String id) {
		return playerRepository.findById(id)
				.orElseThrow(() -> new PlayerNotFoundException("Player with id " + id + " not found."));
	}
}