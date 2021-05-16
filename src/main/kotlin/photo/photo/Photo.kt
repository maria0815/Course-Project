package photo.photo

import photo.album.withPhoto.AlbumWithPhoto
import photo.file.File
import photo.geoData.GeoData
import photo.model.Model
import photo.user.User
import java.time.LocalDate
import java.time.LocalTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "photo", schema = "public")
class Photo(
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000"),

    @Column(name = "upload_date", nullable = false)
    val uploadDate: LocalDate,

    @Column(name = "upload_time", nullable = false)
    val uploadTime: LocalTime,

    @Column(name = "photo_date", nullable = true)
    val photoDate: LocalDate?,

    @Column(name = "photo_time", nullable = true)
    val photoTime: LocalTime?,

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    val user: User,

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id", referencedColumnName = "id")
    val file: File,

    @OneToOne(optional = true)
    @JoinColumn(name = "geo_data_id", referencedColumnName = "id")
    val geoData: GeoData? = null,

    @ManyToOne(optional = true)
    @JoinColumn(name = "model_id", referencedColumnName = "id")
    val model: Model? = null,

    @OneToMany(mappedBy = "photo", fetch = FetchType.LAZY)
    val albumWithPhotos: MutableList<AlbumWithPhoto> = mutableListOf()
)