package photo.api

import photo.entity.Album
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.web.bind.annotation.*
import photo.request.CreateAlbumRequest
import photo.dao.album.AlbumDao
import java.util.*

@RestController
@RequestMapping("/albums")
class AlbumController(private val albumDao: AlbumDao) {

    @ApiOperation("Создает новый альбом")
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Альбом создан"),
            ApiResponse(code = 400, message = "Переданный альбом имеет не корректный формат")
        ]
    )
    @PostMapping("")
    fun createAlbum(
        @ApiParam("Создаваемый альбом")
        @RequestBody album: CreateAlbumRequest,
    ): UUID {
        return albumDao.createAlbum(album.name)
    }

    @ApiOperation("Возвращает список всех альбомов")
    @GetMapping
    fun getAlbums(): MutableList<Album> {
        return albumDao.getAllAlbums()
    }

    @ApiOperation("Переименовывает альбом")
    @PutMapping("/{id}")
    fun renameAlbums(
        @ApiParam("Идентификатор альбома")
        @PathVariable id: UUID,
        @ApiParam("Имя альбома")
        @RequestBody name: String,
    ) {
        albumDao.renameAlbum(id, name)
    }

    @ApiOperation("Удаляет альбом")
    @DeleteMapping("/{id}")
    fun deleteAlbum(
        @ApiParam("Идентификатор альбома")
        @PathVariable id: UUID,
    ) {
        albumDao.deleteAlbum(id)
    }

    @ApiOperation("Добавляет фотографию в альбом")
    @PostMapping("/{id}/addPhoto")
    fun addPhoto(
        @ApiParam("Идентификатор альбома")
        @PathVariable id: UUID,
        @ApiParam("Идентификатор фотографии")
        @RequestBody photoId: UUID,
    ) {
        albumDao.addPhoto(id, photoId)
    }

    @ApiOperation("Возвращает список фотографий по идентификатору альбома")
    @GetMapping("/{id}")
    fun getPhotosByAlbumId(
        @ApiParam("Идентификатор альбома")
        @PathVariable id: UUID,
    ): List<UUID> {
        return albumDao.getPhotosByAlbumId(id)
    }
}