package ru.tinkoff.photo.api

import io.swagger.annotations.*
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.tinkoff.photo.handler.ErrorResponse
import ru.tinkoff.photo.dto.CreateUserRequest
import ru.tinkoff.photo.dto.CreateUserResponse
import ru.tinkoff.photo.dto.UpdateUserRequest
import ru.tinkoff.photo.dto.UserDto
import java.util.*

@Api(description = "Операции для работы с пользователями")
interface UserApi {

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
    ): ResponseEntity<CreateUserResponse>

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
    ): ResponseEntity<Unit>

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
    ): ResponseEntity<Unit>

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
    fun getUsers(): ResponseEntity<Iterable<UserDto>>

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
    ): ResponseEntity<UserDto>
}