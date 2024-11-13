package com.playerdata.playerdataservice.exception;

import com.playerdata.playerdataservice.dto.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Global exception handler for handling exceptions across the application. Uses Spring's {@link
 * ControllerAdvice} to apply to all controllers.
 *
 * @author Sara Nankensky
 */
@ControllerAdvice
public class GlobalExceptionHandler {

  /**
   * Handles {@link PlayerNotFoundException} specifically, returning a 404 status code with a
   * relevant error message.
   *
   * @param ex the exception thrown when a player is not found
   * @param request the HTTP request during which the exception occurred
   * @return a {@link ResponseEntity} containing the error response and HTTP status
   */
  @ExceptionHandler(PlayerNotFoundException.class)
  public ResponseEntity<ErrorResponseDto> handlePlayerNotFoundException(
          PlayerNotFoundException ex, HttpServletRequest request) {
    ErrorResponseDto errorResponse = ErrorResponseDto.builder()
            .status(HttpStatus.NOT_FOUND.value())
            .error("Player Not Found")
            .message(ex.getMessage())
            .path(request.getRequestURI())
            .build();

    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

  /**
   * Handles general {@link Exception} instances that are not specifically handled by other
   * exception handlers, returning a 500 status code and a generic error message.
   *
   * @param ex the exception thrown during request processing
   * @param request the HTTP request during which the exception occurred
   * @return a {@link ResponseEntity} containing the error response and HTTP status
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponseDto> handleGeneralException(
      Exception ex, HttpServletRequest request) {

    ErrorResponseDto errorResponse =
        new ErrorResponseDto(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Internal Server Error",
            "An unexpected error occurred",
            request.getRequestURI());

    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
