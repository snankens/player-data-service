package com.playerdata.playerdataservice.controller;

import com.playerdata.playerdataservice.dto.PlayerDTO;
import com.playerdata.playerdataservice.service.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** REST controller for player-related endpoints.*/
@RestController
@RequestMapping("/api/players")
public class PlayerController {

  private static final Logger logger = LoggerFactory.getLogger(PlayerController.class);
  private final PlayerService playerService;

  /**
   * Constructs PlayerController with the specified PlayerService.
   *
   * @param playerService the player service
   */
  public PlayerController(PlayerService playerService) {
    this.playerService = playerService;
  }

  /**
   * Retrieves all players.
   *
   * @return ResponseEntity containing the list of all players
   */
  @GetMapping
  public ResponseEntity<List<PlayerDTO>> getAllPlayers() {
    logger.info("Fetching all players");
    List<PlayerDTO> players = playerService.getAllPlayers();
    return ResponseEntity.ok(players);
  }

  /**
   * Retrieves a player by their ID.
   *
   * @param playerID the ID of the player
   * @return ResponseEntity containing the player if found, or not found status
   */
  @GetMapping("/{playerID}")
  public ResponseEntity<PlayerDTO> getPlayerById(@PathVariable String playerID) {
    logger.info("Fetching player with ID: {}", playerID);
    PlayerDTO playerDTO = playerService.getPlayerDTOById(playerID);
    return ResponseEntity.ok(playerDTO);
  }
}
