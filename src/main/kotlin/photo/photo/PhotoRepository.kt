package photo.photo

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.util.*

@Repository
interface PhotoRepository : CrudRepository<Photo, UUID> {
    fun findByPhotoDate(photoDate: LocalDate): Iterable<Photo>


    @Query(
        value = "SELECT photo.id FROM geo_data JOIN photo on geo_data.id = photo.geo_data_id WHERE st_dwithin(place, st_makepoint(:latitude, :longitude)\\:\\:geography, :radius * 1000,  false);",
        nativeQuery = true
    )
    fun findPhotoInRadius(
        @Param("latitude") latitude: Double,
        @Param("longitude") longitude: Double,
        @Param("radius") radius: Int
    ): List<UUID>
}