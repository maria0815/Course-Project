package photo.user

import java.util.*

interface UserService {
    /**
     *Создает нового пользователя с именем [name]
     * возвращает идентификатор созданного пользователя
     */
    fun createUser(name: String): UUID

    /**
     * Изменяет пользователя с идентификатором [id] на имя [name]
     */
    fun updateUser(id: UUID, name: String)

    /**
     * Удаляет пользователя с идентификатором [id]
     */
    fun deleteUser(id: UUID)

    /**
     * Возвращает список всех пользователей
     */
    fun getAllUsers(): Iterable<UserDto>

    /**
     * Возвращает пользователя по идентификатору [id]
     */
    fun getUserById(id: UUID): UserDto
}