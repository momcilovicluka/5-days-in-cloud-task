package com.luka.levi9.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.luka.levi9.player.Player;
import com.luka.levi9.player.PlayerController;
import com.luka.levi9.player.PlayerService;

@WebMvcTest(PlayerController.class)
class PlayerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PlayerService playerService;

	private Player player;

	@BeforeEach
	void setUp() {
		player = new Player();
		player.setId("123");
		player.setNickname("testPlayer");
	}

	@Test
	void testGetPlayer_Success() throws Exception {
		when(playerService.getPlayer("123")).thenReturn(player);

		mockMvc.perform(MockMvcRequestBuilders.get("/players/123")).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value("123")).andExpect(jsonPath("$.nickname").value("testPlayer"));

		verify(playerService).getPlayer("123");
	}

	@Test
	void testGetPlayers_Success() throws Exception {
		Player player2 = new Player();
		player2.setId("456");
		player2.setNickname("testPlayer2");
		when(playerService.getPlayers()).thenReturn(List.of(player, player2));

		mockMvc.perform(MockMvcRequestBuilders.get("/players")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].nickname").value("testPlayer"))
				.andExpect(jsonPath("$[1].nickname").value("testPlayer2"));

		verify(playerService).getPlayers();
	}
}
