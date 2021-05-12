package photo.album

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import photo.user.User
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

@Service
class AlbumServiceImpl(private val albumRepository: AlbumRepository) : AlbumService {
    val creationDate = LocalDate.now()
    val creationTime = LocalTime.now()
    val description: String? = null

    override fun createAlbum(name: String, userId: UUID): UUID {
        val album =
            albumRepository.save(
                Album(
                    name = name,
                    creationDate = creationDate,
                    creationTime = creationTime,
                    description = description,
                    userId = userId
                )
            )
        return album.id
    }

    override fun renameAlbum(id: UUID, name: String) {
        val album = albumRepository.findByIdOrNull(id)
        if (album != null) {
            albumRepository.save(
                Album(
                    album.id,
                    name,
                    album.creationDate,
                    album.creationTime,
                    album.description,
                    album.userId
                )
            )
        }
    }

    override fun deleteAlbum(id: UUID) {
        albumRepository.deleteById(id)
    }

    override fun getAllAlbums(): Iterable<Album> {
        return albumRepository.findAll()
    }
}