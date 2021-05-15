package photo.album

import org.springframework.stereotype.Service
import photo.user.UserNotFoundException
import photo.user.UserRepository
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

@Service
class AlbumServiceImpl(
    private val albumRepository: AlbumRepository,
    private val userRepository: UserRepository
) : AlbumService {

    override fun createAlbum(name: String, description: String?, userId: UUID): UUID {
        val user = userRepository.findById(userId).orElseThrow { UserNotFoundException(userId) }

        val album = albumRepository.save(
            Album(
                name = name,
                creationDate = LocalDate.now(),
                creationTime = LocalTime.now(),
                description = description,
                user = user
            )
        )
        return album.id
    }

    override fun updateAlbum(id: UUID, name: String, description: String?) {
        val album = albumRepository.findById(id)
            .orElseThrow { throw AlbumNotFoundException(id) }

        albumRepository.save(
            Album(
                album.id,
                name,
                album.creationDate,
                album.creationTime,
                album.description,
                album.user
            )
        )
    }

    override fun deleteAlbum(id: UUID) {
        val album = albumRepository.findById(id).orElseThrow { AlbumNotFoundException(id) }
        albumRepository.delete(album)
    }

    override fun getAllAlbums(): Iterable<Album> {
        return albumRepository.findAll()
    }
}