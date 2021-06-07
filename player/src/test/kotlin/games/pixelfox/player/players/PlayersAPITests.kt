package games.pixelfox.player.players

import com.google.gson.Gson
import games.pixelfox.hellpers.vo.PlayerVO
import games.pixelfox.player.TestHelper
import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.Matchers.containsString
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest(webEnvironment = DEFINED_PORT)
@AutoConfigureMockMvc
class PlayersAPITests(
    @Autowired val testHelper: TestHelper
) {
    @AfterEach
    fun afterEach() {
        testHelper.clean()
    }

    @Test
    fun `GET existing Player 1, expect 200`() {
        val content = testHelper.get("/players/1")
            .andExpect(status().isOk)
            .andExpect(content().string(containsString("{\"id\":1,\"name\":\"tihlok\"")))
            .andReturn().response.contentAsString
        val vo = Gson().fromJson(content, PlayerVO::class.java)
        assertThat(vo).isNotNull
        assertThat(vo.id).isEqualTo(1)
        assertThat(vo.name).isEqualTo("tihlok")
        assertThat(vo.createdAt).isNotNull
        assertThat(vo.updatedAt).isNotNull
    }

    @Test
    fun `GET existing Player 2, expect 200`() {
        val content = testHelper.get("/players/2")
            .andExpect(status().isOk)
            .andExpect(content().string(containsString("{\"id\":2,\"name\":\"jaq\"")))
            .andReturn().response.contentAsString
        val vo = Gson().fromJson(content, PlayerVO::class.java)
        assertThat(vo).isNotNull
        assertThat(vo.id).isEqualTo(2)
        assertThat(vo.name).isEqualTo("jaq")
        assertThat(vo.createdAt).isNotNull
        assertThat(vo.updatedAt).isNotNull
    }

    @Test
    fun `GET existing Player 3, expect 200`() {
        val content = testHelper.get("/players/3")
            .andExpect(status().isOk)
            .andExpect(content().string(containsString("{\"id\":3,\"name\":\"nhamnham\"")))
            .andReturn().response.contentAsString
        val vo = Gson().fromJson(content, PlayerVO::class.java)
        assertThat(vo).isNotNull
        assertThat(vo.id).isEqualTo(3)
        assertThat(vo.name).isEqualTo("nhamnham")
        assertThat(vo.createdAt).isNotNull
        assertThat(vo.updatedAt).isNotNull
    }

    @Test
    fun `GET non existing Player, expect 404`() {
        testHelper.get("/players/${Long.MAX_VALUE}")
            .andExpect(status().isNotFound)
    }

    @Test
    fun `POST new Player`() {
        var content = testHelper.post("/players/create", PlayerVO(name = "new API player"))
            .andExpect(status().isCreated)
            .andExpect(content().string(containsString("\"name\":\"new API player\"")))
            .andReturn().response.contentAsString

        var vo = Gson().fromJson(content, PlayerVO::class.java)

        content = testHelper.get("/players/${vo.id}")
            .andExpect(status().isOk)
            .andReturn().response.contentAsString

        vo = Gson().fromJson(content, PlayerVO::class.java)

        assertThat(vo).isNotNull
        assertThat(vo.id).isGreaterThan(3)
        assertThat(vo.name).isEqualTo("new API player")
        assertThat(vo.createdAt).isNotNull
        assertThat(vo.updatedAt).isNotNull
    }

    @Test
    fun `error POST new Player with id`() {
        testHelper.post("/players/create", PlayerVO(id = 1, name = "new API player"))
            .andExpect(status().isBadRequest)
    }

    @Test
    fun `error PUT Player without id`() {
        testHelper.put("/players/update", PlayerVO(name = "new API player"))
            .andExpect(status().isBadRequest)
    }

    @Test
    fun `error DELETE Player non existent`() {
        testHelper.delete("/players/${Long.MAX_VALUE}")
            .andExpect(status().isNotFound)
    }
}
