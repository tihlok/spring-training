package games.pixelfox.hellpers.vo

/**
 * Player VO
 *
 * @param id
 * @param name
 * @param createdAt
 * @param updatedAt
 *
 * @author tihlok
 */
class PlayerVO(
    id: Long? = null,
    var name: String? = null,
    var createdAt: Long? = null,
    var updatedAt: Long? = null
) : AbstractVO<Long>(id)
