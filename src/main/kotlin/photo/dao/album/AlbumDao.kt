package photo.dao.album

import photo.entity.Album
import org.springframework.stereotype.Repository
import java.util.UUID

/**
 * Сервис для работы с альбомами фотографий
 */
@Repository
interface AlbumDao {
    /**
     * Создает новый альбом с наименованием [name], +
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
     * Возвращает список всех альбомов +
     */
    fun getAllAlbums(): MutableList<Album>

    /**
     * Возвращает список всех фотографий в альбоме с идентификатором [id]
     */
    fun getPhotosByAlbumId(id: UUID): List<UUID>
}