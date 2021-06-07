package games.pixelfox.player.players

import games.pixelfox.player.TestHelper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
class PlayersRepositoryTests(
    @Autowired val playersRepository: PlayersRepository,
    @Autowired val testHelper: TestHelper
) {
    @AfterEach
    fun afterEach() {
        testHelper.clean()
    }

    @Test
    fun contextLoads() {
        assertThat(playersRepository).isNotNull
    }

    @Test
    fun `Find existing Player 1`() {
        val player = playersRepository.findById(1).orElse(null)
        assertThat(player).isNotNull
        assertThat(player?.id).isEqualTo(1)
        assertThat(player?.name).isEqualTo("tihlok")
    }

    @Test
    fun `Find non existing Player`() {
        val player = playersRepository.findById(Long.MAX_VALUE).orElse(null)
        assertThat(player).isNull()
    }

    @Test
    fun `Create New Player`() {
        val saved = playersRepository.save(Player(name = "new player"))
        assertThat(saved).isNotNull
        assertThat(saved.id).isGreaterThan(0)
        assertThat(saved.name).isEqualTo("new player")
        assertThat(saved.createdAt?.time).isGreaterThan(0)
        assertThat(saved.updatedAt?.time).isGreaterThan(0)
    }

    @Test
    fun `Update New Player`() {
        val saved = playersRepository.save(Player(name = "other new player"))
        assertThat(saved).isNotNull
        assertThat(saved.name).isEqualTo("other new player")

        saved.name = "updated the name"
        var updated = playersRepository.save(saved)
        assertThat(updated.id).isEqualTo(saved.id)
        assertThat(updated.name).isEqualTo("updated the name")
        assertThat(updated.createdAt).isEqualTo(saved.createdAt)

        updated.name = "updated the name 2"
        updated = playersRepository.save(updated)
        assertThat(updated.name).isEqualTo("updated the name 2")

        updated.name = "updated the name 3"
        updated = playersRepository.save(updated)
        assertThat(updated.name).isEqualTo("updated the name 3")
    }

    @Test
    fun `Delete New Player`() {
        val saved = playersRepository.save(Player(name = "new player"))
        assertThat(saved).isNotNull

        playersRepository.delete(saved)
        val player = playersRepository.findById(saved.id!!).orElse(null)
        assertThat(player).isNull()
    }
}
