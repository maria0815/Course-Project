package photo.entity

import io.swagger.annotations.ApiModelProperty
import java.util.*

data class User(
    @ApiModelProperty(value = "Идентификатор автора")
    val id: UUID,
    @ApiModelProperty(value = "Имя автора фотографии")
    var name: String,
)