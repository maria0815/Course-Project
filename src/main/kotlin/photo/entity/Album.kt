package photo.entity

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.util.*

@ApiModel(description = "Альбом")
data class Album(
    @ApiModelProperty(value = "Идентификатор альбома")
    val id: UUID,
    @ApiModelProperty(value = "Название альбома")
    var album_name: String,
    @ApiModelProperty(value = "Дата создания альбома")
    val creation_date: String?,
    @ApiModelProperty(value = "Время создания альбома")
    val creation_time: String?,
    @ApiModelProperty(value = "Описание")
    val description: String?,
    @ApiModelProperty(value = "Создатель альбома")
    val user_id: UUID
)