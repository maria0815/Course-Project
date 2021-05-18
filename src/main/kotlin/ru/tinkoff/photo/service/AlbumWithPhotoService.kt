package ru.tinkoff.photo.service

import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AlbumWithPhotoService {
    /**
     * Добавляет в альбом с идентификикатором [albumId] фотографию с идентификатором [photoId]
     */
    fun addPhoto(albumId: UUID, photoId: UUID)

    /**
     * Возвращает список идентификаторов фотографий по идентификатору альбома [albumId]
     */
    fun getPhotosByAlbumId(albumId: UUID): Iterable<UUID>
}
