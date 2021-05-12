package photo.album.withPhoto

import io.swagger.annotations.ApiModelProperty
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "album_with_photos", schema = "public")
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