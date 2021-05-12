package photo.album

import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import photo.album.withPhoto.AlbumWithPhoto
import photo.album.withPhoto.AlbumWithPhotoDataClass
import photo.album.withPhoto.AlbumWithPhotoService
import java.util.*

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
        @RequestBody album: CreateAlbumRequest,
    ): ResponseEntity<UUID?> {
        val uuid = albumService.createAlbum(album.name, album.userId)
        return ResponseEntity(uuid, HttpStatus.CREATED)
    }

    @ApiOperation("Возвращает список всех альбомов")
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "Список альбомов доступен")
        ]
    )
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAlbums(): ResponseEntity<Iterable<Album>> {
        val listOfAlbums = albumService.getAllAlbums()
        return ResponseEntity.ok(listOfAlbums)
    }

    @ApiOperation("Переименовывает альбом")
    @ApiResponses(
        value = [
            ApiResponse(code = 204, message = "Альбом переименован"),
            ApiResponse(code = 404, message = "Альбом с таким идентификатором не найден")
        ]
    )
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    fun renameAlbums(
        @ApiParam("Идентификатор альбома")
        @PathVariable id: UUID,
        @ApiParam("Имя альбома")
        @RequestBody name: String,
    ): ResponseEntity<Unit> {
        albumService.renameAlbum(id, name)
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
    @PostMapping("/{id}/addPhoto")
    fun addPhoto(
        @ApiParam("Идентификатор альбома")
        @PathVariable id: UUID,
        @ApiParam("Идентификатор фотографии")
        @RequestParam photoId: UUID,
    ): ResponseEntity<Unit> {
        albumWithPhotoService.addPhoto(id, photoId)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @ApiOperation("Возвращает список фотографий по идентификатору альбома")
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
    ): ResponseEntity<List<UUID>> {
        val listOfPhotos = albumWithPhotoService.getPhotosByAlbumId(id)
        return ResponseEntity(listOfPhotos, HttpStatus.OK)
    }
}