package games.pixelfox.hellpers

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class PlayerApplication

fun main(args: Array<String>) {
    runApplication<PlayerApplication>(*args)
}
