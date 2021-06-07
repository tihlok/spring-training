package games.pixelfox.player.players

import games.pixelfox.hellpers.abstracts.AbstractTransformer
import games.pixelfox.hellpers.vo.PlayerVO
import java.sql.Timestamp

/**
 * Helper Object to transform {@link games.pixelfox.player.players.Player} and {@link games.pixelfox.player.players.PlayerVO}
 *
 * @author tihlok
 */
object PlayerTransformer : AbstractTransformer<Player, PlayerVO>() {
    /**
     * Transform {@link games.pixelfox.player.players.Player} to {@link games.pixelfox.player.players.PlayerVO}
     *
     * @param t Player Entity
     * @return transformed {@link games.pixelfox.player.players.PlayerVO}
     */
    override fun from(t: Player): PlayerVO = PlayerVO(
        id = t.id,
        name = t.name,
        createdAt = t.createdAt?.time,
        updatedAt = t.updatedAt?.time
    )

    /**
     * Transform {@link games.pixelfox.player.players.PlayerVO} to {@link games.pixelfox.player.players.Player}
     *
     * @param vo Player VO
     * @return transformed {@link games.pixelfox.player.players.Player}
     */
    override fun from(vo: PlayerVO): Player = with(
        Player(
            name = vo.name!!,
        )
    ) {
        if (vo.id != null) this.id = vo.id
        if (vo.createdAt != null) this.createdAt = Timestamp(vo.createdAt!!)
        if (vo.updatedAt != null) this.updatedAt = Timestamp(vo.updatedAt!!)
        return this
    }
}
