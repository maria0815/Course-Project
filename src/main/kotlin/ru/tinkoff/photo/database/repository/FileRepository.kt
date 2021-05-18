package ru.tinkoff.photo.database.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.tinkoff.photo.database.entity.File
import java.util.*

@Repository
interface FileRepository : CrudRepository<File, UUID>