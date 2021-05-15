package photo.photo

import io.swagger.annotations.*
import org.slf4j.LoggerFactory
import org.springframework.core.io.Resource
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.*
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import photo.handler.ErrorResponse
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
            ApiResponse(code = 201, message = "Загружена новая фотография"),
            ApiResponse(code = 400, message = "Фотография не загружена", response = ErrorResponse::class)
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
        if (file.originalFilename == null) throw  EmptyFilenameException()
        if (file.contentType != "image/jpeg") throw  EmptyContentTypeException()
        val uuid = photoService.uploadPhoto(file, userId)
        return ResponseEntity(UploadPhotoResponse(uuid), HttpStatus.CREATED)
    }

    @ApiOperation("Возвращает метаданные фотографии по идентификатору")
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "Метаданные фотографии найдены", response = PhotoMetadata::class),
            ApiResponse(code = 404, message = "Фотография не найдена", response = ErrorResponse::class)
        ]
    )
    @GetMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getPhotoMetadataById(
        @ApiParam("Идентификатор фотографии")
        @PathVariable id: UUID,
    ): ResponseEntity<PhotoMetadata> {
        val metadata = photoService.getPhotoMetadata(id)
        return ResponseEntity.ok(metadata)
    }

    @ApiOperation("Возвращает файл фотографии по идентификатору")
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "Файл фотографии найден"),
            ApiResponse(code = 404, message = "Фотография не найдена", response = ErrorResponse::class)
        ]
    )
    @GetMapping("/{id}/file", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getPhotoFileById(
        @ApiParam("Идентификатор фотографии")
        @PathVariable id: UUID,
    ): ResponseEntity<Resource> {
        val resource = photoService.getPhotoFileById(id)

        val mediaType: MediaType = MediaTypeFactory.getMediaType(resource)
            .orElse(MediaType.APPLICATION_OCTET_STREAM)

        val headers = HttpHeaders().apply {
            contentType = mediaType
            contentDisposition = ContentDisposition
                .inline()
                .filename(resource.filename ?: "data")
                .build()
        }

        return ResponseEntity(resource, headers, HttpStatus.OK)
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
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
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