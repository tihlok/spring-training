package games.pixelfox.hellpers.exceptions

import games.pixelfox.hellpers.vo.AbstractVO

class EntityCreationException(val t: AbstractVO<*>? = null) : RuntimeException("New entity should not have auto generated fields set")
