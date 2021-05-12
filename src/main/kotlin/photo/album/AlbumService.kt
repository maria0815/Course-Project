package photo.album

import org.springframework.stereotype.Repository
import photo.user.User
import java.util.UUID

/**
 * Сервис для работы с альбомами фотографий
 */
@Repository
interface AlbumService {
    /**
     * Создает новый альбом с наименованием [name], +
     * возвращает идентификатор созданного альбома
     */
    fun createAlbum(name: String, userId: UUID): UUID

    /**
     * Изменяет имя альбома с идентификатором [id] на имя [name]
     */
    fun renameAlbum(id: UUID, name: String)

    /**
     * Удаляет альбом с идентификатором [id]
     */
    fun deleteAlbum(id: UUID)

    /**
     * Возвращает список всех альбомов +
     */
    fun getAllAlbums(): Iterable<Album>
}