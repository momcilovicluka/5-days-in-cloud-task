package com.luka.levi9.player;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/players")
public class PlayerController {

	private final PlayerService playerService;

	@PostMapping("/create")
	public Player createPlayer(@Valid @RequestBody Player player) {
		return playerService.createPlayer(player);
	}

	@GetMapping("/{id}")
	public Player getPlayer(@PathVariable String id) {
		return playerService.getPlayer(id);
	}

	@GetMapping()
	public List<Player> getPlayers() {
		return playerService.getPlayers();
	}
}