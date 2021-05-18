package ru.tinkoff.photo.api.impl

import org.slf4j.LoggerFactory
import org.springframework.core.io.Resource
import org.springframework.http.*
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import ru.tinkoff.photo.api.PhotoApi
import ru.tinkoff.photo.dto.PhotoMetadata
import ru.tinkoff.photo.dto.UploadPhotoResponse
import ru.tinkoff.photo.exception.EmptyContentTypeException
import ru.tinkoff.photo.exception.EmptyFilenameException
import ru.tinkoff.photo.service.PhotoService
import java.time.LocalDate
import java.util.*

@RestController
@RequestMapping("/photos")
class PhotoController(private val photoService: PhotoService) : PhotoApi {

    private val logger = LoggerFactory.getLogger(PhotoController::class.java)

    override fun uploadPhoto(userId: UUID, file: MultipartFile): ResponseEntity<UploadPhotoResponse> {
        if (file.originalFilename == null) throw  EmptyFilenameException()
        if (file.contentType != "image/jpeg") throw  EmptyContentTypeException()
        val uuid = photoService.uploadPhoto(file, userId)
        return ResponseEntity(UploadPhotoResponse(uuid), HttpStatus.CREATED)
    }

    override fun getPhotoMetadataById(id: UUID): ResponseEntity<PhotoMetadata> {
        val metadata = photoService.getPhotoMetadata(id)
        return ResponseEntity.ok(metadata)
    }

    override fun getPhotoFileById(id: UUID): ResponseEntity<Resource> {
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

    override fun getPhotosByDate(date: LocalDate): ResponseEntity<Iterable<UUID>> {
        val listOfPhotos = photoService.getPhotosByDate(date)
        return ResponseEntity.ok(listOfPhotos)
    }

    override fun getAllPhotos(): ResponseEntity<Iterable<UUID>> {
        val listOfPhotos = photoService.getAllPhotos()
        return ResponseEntity.ok(listOfPhotos)
    }

    override fun getPhotosByCoordinates(
        latitude: Double,
        longitude: Double,
        radius: Int
    ): ResponseEntity<Iterable<UUID>> {
        val listOfPhotos = photoService.getPhotosByCoordinates(latitude, longitude, radius)
        return ResponseEntity.ok(listOfPhotos)
    }
}