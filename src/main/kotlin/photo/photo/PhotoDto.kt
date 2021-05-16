package photo.photo

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

@ApiModel("Ответ на загрузку фотографии")
data class UploadPhotoResponse(
    @ApiModelProperty("Идентификатор загруженной фотографии")
    val id: UUID
)

@ApiModel("Метаданные фотографии")
data class PhotoMetadata(
    @ApiModelProperty(value = "Идентификатор фотографии")
    val photoId: UUID,
    @ApiModelProperty(value = "Имя файла")
    var fileName: String,
    @ApiModelProperty(value = "Дата загрузки фотографии")
    var photoDate: LocalDate?,
    @ApiModelProperty(value = "Время загрузки фотографии")
    var photoTime: LocalTime?,
    @ApiModelProperty(value = "Производитель фотоаппарата")
    var manufacturer: String?,
    @ApiModelProperty(value = "Модель фотоаппарата")
    var model: String?,
    @ApiModelProperty(value = "Широта")
    var latitude: Double?,
    @ApiModelProperty(value = "Долгота")
    var longitude: Double?
) {
    constructor(photo: Photo) : this(
        photo.id,
        photo.file.name,
        photo.photoDate,
        photo.photoTime,
        photo.model?.manufacturer?.name,
        photo.model?.name,
        photo.geoData?.place?.x,
        photo.geoData?.place?.y
    )
}