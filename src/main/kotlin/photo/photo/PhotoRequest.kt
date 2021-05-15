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

data class PhotoMetadata(
    val photoId: UUID,
    var fileName: String,
    var photoDate: LocalDate?,
    var photoTime: LocalTime?,
    var manufacturer: String?,
    var model: String?,
    var latitude: Double?,
    var longitude: Double?
) {
    constructor(photo: Photo) : this(
        photo.id,
        photo.fileName,
        photo.photoDate,
        photo.photoTime,
        photo.model?.manufacturer?.name,
        photo.model?.name,
        photo.geoData?.place?.x,
        photo.geoData?.place?.y
    )
}