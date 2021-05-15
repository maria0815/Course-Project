package photo.user

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
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