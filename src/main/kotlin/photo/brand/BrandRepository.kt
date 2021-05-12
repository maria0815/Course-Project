package photo.brand

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import photo.model.Model
import java.util.*

@Repository
interface BrandRepository : CrudRepository<Brand, UUID> {
    fun findByName(name: String?): Brand?
}