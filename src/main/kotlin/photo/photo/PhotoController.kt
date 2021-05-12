package photo.photo

import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import photo.user.User
import java.time.LocalDate
import java.util.*

@RestController
@RequestMapping("/photos")
class PhotoController(private val photoService: PhotoService) {

    private val logger = LoggerFactory.getLogger(PhotoController::class.java)

    @ApiOperation("Загружает фотографию")
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Загружена новая фотография", response = UUID::class)
        ]
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun uploadPhoto(
        @RequestParam userId: UUID,
        @RequestPart(value = "file", required = true) file: MultipartFile
    ): ResponseEntity<UUID?> {
        val uuid = photoService.uploadPhoto(file, userId)
        return ResponseEntity(uuid, HttpStatus.CREATED)
    }

    @ApiOperation("Возвращает фотографию по идентификатору")
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "Фотография найдена", response = Photo::class),
            ApiResponse(code = 404, message = "Фотография с таким идентификатором не найдена")
        ]
    )
    @GetMapping("/{id}")
    fun getPhotoById(
        @ApiParam("Идентификатор фотографии")
        @PathVariable id: UUID,
    ): ResponseEntity<Photo?> {
        val photo = photoService.getPhotoById(id)
        return if (photo != null) {
            ResponseEntity(photo, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @ApiOperation("Возвращает список фотографий, сделанных в определенную дату")
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "Фотография найдена", response = Photo::class)
        ]
    )
    @GetMapping("/getByDate")
    fun getPhotosByDate(
        @ApiParam("Дата, когда была сделана фотография")
        @RequestParam date: LocalDate,
    ): ResponseEntity<List<UUID>> {
        val listOfPhotos = photoService.getPhotosByDate(date)
        return ResponseEntity.ok(listOfPhotos)
    }

    @ApiOperation("Возвращает список всех фотографий")
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "Список фотографий доступен")
        ]
    )
    @GetMapping
    fun getAllPhotos(
    ): ResponseEntity<List<UUID>> {
        val listOfPhotos = photoService.getAllPhotos()
        return ResponseEntity.ok(listOfPhotos)
    }

    @ApiOperation("Возвращает список фотографий, снятых в определенном радиусе")
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "Список фотографий найден", response = Photo::class)
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
    ): List<UUID>? {
        return photoService.getPhotosByCoordinates(latitude, longitude, radius)
    }
}