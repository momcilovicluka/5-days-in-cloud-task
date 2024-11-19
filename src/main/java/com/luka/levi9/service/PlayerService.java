package com.luka.levi9.service;

import org.springframework.stereotype.Service;

import com.luka.levi9.repository.PlayerRepository;

@Service
public class PlayerService {

	private final PlayerRepository playerRepository;

	public PlayerService(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}
}