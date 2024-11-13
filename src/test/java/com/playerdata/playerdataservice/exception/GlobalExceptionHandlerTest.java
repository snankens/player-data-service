package com.playerdata.playerdataservice.exception;

import com.playerdata.playerdataservice.dto.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;

    @Mock
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void testHandlePlayerNotFoundException() {
        // Arrange
        PlayerNotFoundException exception = new PlayerNotFoundException("Player with ID 123 not found");
        String requestURI = "/api/player/123";
        when(request.getRequestURI()).thenReturn(requestURI);

        // Act
        ResponseEntity<ErrorResponseDto> responseEntity = globalExceptionHandler.handlePlayerNotFoundException(exception, request);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        ErrorResponseDto errorResponse = responseEntity.getBody();
        assert errorResponse != null;
        assertEquals(HttpStatus.NOT_FOUND.value(), errorResponse.getStatusCode());
        assertEquals("Player Not Found", errorResponse.getError());
        assertEquals("Player with ID 123 not found", errorResponse.getMessage());
        assertEquals(requestURI, errorResponse.getPath());
    }

    @Test
    void testHandleGeneralException() {
        // Arrange
        Exception exception = new Exception("Unexpected error occurred");
        String requestURI = "/api/player";
        when(request.getRequestURI()).thenReturn(requestURI);

        // Act
        ResponseEntity<ErrorResponseDto> responseEntity = globalExceptionHandler.handleGeneralException(exception, request);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        ErrorResponseDto errorResponse = responseEntity.getBody();
        assert errorResponse != null;
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorResponse.getStatusCode());
        assertEquals("Internal Server Error", errorResponse.getError());
        assertEquals("An unexpected error occurred", errorResponse.getMessage());
        assertEquals(requestURI, errorResponse.getPath());
    }
}
