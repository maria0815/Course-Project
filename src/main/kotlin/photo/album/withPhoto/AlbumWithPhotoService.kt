package photo.album.withPhoto

import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AlbumWithPhotoService {
    fun addPhoto(albumId: UUID, photoId: UUID)

    fun getPhotosByAlbumId(albumId: UUID): List<UUID>
}
