package photo.photo

import java.time.LocalDate
import java.time.LocalTime
import java.util.*

data class CreatePhotoRequest(
    var id: UUID,
    var userId: UUID,
    var fileName: String?,
    var photoDate: LocalDate?,
    var photoTime: LocalTime?,
    var brandId: String?,
    var modelId: String?,
    var latitude: Double?,
    var longitude: Double?
)