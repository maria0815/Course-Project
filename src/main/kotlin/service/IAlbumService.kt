package service

import entity.Album
import java.util.UUID

/**
 * Сервис для работы с альбомами фотографий
 */
interface IAlbumService {
    /**
     * Создает новый альбом с наименованием [name],
     * возвращает идентификатор созданного альбома
     */
    fun createAlbum(name: String): UUID

    /**
     * Изменяет имя альбома с идентификатором [id] на имя [name]
     */
    fun renameAlbum(id: UUID, name: String)

    /**
     * Удаляет альбом с идентификатором [id]
     */
    fun deleteAlbum(id: UUID)

    /**
     * Добавляет фотографию с идентификатором [photoId]
     * в альбом с идентификатором [id]
     */
    fun addPhoto(photoId: UUID, id: UUID)

    /**
     * Возвращает список всех альбомов
     */
    fun getAlbums(): List<Album>

    /**
     * Возвращает список всех фотографий в альбоме с идентификатором [id]
     */
    fun getPhotosByAlbumId(id: UUID)
}