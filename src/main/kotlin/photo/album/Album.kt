package photo.album

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import photo.album.withPhoto.AlbumWithPhoto
import photo.user.User
import java.time.LocalDate
import java.time.LocalTime
import java.util.*
import javax.persistence.*

@ApiModel("Альбом")
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
    val creationDate: LocalDate,

    @ApiModelProperty(value = "Время создания альбома")
    @Column(name = "creation_time", nullable = false)
    val creationTime: LocalTime,

    @ApiModelProperty(value = "Описание")
    @Column(name = "description", nullable = true)
    val description: String?,

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    val user: User,

    @OneToMany(mappedBy = "album")
    val albumsWithPhoto: List<AlbumWithPhoto> = mutableListOf()
)