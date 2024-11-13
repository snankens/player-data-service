/**
 * Mapper interface for converting between Player and PlayerDTO.
 */
package com.playerdata.playerdataservice.mapper;

import com.playerdata.playerdataservice.dto.PlayerDTO;
import com.playerdata.playerdataservice.model.Player;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlayerMapper {

    /**
     * Singleton instance of the PlayerMapper.
     */
    PlayerMapper INSTANCE = Mappers.getMapper(PlayerMapper.class);

    /**
     * Converts a Player entity to a PlayerDTO.
     *
     * @param player the Player entity to convert
     * @return the converted PlayerDTO
     */
    PlayerDTO toDTO(Player player);

    // Uncomment if needed
    // Player toEntity(PlayerDTO dto);
}
