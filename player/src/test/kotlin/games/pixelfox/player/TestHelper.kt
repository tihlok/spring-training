package games.pixelfox.player

import com.google.gson.Gson
import org.flywaydb.core.Flyway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

@Configuration
class TestHelper(
    @Autowired val mockMVC: MockMvc,
    @Autowired val flyway: Flyway
) {
    fun clean() {
        flyway.clean()
        flyway.migrate()
    }

    fun get(url: String): ResultActions =
        mockMVC.perform(MockMvcRequestBuilders.get(url))

    fun post(url: String, obj: Any): ResultActions =
        mockMVC.perform(
            MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(Gson().toJson(obj))
        )

    fun put(url: String, obj: Any): ResultActions =
        mockMVC.perform(
            MockMvcRequestBuilders.put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(Gson().toJson(obj))
        )

    fun delete(url: String): ResultActions =
        mockMVC.perform(MockMvcRequestBuilders.delete(url))
}
