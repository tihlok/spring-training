package games.pixelfox.hellpers.vo

import java.io.Serializable

/**
 * Abstract VO
 *
 * @param id
 *
 * @author tihlok
 */
abstract class AbstractVO<T : Serializable>(
    var id: T? = null,
)
