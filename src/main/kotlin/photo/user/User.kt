package photo.user

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import photo.album.Album
import photo.photo.Photo
import java.util.*
import javax.persistence.*

@ApiModel("Пользователь")
@Entity
@Table(name = "users", schema = "public")
class User(
    @ApiModelProperty(value = "Идентификатор пользователя")
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000"),

    @ApiModelProperty(value = "Имя пользователя")
    @Column(name = "name", nullable = false)
    val name: String,

    @OneToMany(mappedBy = "user")
    val photos: List<Photo> = mutableListOf(),

    @OneToMany(mappedBy = "user")
    val albums: List<Album> = mutableListOf()
)