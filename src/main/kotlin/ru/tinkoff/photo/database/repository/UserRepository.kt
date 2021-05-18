package ru.tinkoff.photo.database.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.tinkoff.photo.database.entity.User
import java.util.*

@Repository
interface UserRepository : CrudRepository<User, UUID>