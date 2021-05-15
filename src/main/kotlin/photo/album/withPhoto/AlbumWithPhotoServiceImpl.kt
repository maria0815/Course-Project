package photo.album.withPhoto

import org.springframework.stereotype.Service
import photo.album.Album
import photo.album.AlbumNotFoundException
import photo.album.AlbumRepository
import photo.photo.Photo
import photo.photo.PhotoNotFoundException
import photo.photo.PhotoRepository
import java.util.*
import javax.persistence.EntityManager

@Service
class AlbumWithPhotoServiceImpl(
    private val entityManager: EntityManager,
    private val albumWithPhotoRepository: AlbumWithPhotoRepository,
    private val albumRepository: AlbumRepository,
    private val photoRepository: PhotoRepository
) : AlbumWithPhotoService {

    override fun addPhoto(albumId: UUID, photoId: UUID) {
        if (!albumRepository.existsById(albumId)) throw  AlbumNotFoundException(albumId)
        if (!photoRepository.existsById(photoId)) throw  PhotoNotFoundException(photoId)

        if (albumWithPhotoRepository.existsByAlbumIdAndPhotoId(albumId, photoId)) {
            throw AlbumWithPhotoConflictException(albumId, photoId)
        }

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
