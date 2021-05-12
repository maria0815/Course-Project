package photo.model

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ModelRepository : CrudRepository<Model, UUID> {
    fun findByNameAndBrandId(name: String?, brandId: UUID): Model?
}