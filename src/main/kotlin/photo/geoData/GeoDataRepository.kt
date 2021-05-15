package photo.geoData

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface GeoDataRepository : CrudRepository<GeoData, UUID>