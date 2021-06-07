package games.pixelfox.player.players

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * {@link games.pixelfox.player.players.Player} JPA Repository
 *
 * @author tihlok
 */
@Repository
interface PlayersRepository : JpaRepository<Player, Long>
