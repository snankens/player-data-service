package com.playerdata.playerdataservice.exception;

/**
 * Exception thrown when a player is not found in the data service.
 *
 * @author Sara Nankensky
 */
public class PlayerNotFoundException extends RuntimeException {

  /**
   * Constructs a new PlayerNotFoundException with a specified message.
   *
   * @param message the detail message for the exception.
   */
  public PlayerNotFoundException(String message) {
    super(message);
  }
}
