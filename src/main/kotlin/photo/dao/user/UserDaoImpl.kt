package photo.dao.user

import photo.entity.User
import java.util.*

class UserDaoImpl : UserDao {
    private val users = mutableListOf(
        User(UUID.fromString("11111111-1111-1111-1111-111111111111"), "Ivan"),
        User(UUID.fromString("22222222-2222-2222-2222-222222222222"), "Alex"),
        User(UUID.fromString("33333333-3333-3333-3333-333333333333"), "Anna")
    )

    override fun createUser(name: String): UUID {
        val id = UUID.randomUUID()
        users.add(User(id, name))
        return id
    }

    override fun renameUser(id: UUID, name: String) {
        val user = users.find { it.id == id }
        if (user != null) {
            user.name = name
        }
    }

    override fun deleteUser(id: UUID) {
        val user = users.find { it.id == id }
        users.remove(user)
    }

    override fun getAllUsers(): List<User> {
        return users
    }

    override fun getUserById(id: UUID): User? {
        return users.find { it.id == id }
    }
}