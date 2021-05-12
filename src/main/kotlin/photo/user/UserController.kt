package photo.user

import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

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
        @RequestBody request: CreateUserRequest,
    ): ResponseEntity<UUID?> {
        val uuid = userService.createUser(request.name)
        return ResponseEntity(uuid, HttpStatus.CREATED)
    }

    @ApiOperation("Изменяет пользователя")
    @ApiResponses(
        value = [
            ApiResponse(code = 204, message = "Пользователь изменен"),
            ApiResponse(code = 404, message = "Пользователь с таким идентификатором не найден")
        ]
    )
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    fun renameUser(
        @ApiParam("Идентификатор пользователя")
        @PathVariable id: UUID,
        @ApiParam("Имя пользователя")
        @RequestBody name: String,
    ): ResponseEntity<Unit> {
        return if (userService.getUserById(id) != null) {
            userService.renameUser(id, name)
            ResponseEntity(HttpStatus.NO_CONTENT)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @ApiOperation("Удаляет пользователя")
    @ApiResponses(
        value = [
            ApiResponse(code = 204, message = "Пользователь удален"),
            ApiResponse(code = 404, message = "Пользователь с таким идентификатором не найден")
        ]
    )
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    fun deleteUser(
        @ApiParam("Идентификатор пользователя")
        @PathVariable id: UUID,
    ): ResponseEntity<Unit> {
        userService.deleteUser(id)
        return if (userService.getUserById(id) != null) {
            userService.deleteUser(id)
            ResponseEntity(HttpStatus.NO_CONTENT)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @ApiOperation("Возвращает список всех пользователей")
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "Список с пользователями доступен")
        ]
    )
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getUsers(): ResponseEntity<Iterable<User>> {
        val listOfUsers = userService.getAllUsers()
        return ResponseEntity.ok(listOfUsers)
    }

    @ApiOperation("Возвращает пользователя по идентификатору")
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "Пользователь найден", response = User::class),
            ApiResponse(code = 404, message = "Пользователь с таким идентификатором не найден")
        ]
    )
    @GetMapping(value = ["{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getUserById(
        @ApiParam("Идентификатор пользователя")
        @PathVariable id: UUID,
    ): ResponseEntity<User?> {
        val user = userService.getUserById(id)
        return if (user != null) {
            ResponseEntity(user, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}