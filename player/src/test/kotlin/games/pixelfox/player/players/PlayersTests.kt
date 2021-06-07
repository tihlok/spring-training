package games.pixelfox.player.players

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayersTests {
    @Test
    fun `Create a minimal Player`() {
        val player = Player(name = "tihlok")
        assertThat(player.id).isNull()
        assertThat(player.name).isEqualTo("tihlok")
        assertThat(player.createdAt).isNull()
        assertThat(player.updatedAt).isNull()
    }
}
