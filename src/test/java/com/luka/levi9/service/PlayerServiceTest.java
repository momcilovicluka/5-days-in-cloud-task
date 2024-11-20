package com.luka.levi9.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.luka.levi9.common.exception.AlreadyExistsException;
import com.luka.levi9.common.exception.NotFoundException;
import com.luka.levi9.player.Player;
import com.luka.levi9.player.PlayerRepository;
import com.luka.levi9.player.PlayerService;

class PlayerServiceTest {

	@Mock
	private PlayerRepository playerRepository;

	@InjectMocks
	private PlayerService playerService;

	private Player player;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		player = new Player();
		player.setId("123");
		player.setNickname("testPlayer");
	}

	@Test
	void testCreatePlayer_Success() {
		when(playerRepository.findByNickname("testPlayer")).thenReturn(Optional.empty());
		when(playerRepository.save(player)).thenReturn(player);

		Player result = playerService.createPlayer(player);

		assertNotNull(result);
		assertEquals("testPlayer", result.getNickname());
		verify(playerRepository).findByNickname("testPlayer");
		verify(playerRepository).save(player);
	}

	@Test
	void testCreatePlayer_AlreadyExists() {
		when(playerRepository.findByNickname("testPlayer")).thenReturn(Optional.of(player));

		assertThrows(AlreadyExistsException.class, () -> playerService.createPlayer(player));
		verify(playerRepository).findByNickname("testPlayer");
		verify(playerRepository, never()).save(any());
	}

	@Test
	void testGetPlayer_Success() {
		when(playerRepository.findById("123")).thenReturn(Optional.of(player));

		Player result = playerService.getPlayer("123");

		assertNotNull(result);
		assertEquals("123", result.getId());
		verify(playerRepository).findById("123");
	}

	@Test
	void testGetPlayer_NotFound() {
		when(playerRepository.findById("123")).thenReturn(Optional.empty());

		assertThrows(NotFoundException.class, () -> playerService.getPlayer("123"));
		verify(playerRepository).findById("123");
	}

	@Test
	void testGetPlayers_Success() {
		Player player2 = new Player();
		player2.setId("456");
		player2.setNickname("testPlayer2");

		when(playerRepository.findAll()).thenReturn(List.of(player, player2));

		List<Player> result = playerService.getPlayers();

		assertNotNull(result);
		assertEquals(2, result.size());
		assertTrue(result.contains(player));
		assertTrue(result.contains(player2));
		verify(playerRepository).findAll();
	}
}
