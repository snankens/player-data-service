package com.playerdata.playerdataservice.repository;

import com.playerdata.playerdataservice.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing and managing {@link Player} entities.
 * Extends {@link JpaRepository} to provide standard CRUD operations.
 */
@Repository
public interface PlayerRepository extends JpaRepository<Player, String> {
}
