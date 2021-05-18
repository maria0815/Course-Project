package ru.tinkoff.photo.api.impl

import io.swagger.annotations.*
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.tinkoff.photo.service.AlbumWithPhotoService
import ru.tinkoff.photo.api.AlbumApi
import ru.tinkoff.photo.dto.*
import ru.tinkoff.photo.service.AlbumService
import java.util.*

@Api(description = "Операции для работы с альбомами")
@RestController
@RequestMapping("/albums")
class AlbumController(
    private val albumService: AlbumService,
    private val albumWithPhotoService: AlbumWithPhotoService
) : AlbumApi {
    private val logger = LoggerFactory.getLogger(AlbumController::class.java)

    override fun createAlbum(createAlbumRequest: CreateAlbumRequest): ResponseEntity<CreateAlbumResponse> {
        val uuid =
            albumService.createAlbum(createAlbumRequest.name, createAlbumRequest.description, createAlbumRequest.userId)
        return ResponseEntity(CreateAlbumResponse(uuid), HttpStatus.CREATED)
    }

    override fun getAlbums(): ResponseEntity<Iterable<AlbumDto>> {
        val listOfAlbums = albumService.getAllAlbums()
        return ResponseEntity.ok(listOfAlbums)
    }

    override fun updateAlbums(id: UUID, updateAlbumRequest: UpdateAlbumRequest): ResponseEntity<Unit> {
        albumService.updateAlbum(id, updateAlbumRequest.name, updateAlbumRequest.description)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    override fun deleteAlbum(id: UUID): ResponseEntity<Unit> {
        albumService.deleteAlbum(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    override fun addPhoto(albumId: UUID, request: AddPhotoRequest): ResponseEntity<Unit> {
        albumWithPhotoService.addPhoto(albumId, request.photoId)
        return ResponseEntity(HttpStatus.CREATED)
    }

    override fun getPhotosByAlbumId(id: UUID): ResponseEntity<Iterable<UUID>> {
        val listOfPhotos = albumWithPhotoService.getPhotosByAlbumId(id)
        return ResponseEntity(listOfPhotos, HttpStatus.OK)
    }
}