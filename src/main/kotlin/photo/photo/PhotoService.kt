package photo.photo

import org.springframework.web.multipart.MultipartFile
import java.time.LocalDate
import java.util.UUID

/**
 * Сервис для работы с фотографиями
 */
interface PhotoService {
    /**
     * Возвращает фотографию по идентификатору [photoId]
     */
    fun getPhotoById(photoId: UUID): Photo?

    /**
     * Загружает фотографию [photo], возвращает идентификатор сохраненной
     * фотографии
     */
    fun uploadPhoto(file: MultipartFile, userId: UUID): UUID?

    /**
     * Возвращает список идентификаторов фотографий за дату [date].
     * Фотографии отсортированны по времени съемки.
     */
    fun getPhotosByDate(date: LocalDate): List<UUID>

    /**
     * Возвращает список идентификаторов всех фотографий.
     * Фотографии отсортированны по времени съемки.
     */
    fun getAllPhotos(): List<UUID>

    /**
     * Возвращает список фотографий, снятых в радиусе [radius] метров
     * от точки с координатами ([latitude], [longitude])
     */
    fun getPhotosByCoordinates(latitude: Double, longitude: Double, radius: Int): List<UUID>?

}