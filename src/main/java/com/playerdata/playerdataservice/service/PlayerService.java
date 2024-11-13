package com.playerdata.playerdataservice.service;

import com.opencsv.CSVReader;
import com.playerdata.playerdataservice.dto.PlayerDTO;
import com.playerdata.playerdataservice.exception.PlayerNotFoundException;
import com.playerdata.playerdataservice.model.Player;
import com.playerdata.playerdataservice.repository.PlayerRepository;
import com.playerdata.playerdataservice.mapper.PlayerMapper;
import com.playerdata.playerdataservice.util.ParsingUtils;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/** Service class for managing player data. */
@Service
@Validated
public class PlayerService {

  private static final Logger logger = LoggerFactory.getLogger(PlayerService.class);
  private final PlayerRepository playerRepository;
  private final Validator validator;

  /**
   * Constructs a PlayerService with repository and validator.
   *
   * @param playerRepository repository for player data
   * @param validator validator for player constraints
   */
  public PlayerService(PlayerRepository playerRepository, Validator validator) {
    this.playerRepository = playerRepository;
    this.validator = validator;
  }

  /**
   * Loads players from a CSV file into the database.
   *
   * @throws Exception if an error occurs during loading
   */
  @PostConstruct
  @Transactional
  public void loadPlayers() throws Exception {
    try (CSVReader csvReader = new CSVReader(new FileReader("./player.csv"))) {
      String[] values;
      csvReader.readNext(); // Skip header
      while ((values = csvReader.readNext()) != null) {
        Player player = parsePlayer(values);
        if (isValidPlayer(player)) {
          playerRepository.save(player);
        }
      }
    } catch (Exception e) {
      logger.error("Error loading players", e);
      throw new Exception("Failed to load player data", e);
    }
  }

  /**
   * Validates a player entity.
   *
   * @param player player entity to validate
   * @return true if valid, false otherwise
   */
  private boolean isValidPlayer(Player player) {
    Set<ConstraintViolation<Player>> violations = validator.validate(player);
    if (!violations.isEmpty()) {
      String errors =
          violations.stream()
              .map(ConstraintViolation::getMessage)
              .collect(Collectors.joining(", "));
      logger.warn("{}: Skipping player due to validation errors: {}", player.getPlayerId(), errors);
      return false;
    }
    return true;
  }

  /**
   * Parses CSV values into a Player entity.
   *
   * @param values CSV values
   * @return Player entity
   */
  private Player parsePlayer(String[] values) {
    return new Player(
        values[0],
        ParsingUtils.parseInteger(values[1]),
        ParsingUtils.parseInteger(values[2]),
        ParsingUtils.parseInteger(values[3]),
        values[4],
        values[5],
        values[6],
        ParsingUtils.parseNullableInteger(values[7]),
        ParsingUtils.parseNullableInteger(values[8]),
        ParsingUtils.parseNullableInteger(values[9]),
        values[10],
        values[11],
        values[12],
        values[13],
        values[14],
        values[15],
        ParsingUtils.parseInteger(values[16]),
        ParsingUtils.parseInteger(values[17]),
        values[18],
        values[19],
        ParsingUtils.parseLocalDate(values[20]),
        ParsingUtils.parseLocalDate(values[21]),
        values[22],
        values[23]);
  }

  /**
   * Retrieves all players as DTOs.
   *
   * @return list of PlayerDTOs
   */
  public List<PlayerDTO> getAllPlayers() {
    return playerRepository.findAll().stream()
        .map(PlayerMapper.INSTANCE::toDTO)
        .collect(Collectors.toList());
  }

  /**
   * Retrieves a player by ID as a DTO.
   *
   * @param playerId ID of the player
   * @return PlayerDTO if found
   * @throws PlayerNotFoundException if the player is not found
   */
  public PlayerDTO getPlayerDTOById(String playerId) throws PlayerNotFoundException {
    return playerRepository.findById(playerId)
            .map(PlayerMapper.INSTANCE::toDTO)
            .orElseThrow(() -> new PlayerNotFoundException("Player with ID " + playerId + " not found"));
  }

}
