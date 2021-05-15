package photo.photo

import io.swagger.annotations.*
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDate
import java.util.*

@Api(description = "Операции для работы с фотографиями")
@RestController
@RequestMapping("/photos")
class PhotoController(private val photoService: PhotoService) {

    private val logger = LoggerFactory.getLogger(PhotoController::class.java)

    @ApiOperation("Загружает фотографию")
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Загружена новая фотография")
        ]
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun uploadPhoto(
        @ApiParam("Идентификатор пользователя")
        @RequestParam userId: UUID,
        @ApiParam("Файл с фотографией")
        @RequestPart(value = "file", required = true) file: MultipartFile
    ): ResponseEntity<UploadPhotoResponse> {
        val uuid = photoService.uploadPhoto(file, userId)
        return ResponseEntity(UploadPhotoResponse(uuid), HttpStatus.CREATED)
    }

    @ApiOperation("Возвращает метаданные фотографии по идентификатору")
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "Метаданные фотографии найдены", response = PhotoMetadata::class),
            ApiResponse(code = 404, message = "Фотография не найдена")
        ]
    )
    @GetMapping("/{id}")
    fun getPhotoById(
        @ApiParam("Идентификатор фотографии")
        @PathVariable id: UUID,
    ): ResponseEntity<PhotoMetadata> {
        val metadata = photoService.getPhotoMetadata(id)
        return ResponseEntity.ok(metadata)
    }

    @ApiOperation(
        value = "Возвращает список идентификаторов фотографий, сделанных в определенную дату",
        response = UUID::class,
        responseContainer = "List"
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "Фотографии найдены")
        ]
    )
    @GetMapping("/getByDate")
    fun getPhotosByDate(
        @ApiParam("Дата, когда была сделана фотография")
        @RequestParam date: LocalDate,
    ): ResponseEntity<Iterable<UUID>> {
        val listOfPhotos = photoService.getPhotosByDate(date)
        return ResponseEntity.ok(listOfPhotos)
    }

    @ApiOperation(
        value = "Возвращает список всех фотографий",
        response = UUID::class,
        responseContainer = "List"
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "Список фотографий доступен")
        ]
    )
    @GetMapping
    fun getAllPhotos(
    ): ResponseEntity<Iterable<UUID>> {
        val listOfPhotos = photoService.getAllPhotos()
        return ResponseEntity.ok(listOfPhotos)
    }

    @ApiOperation(
        value = "Возвращает список фотографий, снятых в определенном радиусе",
        response = UUID::class,
        responseContainer = "List"
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "Список фотографий найден")
        ]
    )
    @GetMapping("getByCoordinates")
    fun getPhotosByCoordinates(
        @ApiParam("Широта")
        @RequestParam latitude: Double,
        @ApiParam("Долгота")
        @RequestParam longitude: Double,
        @ApiParam("Радиус")
        @RequestParam radius: Int,
    ): ResponseEntity<Iterable<UUID>> {
        val listOfPhotos = photoService.getPhotosByCoordinates(latitude, longitude, radius)
        return ResponseEntity.ok(listOfPhotos)
    }
}