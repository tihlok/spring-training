package games.pixelfox.player.players

import games.pixelfox.hellpers.vo.PlayerVO
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayersTransformerTests {
    @Test
    fun `Transform Player to VO`() {
        val player = Player(name = "tihlok")
        val vo = PlayerTransformer.from(player)

        assertThat(vo.id).isNull()
        assertThat(vo.name).isEqualTo(player.name)
        assertThat(vo.createdAt).isNull()
        assertThat(vo.updatedAt).isNull()
    }

    @Test
    fun `Transform VO to Player`() {
        val vo = PlayerVO(id = 1, name = "tihlok", createdAt = System.currentTimeMillis(), updatedAt = System.currentTimeMillis())
        val player = PlayerTransformer.from(vo)

        assertThat(player.id).isEqualTo(vo.id)
        assertThat(player.name).isEqualTo(vo.name)
        assertThat(player.createdAt?.time).isEqualTo(vo.createdAt)
        assertThat(player.updatedAt?.time).isEqualTo(vo.updatedAt)
    }
}
