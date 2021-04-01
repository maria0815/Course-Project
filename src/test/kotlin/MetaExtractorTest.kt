import com.drew.imaging.ImageMetadataReader
import com.drew.metadata.exif.ExifIFD0Directory
import com.drew.metadata.exif.GpsDirectory
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

class MetaExtractorTest {

    @Test
    fun `read metadata from resource 1 test`() {
        val photo = File(this.javaClass.getResource("photos/1.jpg").file)
        val metadataReader = ImageMetadataReader.readMetadata(photo)

        val containsGpsDirectory = metadataReader.containsDirectory(GpsDirectory::class.java)
        Assertions.assertTrue(containsGpsDirectory)

        val gpsDirectory = metadataReader.getDirectory(GpsDirectory::class.java)

        val location = gpsDirectory.geoLocation
        Assertions.assertEquals(54.989666666666665, location.latitude)
        Assertions.assertEquals(-1.9141666666666666, location.longitude)

        val latitudeRef = gpsDirectory.getString(GpsDirectory.TAG_GPS_LATITUDE_REF)
        Assertions.assertEquals("N", latitudeRef)

        val latitude = gpsDirectory.getDescription(GpsDirectory.TAG_GPS_LATITUDE)
        Assertions.assertEquals("54.0° 59.0' 22.799999999992906\"", latitude)

        val longitudeRef = gpsDirectory.getString(GpsDirectory.TAG_GPS_LONGITUDE_REF)
        Assertions.assertEquals("W", longitudeRef)

        val longitude = gpsDirectory.getDescription(GpsDirectory.TAG_GPS_LONGITUDE)
        Assertions.assertEquals("-1.0° 54.0' 50.99999999999966\"", longitude)

        val containsExifDirectory = metadataReader.containsDirectory(ExifIFD0Directory::class.java)
        Assertions.assertTrue(containsExifDirectory)

        val exifDirectory = metadataReader.getDirectory(ExifIFD0Directory::class.java)

        val dateTime = exifDirectory.getDate(ExifIFD0Directory.TAG_DATETIME)
        Assertions.assertEquals(1027070890000, dateTime.time)

        val cameraName = exifDirectory.getString(ExifIFD0Directory.TAG_MAKE)
        Assertions.assertEquals("FUJIFILM", cameraName)

        val cameraModel = exifDirectory.getString(ExifIFD0Directory.TAG_MODEL)
        Assertions.assertEquals("FinePixS1Pro", cameraModel)
    }

    @Test
    fun `read metadata from resource 2 test`() {
        val photo = File(this.javaClass.getResource("photos/2.jpg").file)
        val metadataReader = ImageMetadataReader.readMetadata(photo)

        val containsGpsDirectory = metadataReader.containsDirectory(GpsDirectory::class.java)
        Assertions.assertTrue(containsGpsDirectory)

        val gpsDirectory = metadataReader.getDirectory(GpsDirectory::class.java)

        val location = gpsDirectory.geoLocation
        Assertions.assertEquals(41.853, location.latitude)
        Assertions.assertEquals(12.488833333333334, location.longitude)

        val latitudeRef = gpsDirectory.getString(GpsDirectory.TAG_GPS_LATITUDE_REF)
        Assertions.assertEquals("N", latitudeRef)

        val latitude = gpsDirectory.getDescription(GpsDirectory.TAG_GPS_LATITUDE)
        Assertions.assertEquals("41.0° 51.0' 10.800000000005525\"", latitude)

        val longitudeRef = gpsDirectory.getString(GpsDirectory.TAG_GPS_LONGITUDE_REF)
        Assertions.assertEquals("E", longitudeRef)

        val longitude = gpsDirectory.getDescription(GpsDirectory.TAG_GPS_LONGITUDE)
        Assertions.assertEquals("12.0° 29.0' 19.80000000000203\"", longitude)

        val containsExifDirectory = metadataReader.containsDirectory(ExifIFD0Directory::class.java)
        Assertions.assertTrue(containsExifDirectory)

        val exifDirectory = metadataReader.getDirectory(ExifIFD0Directory::class.java)

        val dateTime = exifDirectory.getDate(ExifIFD0Directory.TAG_DATETIME)
        Assertions.assertEquals(1294918419000, dateTime.time)

        val cameraName = exifDirectory.getString(ExifIFD0Directory.TAG_MAKE)
        Assertions.assertEquals("Apple", cameraName)

        val cameraModel = exifDirectory.getString(ExifIFD0Directory.TAG_MODEL)
        Assertions.assertEquals("iPhone 4", cameraModel)
    }
}