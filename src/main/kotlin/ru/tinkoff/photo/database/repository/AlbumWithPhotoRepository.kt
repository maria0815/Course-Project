package ru.tinkoff.photo.database.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.tinkoff.photo.database.entity.AlbumWithPhoto
import java.util.*

@Repository
interface AlbumWithPhotoRepository : CrudRepository<AlbumWithPhoto, UUID> {
    fun findByAlbumId(albumId: UUID): Iterable<AlbumWithPhoto>
    fun existsByAlbumIdAndPhotoId(albumId: UUID, photoId: UUID): Boolean
}