package ru.tinkoff.photo.database.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.tinkoff.photo.database.entity.Album
import java.util.*

@Repository
interface AlbumRepository : CrudRepository<Album, UUID>