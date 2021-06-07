package games.pixelfox.player.players

import games.pixelfox.hellpers.exceptions.EntityCreationException
import games.pixelfox.hellpers.exceptions.EntityDeleteException
import games.pixelfox.hellpers.exceptions.EntityUpdateException
import games.pixelfox.hellpers.vo.PlayerVO
import games.pixelfox.player.TestHelper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
class PlayersServiceTests(
    @Autowired val playersService: PlayersService,
    @Autowired val testHelper: TestHelper
) {
    @AfterEach
    fun afterEach() {
        testHelper.clean()
    }

    @Test
    fun contextLoads() {
        assertThat(playersService).isNotNull
    }

    @Test
    fun `Find existing Player 1`() {
        val vo = playersService.findByID(1)
        assertThat(vo).isNotNull
        assertThat(vo?.id).isEqualTo(1)
        assertThat(vo?.name).isEqualTo("tihlok")
        assertThat(vo?.createdAt).isGreaterThan(0)
        assertThat(vo?.updatedAt).isGreaterThan(0)
        assertThat(vo?.createdAt).isEqualTo(vo?.updatedAt)
    }

    @Test
    fun `Find non existing Player`() {
        val vo = playersService.findByID(Long.MAX_VALUE)
        assertThat(vo).isNull()
    }

    @Test
    fun `Create new Player`() {
        val vo = playersService.create(PlayerVO(name = "new service player"))

        assertThat(vo).isNotNull
        assertThat(vo.id).isGreaterThan(0)
        assertThat(vo.name).isEqualTo("new service player")
        assertThat(vo.createdAt).isGreaterThan(0)
        assertThat(vo.updatedAt).isGreaterThan(0)
    }

    @Test
    fun `Not create new Player`() {
        assertThrows(EntityCreationException::class.java) {
            playersService.create(PlayerVO(id = Long.MAX_VALUE, name = "new service player"))
        }
    }

    @Test
    fun `Update Player`() {
        val vo = playersService.create(PlayerVO(name = "new service player"))
        assertThat(vo.createdAt).isGreaterThan(0)
        assertThat(vo.updatedAt).isGreaterThan(0)
        assertThat(vo.updatedAt).isGreaterThanOrEqualTo(vo.createdAt)

        vo.name = "updated player service"
        val updated = playersService.update(vo)
        assertThat(updated).isNotNull
        assertThat(updated.id).isEqualTo(vo.id)
        assertThat(updated.name).isEqualTo("updated player service")
        assertThat(updated.createdAt).isGreaterThan(0)
        assertThat(updated.updatedAt).isGreaterThan(0)
        assertThat(updated.updatedAt).isGreaterThanOrEqualTo(updated.createdAt)
    }

    @Test
    fun `Not update Player`() {
        assertThrows(EntityUpdateException::class.java) {
            playersService.update(PlayerVO(name = "updated player service"))
        }
    }

    @Test
    fun `Delete Player`() {
        val vo = playersService.create(PlayerVO(name = "new service player"))
        playersService.deleteByID(vo.id!!)

        val find = playersService.findByID(vo.id!!)
        assertThat(find).isNull()
    }

    @Test
    fun `Not delete Player`() {
        assertThrows(EntityDeleteException::class.java) {
            playersService.deleteByID(Long.MAX_VALUE)
        }
    }
}
