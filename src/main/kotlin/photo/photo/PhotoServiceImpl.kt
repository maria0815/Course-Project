package photo.photo

import com.drew.imaging.ImageMetadataReader
import com.drew.metadata.Metadata
import com.drew.metadata.exif.ExifIFD0Directory
import com.drew.metadata.exif.GpsDirectory
import org.postgis.Point
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import photo.brand.Brand
import photo.brand.BrandRepository
import photo.geoData.GeoData
import photo.geoData.GeoDataRepository
import photo.model.Model
import photo.model.ModelRepository
import java.io.BufferedInputStream
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.util.*

@Service
class PhotoServiceImpl(
    private val photoRepository: PhotoRepository,
    private val brandRepository: BrandRepository,
    private val modelRepository: ModelRepository,
    private val geoDataRepository: GeoDataRepository
) :
    PhotoService {

    override fun getPhotoById(photoId: UUID): Photo? {
        return photoRepository.findByIdOrNull(photoId)
    }

    override fun uploadPhoto(file: MultipartFile, userId: UUID): UUID? {
        var photoDate: LocalDate? = null
        var photoTime: LocalTime? = null

        val inputStream = BufferedInputStream(file.inputStream)
        val metadataReader = ImageMetadataReader.readMetadata(inputStream, false)
        val geoDataId = saveGeoData(metadataReader)
        val modelId = saveModelInfo(metadataReader)

        val containsExifDirectory = metadataReader.containsDirectory(ExifIFD0Directory::class.java)
        if (containsExifDirectory) {
            val exifDirectory = metadataReader.getDirectory(ExifIFD0Directory::class.java)
            val date = exifDirectory.getDate(ExifIFD0Directory.TAG_DATETIME)
            val dateTime = Instant.ofEpochMilli(date.time).atZone(ZoneId.systemDefault()).toLocalDateTime()
            photoDate = dateTime.toLocalDate()
            photoTime = dateTime.toLocalTime()
        }

        val photo = photoRepository.save(
            Photo(
                userId = userId,
                fileName = file.name,
                uploadDate = LocalDate.now(),
                uploadTime = LocalTime.now(),
                photoDate = photoDate,
                photo_time = photoTime,
                modelId = modelId,
                file = file.bytes
            )
        )
        return photo.id
    }

    private fun saveGeoData(metadataReader: Metadata): UUID? {
        val containsGpsDirectory = metadataReader.containsDirectory(GpsDirectory::class.java)
        if (!containsGpsDirectory) return null
        val gpsDirectory = metadataReader.getDirectory(GpsDirectory::class.java)
        val location = gpsDirectory.geoLocation
        val latitude = location.latitude
        val longitude = location.longitude
        val geoData = geoDataRepository.save(GeoData(place = Point(latitude, longitude)))
        return geoData.id
    }

    private fun saveModelInfo(metadataReader: Metadata): UUID? {
        val containsExifDirectory = metadataReader.containsDirectory(ExifIFD0Directory::class.java)
        if (containsExifDirectory) {
            val exifDirectory = metadataReader.getDirectory(ExifIFD0Directory::class.java)

            val cameraName = exifDirectory.getString(ExifIFD0Directory.TAG_MAKE)
            val cameraModel = exifDirectory.getString(ExifIFD0Directory.TAG_MODEL)

            val brand = brandRepository.findByName(cameraName)
            val brandId: UUID
            brandId = brand?.id ?: brandRepository.save(Brand(name = cameraName)).id

            val model = modelRepository.findByNameAndBrandId(cameraName, brandId)
            return model?.id ?: modelRepository.save(Model(name = cameraName, brandId = brandId)).id
        }

        return null
    }

    override fun getPhotosByDate(date: LocalDate): List<UUID> =
        photoRepository.findByPhotoDate(date).map { it.id }

    override fun getAllPhotos(): List<UUID> = photoRepository.findAll().map { it.id }

    override fun getPhotosByCoordinates(latitude: Double, longitude: Double, radius: Int): List<UUID> {
        //return geoDataRepository.findInRadius(latitude, longitude, radius).map { it.photoId }
        return emptyList()
    }
}