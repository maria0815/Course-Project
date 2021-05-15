package photo.album.withPhoto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.util.*
import javax.persistence.*

@ApiModel("Альбом с фотографиями")
@Entity
@Table(
    name = "album_with_photos",
    schema = "public",
    uniqueConstraints = [UniqueConstraint(columnNames = ["album_id", "photo_id"])]
)
class AlbumWithPhoto(
    @ApiModelProperty(value = "Идентификатор записи")
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000"),

    @ApiModelProperty(value = "Идентификатор альбома")
    @Column(name = "album_id", nullable = false)
    val albumId: UUID,

    @ApiModelProperty(value = "Идентификатор фотографии")
    @Column(name = "photo_id", nullable = false)
    val photoId: UUID
)