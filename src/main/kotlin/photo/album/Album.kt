package photo.album

import io.swagger.annotations.ApiModelProperty
import java.time.LocalDate
import java.time.LocalTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "album", schema = "public")
class Album(
    @ApiModelProperty(value = "Идентификатор альбома")
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000"),

    @ApiModelProperty(value = "Название альбома")
    @Column(name = "album_name", nullable = false)
    var name: String,

    @ApiModelProperty(value = "Дата создания альбома")
    @Column(name = "creation_date", nullable = false)
    val creationDate: LocalDate?,

    @ApiModelProperty(value = "Время создания альбома")
    @Column(name = "creation_time", nullable = false)
    val creationTime: LocalTime?,

    @ApiModelProperty(value = "Описание")
    @Column(name = "description")
    val description: String?,

    @ApiModelProperty(value = "Пользователь, создавший альбом")
    @Column(name = "user_id", nullable = false)
    val userId: UUID
)