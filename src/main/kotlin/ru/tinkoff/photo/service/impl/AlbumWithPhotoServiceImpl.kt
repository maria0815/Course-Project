package ru.tinkoff.photo.service.impl

import org.springframework.stereotype.Service
import ru.tinkoff.photo.database.entity.Album
import ru.tinkoff.photo.exception.AlbumNotFoundException
import ru.tinkoff.photo.database.repository.AlbumRepository
import ru.tinkoff.photo.exception.AlbumWithPhotoConflictException
import ru.tinkoff.photo.database.repository.AlbumWithPhotoRepository
import ru.tinkoff.photo.database.entity.Photo
import ru.tinkoff.photo.exception.PhotoNotFoundException
import ru.tinkoff.photo.database.repository.PhotoRepository
import ru.tinkoff.photo.database.entity.AlbumWithPhoto
import ru.tinkoff.photo.service.AlbumWithPhotoService
import java.util.*
import javax.persistence.EntityManager

@Service
class AlbumWithPhotoServiceImpl(
    private val entityManager: EntityManager,
    private val albumWithPhotoRepository: AlbumWithPhotoRepository,
    private val albumRepository: AlbumRepository,
    private val photoRepository: PhotoRepository
) : AlbumWithPhotoService {

    override fun addPhoto(albumId: UUID, photoId: UUID) {
        if (!albumRepository.existsById(albumId)) throw  AlbumNotFoundException(albumId)
        if (!photoRepository.existsById(photoId)) throw  PhotoNotFoundException(photoId)

        if (albumWithPhotoRepository.existsByAlbumIdAndPhotoId(albumId, photoId)) {
            throw AlbumWithPhotoConflictException(albumId, photoId)
        }

        albumWithPhotoRepository.save(
            AlbumWithPhoto(
                album = entityManager.getReference(Album::class.java, albumId),
                photo = entityManager.getReference(Photo::class.java, photoId)
            )
        )
    }

    override fun getPhotosByAlbumId(albumId: UUID): Iterable<UUID> {
        return albumWithPhotoRepository.findByAlbumId(albumId).map { it.photo.id }
    }
}
