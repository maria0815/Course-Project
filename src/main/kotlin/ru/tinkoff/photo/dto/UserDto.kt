package ru.tinkoff.photo.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import ru.tinkoff.photo.database.entity.User
import java.util.*

@ApiModel("Запрос на создание пользователя")
data class CreateUserRequest(
    @ApiModelProperty("Имя нового пользователя")
    val name: String
)

@ApiModel("Ответ на создание пользователя")
data class CreateUserResponse(
    @ApiModelProperty("Идентификатор нового пользователя")
    val id: UUID
)

@ApiModel("Запрос на изменение пользователя")
data class UpdateUserRequest(
    @ApiModelProperty("Обновленное имя пользователя")
    val name: String
)

@ApiModel("Пользователь")
data class UserDto(
    @ApiModelProperty("Идентификатор пользователя")
    val id: UUID,
    @ApiModelProperty("Имя пользователя")
    val name: String
) {
    constructor(user: User) : this(user.id, user.name)
}