package com.playerdata.playerdataservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * Represents a player entity with personal and statistical information.
 * This class includes validations for fields such as birth date, death date, and playing statistics.
 *
 * @author Sara Nankensky
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Player {

  /**
   * Unique identifier for the player.
   */
  @Id
  @NotNull(message = "ID is required")
  private String playerId;

  /**
   * Year the player was born. Must be a positive integer.
   */
  @Positive(message = "Birth year must be a positive number")
  private Integer birthYear;

  /**
   * Month the player was born. Valid values are 1 through 12.
   */
  @Min(value = 1, message = "Birth month must be at least 1")
  @Max(value = 12, message = "Birth month must be at most 12")
  private Integer birthMonth;

  /**
   * Day the player was born. Valid values are 1 through 31.
   */
  @Min(value = 1, message = "Birth day must be at least 1")
  @Max(value = 31, message = "Birth day must be at most 31")
  private Integer birthDay;

  /**
   * Country where the player was born.
   */
  private String birthCountry;

  /**
   * State where the player was born.
   */
  private String birthState;

  /**
   * City where the player was born.
   */
  private String birthCity;

  /**
   * Year the player died. Must be a positive integer.
   */
  @Positive(message = "Death year must be a positive number")
  private Integer deathYear;

  /**
   * Month the player died. Valid values are 1 through 12.
   */
  @Min(value = 1, message = "Death month must be at least 1")
  @Max(value = 12, message = "Death month must be at most 12")
  private Integer deathMonth;

  /**
   * Day the player died. Valid values are 1 through 31.
   */
  @Min(value = 1, message = "Death day must be at least 1")
  @Max(value = 31, message = "Death day must be at most 31")
  private Integer deathDay;

  /**
   * Country where the player died.
   */
  private String deathCountry;

  /**
   * State where the player died.
   */
  private String deathState;

  /**
   * City where the player died.
   */
  private String deathCity;

  /**
   * First name of the player.
   */
  @NotNull(message = "First name is required")
  private String firstName;

  /**
   * Last name of the player.
   */
  @NotNull(message = "Last name is required")
  private String lastName;

  /**
   * Given name or nickname of the player.
   */
  private String givenName;

  /**
   * Weight of the player in pounds. Must be a positive integer.
   */
  @Positive(message = "Weight must be a positive number")
  private Integer weight;

  /**
   * Height of the player in inches. Must be a positive integer.
   */
  @Positive(message = "Height must be a positive number")
  private Integer height;

  /**
   * Indicates the hand the player bats with. Valid values are 'L' for left and 'R' for right.
   */
  @Pattern(regexp = "^[LR]$", message = "Bats must be 'L' (left) or 'R' (right)")
  private String bats;

  /**
   * Indicates the hand the player throws with. Valid values are 'L' for left and 'R' for right.
   */
  @Pattern(regexp = "^[LR]$", message = "Throwing hand must be 'L' (left) or 'R' (right)")
  private String throwingHand;

  /**
   * Date of the player's debut game.
   */
  private LocalDate debut;

  /**
   * Date of the player's final game.
   */
  private LocalDate finalGame;

  /**
   * Retro ID of the player, used for record-keeping and identification.
   */
  private String retroId;

  /**
   * Baseball-Reference ID of the player, used for linking player statistics.
   */
  private String bbrefId;

  // region Validation methods

  /**
   * Validates that the death date is after the birthdate if both dates are provided.
   *
   * @return {@code true} if death date is after birthdate or if either is not provided; {@code false} otherwise.
   */
  @AssertTrue(message = "Death date must be after birth date if both are provided")
  private boolean isValidDeathDate() {
    if (birthYear != null && deathYear != null) {
      int birthMonthValue = (birthMonth != null) ? birthMonth : 1;
      int birthDayValue = (birthDay != null) ? birthDay : 1;
      int deathMonthValue = (deathMonth != null) ? deathMonth : 1;
      int deathDayValue = (deathDay != null) ? deathDay : 1;

      try {
        LocalDate birthDate = LocalDate.of(birthYear, birthMonthValue, birthDayValue);
        LocalDate deathDate = LocalDate.of(deathYear, deathMonthValue, deathDayValue);
        return deathDate.isAfter(birthDate);
      } catch (DateTimeException e) {
        return false;
      }
    }
    return true;
  }

  /**
   * Validates that the debut date is before or on the same day as the final game date.
   *
   * @return {@code true} if the debut date is before or equal to the final game date, or if either date is not provided.
   */
  @AssertTrue(message = "Debut date must be before or on the same day as the final game date")
  private boolean isValidDebutDate() {
    if (debut == null || finalGame == null) {
      return true;
    }
    return debut.isBefore(finalGame) || debut.isEqual(finalGame);
  }
  // endregion
}
