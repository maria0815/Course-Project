package photo.album

import java.util.UUID

/**
 * Сервис для работы с альбомами фотографий
 */
interface AlbumService {
    /**
     * Создает новый альбом с наименованием [name], описанием [description] и пользователем, владельцем альбома [userId]
     * возвращает идентификатор созданного альбома
     */
    fun createAlbum(name: String, description: String?, userId: UUID): UUID

    /**
     * Изменяет имя альбома с идентификатором [id] на имя [name] и на описание [description]
     */
    fun updateAlbum(id: UUID, name: String, description: String?)

    /**
     * Удаляет альбом с идентификатором [id]
     */
    fun deleteAlbum(id: UUID)

    /**
     * Возвращает список всех альбомов
     */
    fun getAllAlbums(): Iterable<AlbumDto>
}