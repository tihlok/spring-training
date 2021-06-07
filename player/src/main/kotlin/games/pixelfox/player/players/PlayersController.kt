package games.pixelfox.player.players

import games.pixelfox.hellpers.exceptions.EntityCreationException
import games.pixelfox.hellpers.exceptions.EntityDeleteException
import games.pixelfox.hellpers.exceptions.EntityUpdateException
import games.pixelfox.hellpers.vo.PlayerVO
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

/**
 * {@link games.pixelfox.player.players.Player} REST Controller
 *
 * @author tihlok
 */
@Api("Players")
@RestController
@RequestMapping("/players")
class PlayersController(val service: PlayersService) {
    /**
     * GET one {@link games.pixelfox.player.players.Player} by ID
     * if NOT FOUND returns 404 status code
     *
     * @param id Player ID
     * @return {@link games.pixelfox.player.players.PlayerVO} or 404
     */
    @Throws(ResponseStatusException::class)
    @ApiOperation("Get ONE Player by its ID")
    @ApiResponses(
        ApiResponse(code = 200, response = PlayerVO::class, message = "Player Found"),
        ApiResponse(code = 404, message = "Player Not Found"),
    )
    @GetMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.OK)
    fun findByID(@PathVariable id: Long): PlayerVO = this.service.findByID(id)
        ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    /**
     * Create one {@link games.pixelfox.player.players.Player}
     * it should not have id set
     *
     * @param vo PlayerVO obj
     * @return the new {@link games.pixelfox.player.players.PlayerVO} or 400
     */
    @Throws(EntityCreationException::class)
    @ApiOperation("Create ONE Player")
    @ApiResponses(
        ApiResponse(code = 201, response = PlayerVO::class, message = "Player Created"),
        ApiResponse(code = 400, message = "Player fields missmatch"),
    )
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody vo: PlayerVO): PlayerVO = try {
        this.service.create(vo)
    } catch (e: EntityCreationException) {
        throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
    }

    /**
     * Update one {@link games.pixelfox.player.players.Player}
     * it should have id set
     *
     * @param vo PlayerVO obj
     * @return the updated {@link games.pixelfox.player.players.PlayerVO} or 400
     */
    @Throws(EntityUpdateException::class)
    @ApiOperation("Update ONE Player")
    @ApiResponses(
        ApiResponse(code = 200, response = PlayerVO::class, message = "Player Updated"),
        ApiResponse(code = 400, message = "Player fields missmatch"),
    )
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    fun update(@RequestBody vo: PlayerVO): PlayerVO = try {
        this.service.update(vo)
    } catch (e: EntityUpdateException) {
        throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
    }

    /**
     * Delete one {@link games.pixelfox.player.players.Player} by ID
     *
     * @param id Player ID
     * @return the deleted {@link games.pixelfox.player.players.PlayerVO} or 404
     */
    @Throws(EntityDeleteException::class)
    @ApiOperation("Delete ONE Player")
    @ApiResponses(
        ApiResponse(code = 200, response = PlayerVO::class, message = "Player Deleted"),
        ApiResponse(code = 404, message = "Player Not Found"),
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    fun deleteByID(@PathVariable id: Long): PlayerVO = try {
        this.service.deleteByID(id)
    } catch (e: EntityDeleteException) {
        throw ResponseStatusException(HttpStatus.NOT_FOUND, e.message)
    }
}
