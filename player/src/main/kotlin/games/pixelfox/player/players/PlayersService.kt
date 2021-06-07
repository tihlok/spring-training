package games.pixelfox.player.players

import games.pixelfox.hellpers.abstracts.AbstractService
import games.pixelfox.hellpers.vo.PlayerVO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service

/**
 * {@link games.pixelfox.player.players.Player} Service
 *
 * @author tihlok
 */
@Service
class PlayersService(repository: JpaRepository<Player, Long>) : AbstractService<Long, Player, PlayerVO>(PlayerTransformer, repository)
