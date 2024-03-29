package ru.tinkoff.photo.service.impl

import com.drew.imaging.ImageMetadataReader
import com.drew.metadata.Metadata
import com.drew.metadata.exif.ExifIFD0Directory
import com.drew.metadata.exif.GpsDirectory
import org.locationtech.jts.geom.Coordinate
import org.locationtech.jts.geom.GeometryFactory
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import ru.tinkoff.photo.database.entity.File
import ru.tinkoff.photo.database.repository.FileRepository
import ru.tinkoff.photo.database.entity.Manufacturer
import ru.tinkoff.photo.database.repository.ManufacturerRepository
import ru.tinkoff.photo.database.entity.GeoData
import ru.tinkoff.photo.database.repository.GeoDataRepository
import ru.tinkoff.photo.database.entity.Model
import ru.tinkoff.photo.database.repository.ModelRepository
import ru.tinkoff.photo.exception.PhotoNotFoundException
import ru.tinkoff.photo.database.repository.PhotoRepository
import ru.tinkoff.photo.exception.UserNotFoundException
import ru.tinkoff.photo.database.repository.UserRepository
import ru.tinkoff.photo.dto.PhotoMetadata
import ru.tinkoff.photo.database.entity.Photo
import ru.tinkoff.photo.service.PhotoService
import ru.tinkoff.photo.utils.FileNameAwareByteArrayResource
import java.io.BufferedInputStream
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.util.*

@Service
class PhotoServiceImpl(
    private val photoRepository: PhotoRepository,
    private val userRepository: UserRepository,
    private val manufacturerRepository: ManufacturerRepository,
    private val modelRepository: ModelRepository,
    private val geoDataRepository: GeoDataRepository,
    private val fileRepository: FileRepository
) :
    PhotoService {

    override fun getPhotoMetadata(photoId: UUID): PhotoMetadata {
        val photo = photoRepository.findById(photoId).orElseThrow { throw PhotoNotFoundException(photoId) }
        return PhotoMetadata(photo)
    }

    override fun getPhotoFileById(photoId: UUID): Resource {
        val photo = photoRepository.findById(photoId).orElseThrow { throw PhotoNotFoundException(photoId) }
        val file = photo.file
        return FileNameAwareByteArrayResource(file.data, file.name)
    }

    override fun uploadPhoto(multipartFile: MultipartFile, userId: UUID): UUID {
        val user = userRepository.findById(userId).orElseThrow { UserNotFoundException(userId) }

        var photoDate: LocalDate? = null
        var photoTime: LocalTime? = null

        val inputStream = BufferedInputStream(multipartFile.inputStream)
        val metadataReader = ImageMetadataReader.readMetadata(inputStream, false)
        val geoData = saveGeoData(metadataReader)
        val model = saveModelInfo(metadataReader)
        val file = saveFileInfo(multipartFile.bytes, multipartFile.originalFilename)

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
                user = user,
                uploadDate = LocalDate.now(),
                uploadTime = LocalTime.now(),
                photoDate = photoDate,
                photoTime = photoTime,
                model = model,
                file = file,
                geoData = geoData
            )
        )
        return photo.id
    }

    override fun getPhotosByDate(date: LocalDate): Iterable<UUID> =
        photoRepository.findByPhotoDateOrderByPhotoTimeAsc(date).map { it.id }

    override fun getAllPhotos(): Iterable<UUID> = photoRepository.findAll().map { it.id }

    override fun getPhotosByCoordinates(latitude: Double, longitude: Double, radius: Int): Iterable<UUID> {
        return photoRepository.findPhotoInRadius(latitude, longitude, radius).map { UUID.fromString(it) }
    }

    private fun saveGeoData(metadataReader: Metadata): GeoData? {
        val containsGpsDirectory = metadataReader.containsDirectory(GpsDirectory::class.java)
        if (!containsGpsDirectory) return null
        val gpsDirectory = metadataReader.getDirectory(GpsDirectory::class.java)
        val location = gpsDirectory.geoLocation
        val latitude = location.latitude
        val longitude = location.longitude
        val point = GeometryFactory().createPoint(Coordinate(latitude, longitude))
        return geoDataRepository.save(GeoData(place = point))
    }

    private fun saveFileInfo(data: ByteArray, fileName: String): File {
        return fileRepository.save(File(data = data, name = fileName))
    }

    private fun saveModelInfo(metadataReader: Metadata): Model? {
        val containsExifDirectory = metadataReader.containsDirectory(ExifIFD0Directory::class.java)
        if (containsExifDirectory) {
            val exifDirectory = metadataReader.getDirectory(ExifIFD0Directory::class.java)

            val cameraManufacturer = exifDirectory.getString(ExifIFD0Directory.TAG_MAKE)

            val cameraModel = exifDirectory.getString(ExifIFD0Directory.TAG_MODEL)

            val manufacturer = manufacturerRepository.findByName(cameraManufacturer)
                ?: manufacturerRepository.save(Manufacturer(name = cameraManufacturer))

            val model = modelRepository.findByNameAndManufacturerId(cameraModel, manufacturer.id)
            return model ?: modelRepository.save(Model(name = cameraModel, manufacturer = manufacturer))
        }
        return null
    }
}