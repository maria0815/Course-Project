package photo.brand

import io.swagger.annotations.ApiModelProperty
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "brand", schema = "public")
class Brand(
    @ApiModelProperty(value = "Идентификатор марки")
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000"),

    @ApiModelProperty(value = "Название марки")
    @Column(name = "name", nullable = false)
    var name: String
)