package photo.photo

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
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

    @ApiModelProperty(value = "Идентификатор пользователя")
    @Column(name = "user_id", nullable = false)
    var userId: UUID,

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

    @ApiModelProperty(value = "Время создания фотографии")
    @Column(name = "photo_time", nullable = true)
    var photoTime: LocalTime?,

    @ApiModelProperty(value = "Идентификатор модели фотоаппарата")
    @Column(name = "model_id", nullable = true)
    var modelId: UUID?,

    @Column(name = "file", nullable = false)
    var file: ByteArray,

    @ApiModelProperty(value = "Идентификатор геоданных")
    @Column(name = "geo_data_id", nullable = true)
    var geoDataId: UUID?
)