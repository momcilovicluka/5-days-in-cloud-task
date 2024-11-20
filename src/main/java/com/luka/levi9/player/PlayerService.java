package com.luka.levi9.player;

import java.util.List;

import org.springframework.stereotype.Service;

import com.luka.levi9.common.exception.AlreadyExistsException;
import com.luka.levi9.common.exception.NotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PlayerService {

	private final PlayerRepository playerRepository;

	public Player createPlayer(Player player) {
		if (playerRepository.findByNickname(player.getNickname()).isPresent())
			throw new AlreadyExistsException("Player with nickname " + player.getNickname() + " already exists.");

		return playerRepository.save(player);
	}

	public Player getPlayer(String id) {
		return playerRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Player with id " + id + " not found."));
	}

	public List<Player> getPlayers() {
		return playerRepository.findAll();
	}
}