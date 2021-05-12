package photo.photo

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.util.*

@Repository
interface PhotoRepository : CrudRepository<Photo, UUID> {
    fun findByPhotoDate(photoDate: LocalDate): List<Photo>
}