package photo.album.withPhoto

import org.springframework.stereotype.Service
import photo.album.Album
import photo.photo.Photo
import java.util.*
import javax.persistence.EntityManager

@Service
class AlbumWithPhotoServiceImpl(
    private val entityManager: EntityManager,
    private val albumWithPhotoRepository: AlbumWithPhotoRepository
) : AlbumWithPhotoService {

    override fun addPhoto(albumId: UUID, photoId: UUID) {
        //TODO проверить если такая запись уже существует
        albumWithPhotoRepository.save(
            AlbumWithPhoto(
                album = entityManager.getReference(Album::class.java, albumId),
                photo = entityManager.getReference(Photo::class.java, photoId)
            )
        )
    }

    override fun getPhotosByAlbumId(albumId: UUID): Iterable<UUID> {
        return albumWithPhotoRepository.findByAlbumId(albumId).map { it.photo.id }
    }
}