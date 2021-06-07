package games.pixelfox.hellpers.abstracts

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.jetbrains.annotations.NotNull
import org.springframework.data.util.ProxyUtils
import java.io.Serializable
import java.sql.Timestamp
import javax.persistence.*

@MappedSuperclass
abstract class AbstractEntity<T : Serializable>(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column
    var id: T? = null
) {
    @CreationTimestamp
    @NotNull
    @Column(insertable = false, updatable = false, nullable = false)
    var createdAt: Timestamp? = null

    @UpdateTimestamp
    @NotNull
    @Column(insertable = false, updatable = true, nullable = false)
    var updatedAt: Timestamp? = null

    override fun equals(other: Any?): Boolean {
        other ?: return false
        if (this === other) return true
        if (javaClass != ProxyUtils.getUserClass(other)) return false
        other as AbstractEntity<*>
        return if (null === this.id) false else this.id === other.id
    }

    override fun hashCode(): Int = id?.hashCode() ?: 0

    override fun toString(): String = "[${this.javaClass.name}] id:$this.id"
}
