package photo.user

import io.swagger.annotations.*
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import photo.handler.ErrorResponse
import java.util.*

@Api(description = "Операции для работы с пользователями")
@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    private val logger = LoggerFactory.getLogger(UserController::class.java)

    @ApiOperation("Добавляет нового пользователя")
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Создан новый пользователь"),
            ApiResponse(code = 400, message = "Переданный пользователь имеет некорректный формат")
        ]
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createUser(
        @ApiParam("Создаваемый пользователь")
        @RequestBody createUserRequest: CreateUserRequest,
    ): ResponseEntity<CreateUserResponse> {
        val uuid = userService.createUser(createUserRequest.name)
        return ResponseEntity(CreateUserResponse(uuid), HttpStatus.CREATED)
    }

    @ApiOperation("Изменяет пользователя")
    @ApiResponses(
        value = [
            ApiResponse(code = 204, message = "Пользователь изменен"),
            ApiResponse(
                code = 404,
                message = "Пользователь с таким идентификатором не найден",
                response = ErrorResponse::class
            )
        ]
    )
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PutMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateUser(
        @ApiParam("Идентификатор пользователя")
        @PathVariable id: UUID,
        @ApiParam("Имя пользователя")
        @RequestBody updateUserRequest: UpdateUserRequest
    ): ResponseEntity<Unit> {
        userService.updateUser(id, updateUserRequest.name)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @ApiOperation("Удаляет пользователя")
    @ApiResponses(
        value = [
            ApiResponse(code = 204, message = "Пользователь удален"),
            ApiResponse(
                code = 404,
                message = "Пользователь с таким идентификатором не найден",
                response = ErrorResponse::class
            )
        ]
    )
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun deleteUser(
        @ApiParam("Идентификатор пользователя")
        @PathVariable id: UUID,
    ): ResponseEntity<Unit> {
        userService.deleteUser(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @ApiOperation(
        value = "Возвращает список всех пользователей",
        response = UserDto::class,
        responseContainer = "List"
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "Список с пользователями доступен")
        ]
    )
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getUsers(): ResponseEntity<Iterable<UserDto>> {
        val listOfUsers = userService.getAllUsers()
        return ResponseEntity.ok(listOfUsers)
    }

    @ApiOperation("Возвращает пользователя по идентификатору")
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "Пользователь найден", response = UserDto::class),
            ApiResponse(
                code = 404,
                message = "Пользователь с таким идентификатором не найден",
                response = ErrorResponse::class
            )
        ]
    )
    @GetMapping(value = ["{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getUserById(
        @ApiParam("Идентификатор пользователя")
        @PathVariable id: UUID,
    ): ResponseEntity<UserDto> {
        val user = userService.getUserById(id)
        return ResponseEntity(user, HttpStatus.OK)
    }
}