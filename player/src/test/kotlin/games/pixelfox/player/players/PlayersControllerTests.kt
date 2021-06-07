package games.pixelfox.player.players

import games.pixelfox.hellpers.vo.PlayerVO
import games.pixelfox.player.TestHelper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
class PlayersControllerTests(
    @Autowired val playersController: PlayersController,
    @Autowired val testHelper: TestHelper
) {
    @AfterEach
    fun afterEach() {
        testHelper.clean()
    }

    @Test
    fun contextLoads() {
        assertThat(playersController).isNotNull
    }

    @Test
    fun `Find existing Player 1`() {
        val vo = playersController.findByID(1)
        assertThat(vo).isNotNull
        assertThat(vo.id).isEqualTo(1)
        assertThat(vo.name).isEqualTo("tihlok")
        assertThat(vo.createdAt).isGreaterThan(0)
        assertThat(vo.updatedAt).isGreaterThan(0)
        assertThat(vo.createdAt).isEqualTo(vo.updatedAt)
        assertThat(vo.updatedAt).isGreaterThanOrEqualTo(vo.createdAt)
    }

    @Test
    fun `Find non existing Player`() {
        assertThrows(ResponseStatusException::class.java) {
            playersController.findByID(Long.MAX_VALUE)
        }
    }

    @Test
    fun `Create new Player`() {
        val vo = playersController.create(PlayerVO(name = "new controller player"))

        assertThat(vo).isNotNull
        assertThat(vo.id).isGreaterThan(0)
        assertThat(vo.name).isEqualTo("new controller player")
        assertThat(vo.createdAt).isGreaterThan(0)
        assertThat(vo.updatedAt).isGreaterThan(0)
        assertThat(vo.updatedAt).isGreaterThanOrEqualTo(vo.createdAt)
    }

    @Test
    fun `Not create new Player`() {
        val exception = assertThrows(ResponseStatusException::class.java) {
            playersController.create(PlayerVO(id = Long.MAX_VALUE, name = "new controller player"))
        }
        assertThat(exception.status).isEqualTo(HttpStatus.BAD_REQUEST)
    }

    @Test
    fun `Update Player`() {
        val vo = playersController.create(PlayerVO(name = "new controller player"))
        assertThat(vo.createdAt).isGreaterThan(0)
        assertThat(vo.updatedAt).isGreaterThan(0)
        assertThat(vo.updatedAt).isGreaterThanOrEqualTo(vo.createdAt)

        vo.name = "updated controller player"
        val updated = playersController.update(vo)
        assertThat(updated).isNotNull
        assertThat(updated.id).isEqualTo(vo.id)
        assertThat(updated.name).isEqualTo("updated controller player")
        assertThat(updated.createdAt).isGreaterThan(0)
        assertThat(updated.updatedAt).isGreaterThan(0)
        assertThat(updated.updatedAt).isGreaterThanOrEqualTo(updated.createdAt)
    }

    @Test
    fun `Not update Player`() {
        val exception = assertThrows(ResponseStatusException::class.java) {
            playersController.update(PlayerVO(name = "updated player service"))
        }
        assertThat(exception.status).isEqualTo(HttpStatus.BAD_REQUEST)
    }

    @Test
    fun `Delete Player`() {
        val vo = playersController.create(PlayerVO(name = "new service player"))
        playersController.deleteByID(vo.id!!)

        val exception = assertThrows(ResponseStatusException::class.java) {
            playersController.findByID(vo.id!!)
        }
        assertThat(exception.status).isEqualTo(HttpStatus.NOT_FOUND)
    }

    @Test
    fun `Not delete Player`() {
        val exception = assertThrows(ResponseStatusException::class.java) {
            playersController.deleteByID(Long.MAX_VALUE)
        }
        assertThat(exception.status).isEqualTo(HttpStatus.NOT_FOUND)
    }
}
