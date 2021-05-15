package photo.album

import io.swagger.annotations.*
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import photo.album.withPhoto.AlbumWithPhoto
import photo.album.withPhoto.AlbumWithPhotoService
import java.util.*

@Api(description = "Операции для работы с альбомами")
@RestController
@RequestMapping("/albums")
class AlbumController(
    private val albumService: AlbumService,
    private val albumWithPhotoService: AlbumWithPhotoService
) {
    private val logger = LoggerFactory.getLogger(AlbumController::class.java)

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
    ): ResponseEntity<CreateAlbumResponse> {
        val uuid =
            albumService.createAlbum(createAlbumRequest.name, createAlbumRequest.description, createAlbumRequest.userId)
        return ResponseEntity(CreateAlbumResponse(uuid), HttpStatus.CREATED)
    }

    @ApiOperation(
        value = "Возвращает список всех альбомов",
        response = Album::class,
        responseContainer = "List"
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "Список альбомов доступен")
        ]
    )
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAlbums(): ResponseEntity<Iterable<Album>> {
        val listOfAlbums = albumService.getAllAlbums()
        return ResponseEntity.ok(listOfAlbums)
    }

    @ApiOperation("Изменяет альбом")
    @ApiResponses(
        value = [
            ApiResponse(code = 204, message = "Альбом изменен"),
            ApiResponse(code = 404, message = "Альбом с таким идентификатором не найден")
        ]
    )
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    fun updateAlbums(
        @ApiParam("Идентификатор альбома")
        @PathVariable id: UUID,
        @ApiParam("Имя альбома")
        @RequestBody updateAlbumRequest: UpdateAlbumRequest,
    ): ResponseEntity<Unit> {
        albumService.updateAlbum(id, updateAlbumRequest.name, updateAlbumRequest.description)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @ApiOperation("Удаляет альбом")
    @ApiResponses(
        value = [
            ApiResponse(code = 204, message = "Альбом удален"),
            ApiResponse(code = 404, message = "Альбом с таким идентификатором не найден")
        ]
    )
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    fun deleteAlbum(
        @ApiParam("Идентификатор альбома")
        @PathVariable id: UUID,
    ): ResponseEntity<Unit> {
        albumService.deleteAlbum(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @ApiOperation("Добавляет фотографию в альбом")
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Фотография добавлена")
        ]
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/{albumId}/addPhoto")
    fun addPhoto(
        @ApiParam("Идентификатор альбома")
        @PathVariable albumId: UUID,
        @ApiParam("Идентификатор фотографии")
        @RequestParam addPhotoRequest: AddPhotoRequest,
    ): ResponseEntity<Unit> {
        albumWithPhotoService.addPhoto(albumId, addPhotoRequest.photoId)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @ApiOperation(
        value = "Возвращает список фотографий по идентификатору альбома",
        response = AlbumWithPhoto::class,
        responseContainer = "List"
    )
    @ApiResponses(
        value = [
            ApiResponse(
                code = 200,
                message = "Список фотографий по идентификатору альбома найден",
                response = AlbumWithPhoto::class
            ),
            ApiResponse(code = 404, message = "Фотографии с таким идентификатором альбома не найдены")
        ]
    )
    @GetMapping(value = ["{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getPhotosByAlbumId(
        @ApiParam("Идентификатор альбома")
        @PathVariable id: UUID,
    ): ResponseEntity<Iterable<UUID>> {
        val listOfPhotos = albumWithPhotoService.getPhotosByAlbumId(id)
        return ResponseEntity(listOfPhotos, HttpStatus.OK)
    }
}