package ru.tinkoff.photo.api

import io.swagger.annotations.*
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.tinkoff.photo.handler.ErrorResponse
import ru.tinkoff.photo.dto.*
import java.util.*

@Api(description = "Операции для работы с альбомами")
interface AlbumApi {
    @ApiOperation("Создает новый альбом")
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Альбом создан"),
            ApiResponse(code = 400, message = "Переданный альбом имеет некорректный формат")
        ]
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createAlbum(
        @ApiParam("Создаваемый альбом")
        @RequestBody createAlbumRequest: CreateAlbumRequest,
    ): ResponseEntity<CreateAlbumResponse>

    @ApiOperation(
        value = "Возвращает список всех альбомов",
        response = AlbumDto::class,
        responseContainer = "List"
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "Список альбомов доступен")
        ]
    )
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAlbums(): ResponseEntity<Iterable<AlbumDto>>

    @ApiOperation("Изменяет альбом")
    @ApiResponses(
        value = [
            ApiResponse(code = 204, message = "Альбом изменен"),
            ApiResponse(
                code = 404,
                message = "Альбом с таким идентификатором не найден",
                response = ErrorResponse::class
            )
        ]
    )
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PutMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateAlbums(
        @ApiParam("Идентификатор альбома")
        @PathVariable id: UUID,
        @ApiParam("Имя альбома")
        @RequestBody updateAlbumRequest: UpdateAlbumRequest,
    ): ResponseEntity<Unit>

    @ApiOperation("Удаляет альбом")
    @ApiResponses(
        value = [
            ApiResponse(code = 204, message = "Альбом удален"),
            ApiResponse(
                code = 404,
                message = "Альбом с таким идентификатором не найден",
                response = ErrorResponse::class
            )
        ]
    )
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun deleteAlbum(
        @ApiParam("Идентификатор альбома")
        @PathVariable id: UUID,
    ): ResponseEntity<Unit>

    @ApiOperation("Добавляет фотографию в альбом")
    @ApiResponses(
        value = [
            ApiResponse(
                code = 201,
                message = "Фотография добавлена"
            ),
            ApiResponse(
                code = 409,
                message = "Фото уже есть в альбоме",
                response = ErrorResponse::class
            ),
            ApiResponse(
                code = 404,
                message = "Фотография или альбом не найден",
                response = ErrorResponse::class
            )
        ]
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/{albumId}/addPhoto", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun addPhoto(
        @ApiParam("Идентификатор альбома")
        @PathVariable albumId: UUID,
        @ApiParam("Идентификатор фотографии")
        @RequestBody request: AddPhotoRequest,
    ): ResponseEntity<Unit>

    @ApiOperation(
        value = "Возвращает список фотографий по идентификатору альбома",
        response = UUID::class,
        responseContainer = "List"
    )
    @ApiResponses(
        value = [
            ApiResponse(
                code = 200,
                message = "Список фотографий по идентификатору альбома найден"
            ),
            ApiResponse(
                code = 404,
                message = "Фотографии с таким идентификатором альбома не найдены",
                response = ErrorResponse::class
            )
        ]
    )
    @GetMapping(value = ["{id}/photos"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getPhotosByAlbumId(
        @ApiParam("Идентификатор альбома")
        @PathVariable id: UUID,
    ): ResponseEntity<Iterable<UUID>>
}