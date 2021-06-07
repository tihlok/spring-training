package games.pixelfox.hellpers.abstracts

/**
 * Helper Object to transform Entity and VO
 *
 * @author tihlok
 */
abstract class AbstractTransformer<T : AbstractEntity<*>, VO> {
    /**
     * Transform T to VO
     *
     * @param t Entity
     * @return transformed VO
     */
    abstract fun from(t: T): VO

    /**
     * Transform VO to T
     *
     * @param vo VO
     * @return transformed T
     */
    abstract fun from(vo: VO): T
}
