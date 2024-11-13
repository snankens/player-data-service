package com.playerdata.playerdataservice.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

/**
 * Data Transfer Object for Player entity.
 * Contains all fields required to transfer Player data without exposing the entity itself.
 *
 * @author Sara Nankensky
 */
@Data
public class PlayerDTO {
    private String playerId;
    private Integer birthYear;
    private Integer birthMonth;
    private Integer birthDay;
    private String birthCountry;
    private String birthState;
    private String birthCity;
    private Integer deathYear;
    private Integer deathMonth;
    private Integer deathDay;
    private String deathCountry;
    private String deathState;
    private String deathCity;
    private String firstName;
    private String lastName;
    private String givenName;
    private Integer weight;
    private Integer height;
    private String bats;
    private String throwingHand;
    private LocalDate debut;
    private LocalDate finalGame;
    private String retroId;
    private String bbrefId;
}
