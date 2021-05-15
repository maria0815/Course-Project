package photo.geoData

import org.locationtech.jts.geom.Point
import photo.photo.Photo
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "geo_data", schema = "public")
class GeoData(
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000"),

    @Column(name = "place", nullable = false)
    var place: Point,

    @OneToOne(mappedBy = "geoData")
    val photo: Photo? = null
)