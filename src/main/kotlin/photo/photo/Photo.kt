package photo.photo

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import photo.album.withPhoto.AlbumWithPhoto
import photo.geoData.GeoData
import photo.model.Model
import photo.user.User
import java.time.LocalDate
import java.time.LocalTime
import java.util.*
import javax.persistence.*

@ApiModel("Фотография")
@Entity
@Table(name = "photo", schema = "public")
class Photo(
    @ApiModelProperty(value = "Идентификатор фотографии")
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000"),

    @ApiModelProperty(value = "Имя файла")
    @Column(name = "file_name", nullable = false)
    var fileName: String,

    @ApiModelProperty(value = "Дата загрузки фотографии")
    @Column(name = "upload_date", nullable = false)
    var uploadDate: LocalDate,

    @ApiModelProperty(value = "Время загрузки фотографии")
    @Column(name = "upload_time", nullable = false)
    var uploadTime: LocalTime,

    @ApiModelProperty(value = "Дата создания фотографии")
    @Column(name = "photo_date", nullable = true)
    var photoDate: LocalDate?,

    @Column(name = "photo_time", nullable = true)
    val photoTime: LocalTime?,

    @Column(name = "file", nullable = false)
    val file: ByteArray,

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    val user: User,

    @OneToOne(optional = true)
    @JoinColumn(name = "geo_data_id", referencedColumnName = "id")
    val geoData: GeoData? = null,

    @ManyToOne(optional = true)
    @JoinColumn(name = "model_id", referencedColumnName = "id")
    val model: Model? = null,

    @OneToMany(mappedBy = "photo")
    val albumWithPhotos: List<AlbumWithPhoto> = mutableListOf()
)