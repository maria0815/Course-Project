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
    var brand: String?,
    var model: String?,
    var latitude: Double?,
    var longitude: Double?
) {
    constructor(photo: Photo) : this(
        photo.id,
        photo.fileName,
        photo.photoDate,
        photo.photoTime,
        null,
        null,
        null,
        null
    )
}