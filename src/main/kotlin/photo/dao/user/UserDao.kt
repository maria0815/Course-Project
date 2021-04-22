package photo.dao.user

import photo.entity.User
import java.util.*

interface UserDao {
    fun createUser(name: String): UUID
    fun renameUser(id: UUID, name: String)
    fun deleteUser(id: UUID)
    fun getAllUsers(): List<User>
    fun getUserById(id: UUID): User?
}