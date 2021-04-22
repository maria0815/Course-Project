package photo.dao.photo

import photo.entity.Photo
import java.time.LocalDate
import java.util.*

class PhotoDaoImpl : PhotoDao {
    var photos = mutableListOf<Photo>()

    override fun getPhotoById(photoId: UUID): Photo? {
        return photos.find { it.id == photoId }
    }

    override fun uploadPhoto(photo: Photo): UUID {
        photos.add(photo)
        return photos.last().id
    }

    override fun getPhotosByDate(date: LocalDate): List<UUID> {
        val listOfPhotosByDate = photos.filter { it.photo_date == date }
        val listOfPhotosByTime = listOfPhotosByDate.sortedBy { it.photo_time }
        return listOfPhotosByTime.map { it.id }
    }

    override fun getAllPhotos(): List<UUID> {
        return photos.map { it.id }
    }

    override fun getPhotosByCoordinates(latitude: Double, longitude: Double, radius: Int): List<UUID>? {
        println("пока не готово")
        return null
    }

}