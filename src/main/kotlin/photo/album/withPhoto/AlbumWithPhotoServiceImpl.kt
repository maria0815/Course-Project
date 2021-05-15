package photo.album.withPhoto

import org.springframework.stereotype.Service
import java.util.*

@Service
class AlbumWithPhotoServiceImpl(
    private val albumWithPhotoRepository: AlbumWithPhotoRepository
) : AlbumWithPhotoService {

    override fun addPhoto(albumId: UUID, photoId: UUID) {
        //TODO проверить если такая запись уже существует
        albumWithPhotoRepository.save(AlbumWithPhoto(albumId = albumId, photoId = photoId))
    }

    override fun getPhotosByAlbumId(albumId: UUID): Iterable<UUID> {
        return albumWithPhotoRepository.findByAlbumId(albumId).map { it.photoId }
    }
}