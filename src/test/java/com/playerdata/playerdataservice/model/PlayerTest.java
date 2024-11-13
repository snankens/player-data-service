package com.playerdata.playerdataservice.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest {

  private static Validator validator;

  @BeforeAll
  public static void setUp() {
    try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
      validator = factory.getValidator();
    }
  }

  @Test
  public void testValidPlayer() {
    Player player =
        new Player(
            "1",
            1990,
            5,
            15,
            "Country",
            "State",
            "City",
            2021,
            10,
            5,
            "Country",
            "State",
            "City",
            "John",
            "Doe",
            null,
            80,
            180,
            "R",
            "L",
            LocalDate.of(2005, 6, 15),
            LocalDate.of(2019, 8, 10),
            "RET123",
            "BB123");

    Set<ConstraintViolation<Player>> violations = validator.validate(player);
    assertTrue(violations.isEmpty());
  }

  @Test
  public void testInvalidBirthMonth() {
    Player player =
        new Player(
            "1",
            1990,
            13,
            15,
            "Country",
            "State",
            "City",
            2021,
            10,
            5,
            "Country",
            "State",
            "City",
            "John",
            "Doe",
            null,
            80,
            180,
            "R",
            "L",
            LocalDate.of(2005, 6, 15),
            LocalDate.of(2019, 8, 10),
            "RET123",
            "BB123");

    Set<ConstraintViolation<Player>> violations = validator.validate(player);
    assertFalse(violations.isEmpty());
  }

  @Test
  public void testDeathDateAfterBirthDate() {
    Player player =
        new Player(
            "1",
            1990,
            5,
            15,
            "Country",
            "State",
            "City",
            1989,
            4,
            10,
            "Country",
            "State",
            "City",
            "John",
            "Doe",
            null,
            80,
            180,
            "R",
            "L",
            LocalDate.of(2005, 6, 15),
            LocalDate.of(2019, 8, 10),
            "RET123",
            "BB123");

    Set<ConstraintViolation<Player>> violations = validator.validate(player);
    assertFalse(violations.isEmpty());
  }
}
