package com.luka.levi9.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luka.levi9.model.Player;
import com.luka.levi9.service.PlayerService;

@RestController
@RequestMapping("/players")
public class PlayerController {

	private final PlayerService playerService;

	public PlayerController(PlayerService playerService) {
		this.playerService = playerService;
	}

	@PostMapping("/create")
	public Player createPlayer(@RequestBody Player player) {
		return playerService.createPlayer(player);
	}
}