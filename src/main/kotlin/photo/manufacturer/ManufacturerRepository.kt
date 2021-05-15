package photo.manufacturer

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ManufacturerRepository : CrudRepository<Manufacturer, UUID> {
    fun findByName(name: String): Manufacturer?
}