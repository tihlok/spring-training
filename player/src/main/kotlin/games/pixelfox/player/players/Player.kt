package games.pixelfox.player.players

import games.pixelfox.hellpers.abstracts.AbstractEntity
import org.jetbrains.annotations.NotNull
import javax.persistence.Column
import javax.persistence.Entity

/**
 * Player Entity
 *
 * @param name must not be {@literal null}.
 *
 * @author tihlok
 */
@Entity
class Player(
    id: Long? = null,
    @NotNull @Column var name: String,
) : AbstractEntity<Long>(id = id)
