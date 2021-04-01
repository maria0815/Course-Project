package service

import entity.Photo
import java.util.UUID
import java.util.Date

/**
 * Сервис для работы с фотографиями
 */
interface IPhotoService {
    /**
     * Возвращает фотографию по идентификатору [photoId]
     */
    fun getPhotoById(photoId: UUID): Photo

    /**
     * Загружает фотографию [photo], возвращает идентификатор сохраненной
     * фотографии
     */
    fun uploadPhoto(photo: Photo): UUID

    /**
     * Возвращает список идентификаторов фотографий за дату [date].
     * Фотографии отсортированны по времени съемки.
     */
    fun getPhotosByDate(date: Date): List<UUID>

    /**
     * Возвращает список идентификаторов всех фотографий.
     * Фотографии отсортированны по времени съемки.
     */
    fun getAllPhotos(): List<UUID>

    /**
     * Возвращает список фотографий, снятых в радиусе [radius] метров
     * от точки с координатами ([latitude], [longitude])
     */
    fun getPhotosByCoordinates(latitude: Double, longitude: Double, radius: Int): List<UUID>
}