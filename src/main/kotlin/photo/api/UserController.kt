package photo.api

import photo.entity.User
import photo.dao.user.UserDao
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import photo.request.CreateUserRequest
import java.util.*


@RestController
@RequestMapping("/users")
class UserController(private val userDao: UserDao) {

    private val logger = LoggerFactory.getLogger(UserController::class.java)

    @ApiOperation("Добавляет нового пользователя")
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Создан новый пользователь", response = UUID::class),
        ]
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createUser(
        @ApiParam("Создаваемый пользователь")
        @RequestBody user: CreateUserRequest,
    ): ResponseEntity<UUID> {
        val uuid = userDao.createUser(user.name)
        return ResponseEntity(uuid, HttpStatus.CREATED)
    }

    @ApiOperation("Изменяет пользователя")
    @ApiResponses(
        value = [
            ApiResponse(code = 204, message = "Пользователь изменен"),
            ApiResponse(code = 404, message = "Пользователь с таким идентификатором не найден")
        ]
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    @PutMapping("/{id}")
    fun renameAlbums(
        @ApiParam("Идентификатор пользователя")
        @PathVariable id: UUID,
        @ApiParam("Имя пользователя")
        @RequestBody name: String,
    ) {
        userDao.renameUser(id, name)
    }

    @ApiOperation("Удаляет пользователя")
    @ApiResponses(
        value = [
            ApiResponse(code = 204, message = "Пользователь удален"),
            ApiResponse(code = 404, message = "Пользователь с таким идентификатором не найден"),
        ]
    )
    @DeleteMapping("/{id}")
    fun deleteAlbum(
        @ApiParam("Идентификатор пользователя")
        @PathVariable id: UUID,
    ) {
        userDao.deleteUser(id)
    }

    @ApiOperation("Возвращает список всех пользователей")
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "Список с пользователями доступен"),
        ]
    )
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAlbums(): ResponseEntity<List<User>> {
        val listOfUsers = userDao.getAllUsers()
        return ResponseEntity.ok(listOfUsers)
    }

    @ApiOperation("Возвращает пользователя по идентификатору")
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "Пользователь найден", response = User::class),
            ApiResponse(code = 404, message = "Пользователь с таким идентификатором не найден"),
        ]
    )
    @GetMapping(value = ["{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getUserById(
        @ApiParam("Идентификатор пользователя")
        @PathVariable id: UUID,
    ): ResponseEntity<User?> {
        val user = userDao.getUserById(id)
        return ResponseEntity(user, HttpStatus.NOT_FOUND)
    }
}