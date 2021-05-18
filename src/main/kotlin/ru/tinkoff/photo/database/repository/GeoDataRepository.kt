package ru.tinkoff.photo.database.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.tinkoff.photo.database.entity.GeoData
import java.util.*

@Repository
interface GeoDataRepository : CrudRepository<GeoData, UUID>