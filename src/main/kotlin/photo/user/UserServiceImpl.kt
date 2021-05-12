package photo.user

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {
    override fun createUser(name: String): UUID {
        val user = userRepository.save(User(name = name))
        return user.id
    }

    override fun renameUser(id: UUID, name: String) {
        val user = userRepository.findByIdOrNull(id)
        if (user != null) {
            userRepository.save(User(user.id, name))
        }
    }

    override fun deleteUser(id: UUID) {
        userRepository.deleteById(id)
    }

    override fun getAllUsers(): Iterable<User> {
        return userRepository.findAll()
    }

    override fun getUserById(id: UUID): User? {
        return userRepository.findByIdOrNull(id)
    }
}