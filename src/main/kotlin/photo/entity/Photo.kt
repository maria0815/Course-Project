package photo.entity

import io.swagger.annotations.ApiModelProperty
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

data class Photo(
    @ApiModelProperty(value = "Идентификатор фотографии")
    val id: UUID,
    @ApiModelProperty(value = "Автор снимка")
    var user_id: UUID,
    @ApiModelProperty(value = "Путь к фотографии")
    var path_to_file: String,
    @ApiModelProperty(value = "Наименование фотографии")
    var file_name: String?,
    @ApiModelProperty(value = "Дата загрузки фотографии")
    var upload_date: LocalDate,
    @ApiModelProperty(value = "Время загрузки фотографии")
    var upload_time: LocalTime,
    @ApiModelProperty(value = "Дата снимка")
    var photo_date: LocalDate?,
    @ApiModelProperty(value = "Время снимка")
    var photo_time: LocalTime?,
    @ApiModelProperty(value = "Девайс")
    var device_id: UUID?,
    @ApiModelProperty(value = "Модель фотоаппарата")
    var model_id: UUID?
)