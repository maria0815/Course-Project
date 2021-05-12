package photo.geoData

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface GeoDataRepository : CrudRepository<GeoData, UUID> {

    @Query(
        value = "SELECT * FROM geo_data WHERE st_dwithin(place, st_makepoint(:latitude,:longitude)\\:\\:geography, :radius*1000, false)",
        nativeQuery = true
    )
    fun findInRadius(
        @Param("latitude") latitude: Double,
        @Param("longitude") longitude: Double,
        @Param("radius") radius: Int
    ): List<GeoData>
}