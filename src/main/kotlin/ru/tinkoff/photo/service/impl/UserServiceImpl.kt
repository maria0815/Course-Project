package ru.tinkoff.photo.service.impl

import org.springframework.stereotype.Service
import ru.tinkoff.photo.exception.UserNotFoundException
import ru.tinkoff.photo.database.repository.UserRepository
import ru.tinkoff.photo.dto.UserDto
import ru.tinkoff.photo.database.entity.User
import ru.tinkoff.photo.service.UserService
import java.util.*

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {
    override fun createUser(name: String): UUID {
        val user = userRepository.save(User(name = name))
        return user.id
    }

    override fun updateUser(id: UUID, name: String) {
        val user = userRepository.findById(id)
            .orElseThrow { UserNotFoundException(id) }

        userRepository.save(User(user.id, name))
    }

    override fun deleteUser(id: UUID) {
        val user = userRepository.findById(id)
            .orElseThrow { UserNotFoundException(id) }
        userRepository.delete(user)
    }

    override fun getAllUsers(): Iterable<UserDto> {
        return userRepository.findAll().map { UserDto(it) }
    }

    override fun getUserById(id: UUID): UserDto {
        val user = userRepository.findById(id)
            .orElseThrow { UserNotFoundException(id) }
        return UserDto(user)
    }
}