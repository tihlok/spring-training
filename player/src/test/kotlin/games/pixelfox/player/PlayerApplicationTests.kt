package games.pixelfox.player

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class PlayerApplicationTests {
    @Autowired
    val mockMVC: MockMvc? = null

    @Test
    fun `Get Server actuator status`() {
        this.mockMVC?.perform(get("/actuator/health"))
            ?.andExpect(status().isOk)
            ?.andExpect(content().json("{}"))
    }
}
