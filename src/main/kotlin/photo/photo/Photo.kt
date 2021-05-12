package photo.photo

import io.swagger.annotations.ApiModelProperty
import java.time.LocalDate
import java.time.LocalTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "photo", schema = "public")
class Photo(
    @ApiModelProperty(value = "Идентификатор фотографии")
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000"),

    @ApiModelProperty(value = "Имя пользователя, создавшего фотографию")
    @Column(name = "user_id", nullable = false)
    var userId: UUID,

    @ApiModelProperty(value = "Наименование фотографии")
    @Column(name = "file_name")
    var fileName: String?,

    @ApiModelProperty(value = "Дата загрузки фотографии")
    @Column(name = "upload_date", nullable = false)
    var uploadDate: LocalDate?,

    @ApiModelProperty(value = "Время загрузки фотографии")
    @Column(name = "upload_time", nullable = false)
    var uploadTime: LocalTime?,

    @ApiModelProperty(value = "Дата снимка")
    @Column(name = "photo_date", nullable = false)
    var photoDate: LocalDate?,

    @ApiModelProperty(value = "Время снимка")
    @Column(name = "photo_time", nullable = false)
    var photo_time: LocalTime?,

    @ApiModelProperty(value = "Модель фотоаппарата")
    @Column(name = "model_id", nullable = false)
    var modelId: UUID?,

    @ApiModelProperty(value = "Фотография")
    @Column(name = "file", nullable = false)
    var file: ByteArray
)