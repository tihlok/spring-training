package games.pixelfox.hellpers.abstracts

import games.pixelfox.hellpers.exceptions.EntityCreationException
import games.pixelfox.hellpers.exceptions.EntityDeleteException
import games.pixelfox.hellpers.exceptions.EntityUpdateException
import games.pixelfox.hellpers.vo.AbstractVO
import org.springframework.data.jpa.repository.JpaRepository
import java.io.Serializable

/**
 * Abstract Service
 *
 * @author tihlok
 */
abstract class AbstractService<
        ID : Serializable,
        T : AbstractEntity<ID>,
        VO : AbstractVO<ID>>(
    val transformer: AbstractTransformer<T, VO>,
    val repository: JpaRepository<T, ID>
) {
    /**
     * GET one {@link games.pixelfox.hellpers.players.Player} by ID
     * if NOT FOUND returns {@literal null}
     *
     * @param id entity id
     * @return VO or {@literal null}
     */
    fun findByID(id: ID): VO? = this.repository.findById(id)
        .map { transformer.from(it) }
        .orElse(null)

    /**
     * CREATE one entity
     * it should not have id, created_at and updated_at fields set
     *
     * @param vo
     * @return VO
     */
    @Throws(EntityCreationException::class)
    fun create(vo: VO): VO = if (vo.id === null) {
        val t = transformer.from(vo)
        transformer.from(this.repository.save(t))
    } else {
        throw EntityCreationException(vo)
    }

    /**
     * UPDATE one entity
     * it should have id
     *
     * @param vo
     * @return VO
     */
    @Throws(EntityUpdateException::class)
    fun update(vo: VO): VO = if (vo.id !== null) {
        val t = transformer.from(vo)
        this.repository.saveAndFlush(t)
        transformer.from(this.repository.getOne(t.id!!))
    } else {
        throw EntityUpdateException(vo)
    }

    /**
     * DELETE one entity by ID
     *
     * @param id entity id
     * @return Boolean
     */
    @Throws(EntityDeleteException::class)
    fun deleteByID(id: ID): VO = with(this.repository.findById(id)) {
        if (this.isPresent) {
            repository.deleteById(id)
            if (repository.findById(id).isPresent)
                throw EntityDeleteException(id)
            transformer.from(this.get())
        } else {
            throw EntityDeleteException(id)
        }
    }
}
