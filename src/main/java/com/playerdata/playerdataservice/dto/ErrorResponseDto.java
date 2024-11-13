package com.playerdata.playerdataservice.dto;

import lombok.*;

/**
 * Data Transfer Object for handling error responses in the player data service.
 * This class is used to encapsulate error details that can be sent as a response
 * when an error occurs.
 *
 * @author Sara Nankensky
 */
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDto {

  /**
   * The HTTP status code of the error response.
   */
  private int status;

  /**
   * The error description or code, providing more details on the error type.
   */
  private String error;

  /**
   * A message providing further explanation of the error.
   */
  private String message;

  /**
   * The path or endpoint where the error occurred.
   */
  private String path;

  /**
   * Gets the HTTP status code of the error response.
   *
   * @return the status code of the error response.
   */
  public int getStatusCode() {
    return status;
  }
}
