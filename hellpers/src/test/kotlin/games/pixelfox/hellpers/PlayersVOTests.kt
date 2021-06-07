package games.pixelfox.hellpers

import games.pixelfox.hellpers.vo.PlayerVO
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayersVOTests {
    @Test
    fun `Create a empty PlayerVO`() {
        val vo = PlayerVO()
        assertThat(vo.id).isNull()
        assertThat(vo.name).isNull()
        assertThat(vo.createdAt).isNull()
        assertThat(vo.updatedAt).isNull()
    }

    @Test
    fun `Create a complete PlayerVO`() {
        val vo = PlayerVO(id = 1, name = "tihlok")
        assertThat(vo.id).isEqualTo(1)
        assertThat(vo.name).isEqualTo("tihlok")
    }
}
