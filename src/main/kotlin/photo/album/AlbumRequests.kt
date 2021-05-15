package photo.album

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.util.*

@ApiModel("Запрос на создание альбома")
data class CreateAlbumRequest(
    @ApiModelProperty("Имя  альбома")
    val name: String,
    @ApiModelProperty("Описание  альбома")
    val description: String?,
    @ApiModelProperty("Идентификатор пользователя, владельца альбома")
    val userId: UUID
)

@ApiModel("Ответ на создание альбома")
data class CreateAlbumResponse(
    @ApiModelProperty("Идентификатор нового альбома")
    val id: UUID
)

@ApiModel("Запрос на изменение альбома")
data class UpdateAlbumRequest(
    @ApiModelProperty("Обновленное название альбома")
    val name: String,
    @ApiModelProperty("Обновленное описание альбома")
    val description: String?
)

@ApiModel("Запрос на добавление фотографии в альбом")
data class AddPhotoRequest(
    @ApiModelProperty("Идентификатор фотографии")
    val photoId: UUID
)