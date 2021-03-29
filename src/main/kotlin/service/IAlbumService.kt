package service

import entity.Album

/**
 * Сервис для работы с альбомами фотографий
 */
interface IAlbumService {
    /**
     * Создает новый альбом с наименованием [name],
     * возвращает идентификатор созданного альбома
     */
    fun createAlbum(name: String): Int

    /**
     * Изменяет имя альбома с идентификатором [id] на имя [name]
     */
    fun renameAlbum(id: Int, name: String)

    /**
     * Удаляет альбом с идентификатором [id]
     */
    fun deleteAlbum(id: Int)

    /**
     * Добавляет фотографию с идентификатором [photoId]
     * в альбом с идентификатором [id]
     */
    fun addPhoto(photoId: Int, id: Int)

    /**
     * Возвращает список всех альбомов
     */
    fun getAlbums(): List<Album>

    /**
     * Возвращает список всех фотографий в альбоме с идентификатором [id]
     */
    fun getPhotosByAlbumId(id: Int)
}