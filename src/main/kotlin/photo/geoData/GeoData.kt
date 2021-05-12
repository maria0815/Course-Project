package photo.geoData

import io.swagger.annotations.ApiModelProperty
import org.postgis.Point

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "geo_data", schema = "public")
class GeoData(
    @ApiModelProperty(value = "Идентификатор фотографии")
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000"),

    @ApiModelProperty(value = "Место, где была сделана фотография")
    @Column(name = "place", nullable = false)
    var place: Point
)