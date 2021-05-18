package ru.tinkoff.photo.api

import io.swagger.annotations.*
import org.springframework.core.io.Resource
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import ru.tinkoff.photo.dto.PhotoMetadata
import ru.tinkoff.photo.dto.UploadPhotoResponse
import ru.tinkoff.photo.handler.ErrorResponse
import java.time.LocalDate
import java.util.*

@Api(description = "Операции для работы с фотографиями")
interface PhotoApi {
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
    ): ResponseEntity<UploadPhotoResponse>

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
    ): ResponseEntity<PhotoMetadata>

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
    ): ResponseEntity<Resource>

    @ApiOperation(
        value = "Возвращает список идентификаторов фотографий, " +
                "сделанных в определенную дату, отсортированные по времени съемки",
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
    ): ResponseEntity<Iterable<UUID>>

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
    ): ResponseEntity<Iterable<UUID>>

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
    ): ResponseEntity<Iterable<UUID>>
}