package photo.album.withPhoto

import org.springframework.stereotype.Service
import java.util.*

@Service
class AlbumWithPhotoImpl(private val albumWithPhotoRepository: AlbumWithPhotoRepository) : AlbumWithPhotoService {
    override fun addPhoto(albumId: UUID, photoId: UUID) {
        albumWithPhotoRepository.save(AlbumWithPhoto(albumId = albumId, photoId = photoId))
    }

    override fun getPhotosByAlbumId(albumId: UUID): List<UUID> {
        val allPhoto = albumWithPhotoRepository.findAll()
        val listOfPhotoByAlbumId = allPhoto.filter { it.albumId == albumId }
        return listOfPhotoByAlbumId.map { it.photoId }
    }
}