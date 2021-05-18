package ru.tinkoff.photo.database.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.tinkoff.photo.database.entity.Manufacturer
import java.util.*

@Repository
interface ManufacturerRepository : CrudRepository<Manufacturer, UUID> {
    fun findByName(name: String): Manufacturer?
}