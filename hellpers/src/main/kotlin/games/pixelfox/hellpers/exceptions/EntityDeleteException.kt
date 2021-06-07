package games.pixelfox.hellpers.exceptions

import java.io.Serializable

class EntityDeleteException(var id: Serializable? = null) : RuntimeException("Could not delete entity")
