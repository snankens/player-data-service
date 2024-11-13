package com.playerdata.playerdataservice.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ParsingUtils {

    private static final DateTimeFormatter FORMATTER1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter FORMATTER2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Parses a string to an Integer. Returns 0 if the input string is empty.
     *
     * @param value the string to parse
     * @return the parsed integer, or 0 if the input is empty
     */
    public static Integer parseInteger(String value) {
        return value.isEmpty() ? 0 : Integer.parseInt(value);
    }

    /**
     * Parses a string to an Integer. Returns null if the input string is empty.
     *
     * @param value the string to parse
     * @return the parsed integer, or null if the input is empty
     */
    public static Integer parseNullableInteger(String value) {
        return value.isEmpty() ? null : Integer.parseInt(value);
    }

    /**
     * Parses a string to a LocalDate. Attempts to parse the date in two formats: "dd/MM/yyyy" and
     * "yyyy-MM-dd". If parsing fails, returns null.
     *
     * @param value the string to parse
     * @return the parsed LocalDate, or null if parsing fails
     */
    public static LocalDate parseLocalDate(String value) {
        try {
            return LocalDate.parse(value, FORMATTER1);
        } catch (DateTimeParseException e1) {
            try {
                return LocalDate.parse(value, FORMATTER2);
            } catch (DateTimeParseException e2) {
                return null;
            }
        }
    }
}
