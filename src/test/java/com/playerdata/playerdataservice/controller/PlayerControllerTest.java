package com.playerdata.playerdataservice.controller;

import com.playerdata.playerdataservice.dto.PlayerDTO;
import com.playerdata.playerdataservice.exception.PlayerNotFoundException;
import com.playerdata.playerdataservice.service.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PlayerControllerTest {

  @Mock private PlayerService playerService;

  @InjectMocks private PlayerController playerController;

  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(playerController).build();
  }

  @Test
  void testGetAllPlayers_returnsListOfPlayers() throws Exception {
    // Arrange
    PlayerDTO player1 = new PlayerDTO();
    PlayerDTO player2 = new PlayerDTO();
    player1.setPlayerId("1");
    player2.setPlayerId("2");
    List<PlayerDTO> players = Arrays.asList(player1, player2);

    when(playerService.getAllPlayers()).thenReturn(players);

    // Act and Assert
    mockMvc.perform(get("/api/players")).andExpect(status().isOk());

    // Verify service method was called
    verify(playerService, times(1)).getAllPlayers();
  }

  @Test
  void testGetPlayerById_returnsPlayer_whenFound() throws Exception {
    // Arrange
    String playerID = "1";
    PlayerDTO player = new PlayerDTO();
    player.setPlayerId(playerID);
    when(playerService.getPlayerDTOById(playerID)).thenReturn(player);

    // Act
    ResponseEntity<PlayerDTO> response = playerController.getPlayerById(playerID);

    // Assert
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isEqualTo(player);

    // Verify service method was called
    verify(playerService, times(1)).getPlayerDTOById(playerID);
  }

  @Test
  void testGetPlayerById_throwsPlayerNotFoundException_whenNotFound() {
    // Arrange
    String playerID = "999";
    when(playerService.getPlayerDTOById(playerID))
        .thenThrow(new PlayerNotFoundException("Player with ID " + playerID + " not found"));

    // Act & Assert
    Throwable thrown = catchThrowable(() -> playerController.getPlayerById(playerID));
    assertThat(thrown)
        .isInstanceOf(PlayerNotFoundException.class)
        .hasMessageContaining("Player with ID " + playerID + " not found");

    // Verify service method was called
    verify(playerService, times(1)).getPlayerDTOById(playerID);
  }
}
