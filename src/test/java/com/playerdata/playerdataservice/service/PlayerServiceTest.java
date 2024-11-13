package com.playerdata.playerdataservice.service;

import com.playerdata.playerdataservice.dto.PlayerDTO;
import com.playerdata.playerdataservice.exception.PlayerNotFoundException;
import com.playerdata.playerdataservice.mapper.PlayerMapper;
import com.playerdata.playerdataservice.model.Player;
import com.playerdata.playerdataservice.repository.PlayerRepository;
import com.playerdata.playerdataservice.util.ParsingUtils;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {

  @Mock private PlayerRepository playerRepository;

  @Mock private Validator validator;

  @Mock private Logger logger;

  @InjectMocks private PlayerService playerService;

  private Player samplePlayer;
  private PlayerDTO samplePlayerDTO;

  @BeforeEach
  void setUp() {
    samplePlayer =
        new Player(
            "aaronha01",
            1934,
            2,
            5,
            "USA",
            "AL",
            "Mobile",
            1984,
            8,
            7,
            "USA",
            "GA",
            "Atlanta",
            "Tommie",
            "Aaron",
            "Tommie Lee",
            100,
            90,
            "R",
            "R",
            ParsingUtils.parseLocalDate("1962-04-10"),
            ParsingUtils.parseLocalDate("1971-09-26"),
            "aarot101",
            "aaronto01");

    samplePlayerDTO = PlayerMapper.INSTANCE.toDTO(samplePlayer);
  }

  @Test
  void testGetAllPlayers() {
    when(playerRepository.findAll()).thenReturn(Collections.singletonList(samplePlayer));

    List<PlayerDTO> players = playerService.getAllPlayers();

    assertEquals(1, players.size());
    assertEquals(samplePlayerDTO, players.get(0));
  }

  @Test
  void testGetPlayerDTOById_PlayerFound() throws PlayerNotFoundException {
    when(playerRepository.findById("aaronha01")).thenReturn(Optional.of(samplePlayer));

    PlayerDTO result = playerService.getPlayerDTOById("aaronha01");

    assertNotNull(result);
    assertEquals(samplePlayerDTO, result);
  }

  @Test
  void testGetPlayerDTOById_PlayerNotFound() {
    when(playerRepository.findById("nonExistentId")).thenReturn(Optional.empty());

    assertThrows(
        PlayerNotFoundException.class, () -> playerService.getPlayerDTOById("nonExistentId"));
  }
}
