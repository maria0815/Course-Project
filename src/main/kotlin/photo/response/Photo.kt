package photo.response

import io.swagger.annotations.ApiModelProperty
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

data class PhotoResponse(
    @ApiModelProperty(value = "Идентификатор фотографии")
    var id: UUID,
    @ApiModelProperty(value = "Автор снимка")
    var user_id: UUID,
    @ApiModelProperty(value = "Наименование фотографии")
    var file_name: String? = null,
    @ApiModelProperty(value = "Дата снимка")
    var photo_date: LocalDate? = null,
    @ApiModelProperty(value = "Время снимка")
    var photo_time: LocalTime? = null,
    @ApiModelProperty(value = "Девайс")
    var device: String? = null,
    @ApiModelProperty(value = "Модель фотоаппарата")
    var model: String?  = null,
    var latitude: Double?  = null,
    var longitude: Double?  = null,
)