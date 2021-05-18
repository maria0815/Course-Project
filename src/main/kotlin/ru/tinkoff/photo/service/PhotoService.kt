package ru.tinkoff.photo.service

import org.springframework.core.io.Resource
import org.springframework.web.multipart.MultipartFile
import ru.tinkoff.photo.dto.PhotoMetadata
import java.time.LocalDate
import java.util.UUID

/**
 * Сервис для работы с фотографиями
 */
interface PhotoService {
    /**
     * Возвращает метаданные фотографии по идентификатору [photoId]
     */
    fun getPhotoMetadata(photoId: UUID): PhotoMetadata

    /**
     * Возвращает файл фотографии по идентификатору [photoId]
     */
    fun getPhotoFileById(photoId: UUID): Resource

    /**
     * Загружает фотографию [multipartFile], возвращает идентификатор сохраненной
     * фотографии
     */
    fun uploadPhoto(multipartFile: MultipartFile, userId: UUID): UUID

    /**
     * Возвращает список идентификаторов фотографий за дату [date], отсортированных по времени съемки.
     */
    fun getPhotosByDate(date: LocalDate): Iterable<UUID>

    /**
     * Возвращает список идентификаторов всех фотографий.
     */
    fun getAllPhotos(): Iterable<UUID>

    /**
     * Возвращает список идентификаторов фотографий, снятых в радиусе [radius] метров
     * от точки с координатами ([latitude], [longitude])
     */
    fun getPhotosByCoordinates(latitude: Double, longitude: Double, radius: Int): Iterable<UUID>
}