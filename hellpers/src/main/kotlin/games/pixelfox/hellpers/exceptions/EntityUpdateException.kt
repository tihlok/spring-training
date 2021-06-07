package games.pixelfox.hellpers.exceptions

import games.pixelfox.hellpers.vo.AbstractVO

class EntityUpdateException(val t: AbstractVO<*>? = null) : RuntimeException("Could not update entity")
