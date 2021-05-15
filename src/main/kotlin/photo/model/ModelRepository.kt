package photo.model

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ModelRepository : CrudRepository<Model, UUID> {
    fun findByNameAndManufacturerId(name: String, manufacturerId: UUID): Model?
}