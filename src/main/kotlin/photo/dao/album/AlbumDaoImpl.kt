package photo.dao.album

import photo.entity.Album
import photo.entity.AlbumWithPhoto
import java.util.*

class AlbumDaoImpl : AlbumDao {
    var albums = mutableListOf<Album>(
        Album(UUID.randomUUID(), "cats", "21/04/19", "17:08", "album of cats", UUID.randomUUID()),
        Album(UUID.randomUUID(), "dogs", "21/03/18", "12:48", "album of dogs", UUID.randomUUID()),
        Album(UUID.randomUUID(), "bears", "21/02/17", "7:18", "album of bears", UUID.randomUUID())
    )

    var albumWitPhotos = mutableListOf<AlbumWithPhoto>(
    )

    override fun createAlbum(name: String): UUID {
        val uuidForId = UUID.randomUUID()
        val uuidForClientId = UUID.randomUUID()
        albums.add(Album(id = uuidForId,
            album_name = name,
            creation_date = null,
            creation_time = null,
            description = null,
            user_id = uuidForClientId))
        return albums.last().id
    }

    override fun renameAlbum(id: UUID, name: String) {
        val album = albums.find { it.id == id }
        if (album != null) {
            album.album_name = name
        }
    }

    override fun deleteAlbum(id: UUID) {
        val album = albums.find { it.id == id }
        albums.remove(album)
    }

    override fun addPhoto(album_id: UUID, photoId: UUID) {
        val id = UUID.randomUUID()
        albumWitPhotos.add(AlbumWithPhoto(id, album_id, photoId))
    }

    override fun getAllAlbums(): MutableList<Album> {
        return albums
    }

    override fun getPhotosByAlbumId(id: UUID): List<UUID> {
        val listOfPhotos = albumWitPhotos.filter { it.album_id == id }
        return listOfPhotos.map { it.photo_id }
    }
}