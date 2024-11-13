package com.playerdata.playerdataservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Main application class for the Player Data Service.
 * This service handles player-related data and uses JPA for data persistence.
 */
@SpringBootApplication
@EnableJpaRepositories
public class PlayerDataServiceApplication {

  /**
   * Main method to launch the Spring Boot application.
   *
   * @param args command-line arguments
   */
  public static void main(String[] args) {
    SpringApplication.run(PlayerDataServiceApplication.class, args);
  }
}

