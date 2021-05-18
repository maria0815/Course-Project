package ru.tinkoff.photo.database.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.tinkoff.photo.database.entity.Model
import java.util.*

@Repository
interface ModelRepository : CrudRepository<Model, UUID> {
    fun findByNameAndManufacturerId(name: String, manufacturerId: UUID): Model?
}