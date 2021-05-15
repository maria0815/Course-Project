package photo.album.withPhoto

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AlbumWithPhotoRepository : CrudRepository<AlbumWithPhoto, UUID> {
    fun findByAlbumId(albumId: UUID): Iterable<AlbumWithPhoto>
}