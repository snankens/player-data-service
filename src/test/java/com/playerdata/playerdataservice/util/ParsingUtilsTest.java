package com.playerdata.playerdataservice.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ParsingUtilsTest {

  @Test
  public void testParseInteger_NonEmptyString() {
    assertEquals(123, ParsingUtils.parseInteger("123"));
  }

  @Test
  public void testParseInteger_EmptyString() {
    assertEquals(0, ParsingUtils.parseInteger(""));
  }

  @Test
  public void testParseNullableInteger_NonEmptyString() {
    assertEquals(123, ParsingUtils.parseNullableInteger("123"));
  }

  @Test
  public void testParseNullableInteger_EmptyString() {
    assertNull(ParsingUtils.parseNullableInteger(""));
  }

  @Test
  public void testParseLocalDate_ValidDate_ddMMyyyy() {
    assertEquals(LocalDate.of(2021, 12, 25), ParsingUtils.parseLocalDate("25/12/2021"));
  }

  @Test
  public void testParseLocalDate_ValidDate_yyyyMMdd() {
    assertEquals(LocalDate.of(2021, 12, 25), ParsingUtils.parseLocalDate("2021-12-25"));
  }

  @Test
  public void testParseLocalDate_InvalidDateFormat() {
    assertNull(ParsingUtils.parseLocalDate("25-12-2021"));
  }

  @Test
  public void testParseLocalDate_EmptyString() {
    assertNull(ParsingUtils.parseLocalDate(""));
  }
}
