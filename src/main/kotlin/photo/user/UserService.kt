package photo.user

import java.util.*

interface UserService {
    fun createUser(name: String): UUID
    fun updateUser(id: UUID, name: String)
    fun deleteUser(id: UUID)
    fun getAllUsers(): Iterable<User>
    fun getUserById(id: UUID): User
}