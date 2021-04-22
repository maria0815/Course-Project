package photo.api

import com.drew.imaging.ImageMetadataReader
import com.drew.metadata.exif.ExifIFD0Directory
import com.drew.metadata.exif.GpsDirectory
import photo.entity.Photo
import photo.dao.photo.PhotoDao
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import photo.response.PhotoResponse
import java.io.BufferedInputStream
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

@RestController
@RequestMapping("/photos")
class PhotoController(private val photoDao: PhotoDao) {

    private val logger = LoggerFactory.getLogger(PhotoController::class.java)

    @ApiOperation("Загружает фотографию")
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Создан новый пользователь", response = UUID::class),
        ]
    )
    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun uploadPhoto(
        @RequestParam userId: UUID,
        @RequestPart(value = "file", required = true) file: MultipartFile
    ): PhotoResponse {
        val photo = PhotoResponse(UUID.randomUUID(), userId, file.originalFilename)
        val inputStream = BufferedInputStream(file.inputStream)
        val metadataReader = ImageMetadataReader.readMetadata(inputStream, false)

        val containsGpsDirectory = metadataReader.containsDirectory(GpsDirectory::class.java)
        if (containsGpsDirectory) {
            val gpsDirectory = metadataReader.getDirectory(GpsDirectory::class.java)
            val location = gpsDirectory.geoLocation
            photo.latitude = location.latitude
            photo.longitude = location.longitude
        }

        val containsExifDirectory = metadataReader.containsDirectory(ExifIFD0Directory::class.java)
        if (containsExifDirectory) {
            val exifDirectory = metadataReader.getDirectory(ExifIFD0Directory::class.java)
            val date = exifDirectory.getDate(ExifIFD0Directory.TAG_DATETIME)
            if (date != null) {
                val dateTime = Instant.ofEpochMilli(date.time).atZone(ZoneId.systemDefault()).toLocalDateTime()
                photo.photo_date = dateTime.toLocalDate()
                photo.photo_time = dateTime.toLocalTime()
            }
            val cameraName = exifDirectory.getString(ExifIFD0Directory.TAG_MAKE)
            val cameraModel = exifDirectory.getString(ExifIFD0Directory.TAG_MODEL)

            photo.model = cameraModel
            photo.device = cameraName
        }

        return photo
    }

    @ApiOperation("Возвращает фотографию по идентификатору")
    @GetMapping("/{id}")
    fun getPhotoById(
        @ApiParam("Идентификатор фотографии")
        @PathVariable id: UUID,
    ): Photo? {
        return photoDao.getPhotoById(id)
    }

    @ApiOperation("Возвращает список фотографий, сделанных в определенную дату")
    @GetMapping("getByDate")
    fun getPhotosByDate(
        @ApiParam("Дата, когда была сделана фотография")
        @RequestParam date: LocalDate,
    ): List<UUID> {
        return photoDao.getPhotosByDate(date)
    }

    @ApiOperation("Возвращает список всех фотографий")
    @GetMapping
    fun getAllPhotos(
    ): List<UUID> {
        return photoDao.getAllPhotos()
    }


    @ApiOperation("Возвращает список фотографий, снятых в определенном радиусе")
    @GetMapping("getByCoordinates")
    fun getPhotosByCoordinates(
        @ApiParam("Широта")
        @RequestParam latitude: Double,
        @ApiParam("Долгота")
        @RequestParam longitude: Double,
        @ApiParam("Радиус")
        @RequestParam radius: Int,
    ): List<UUID>? {
        return photoDao.getPhotosByCoordinates(latitude, longitude, radius)
    }
}