package photo.model

import io.swagger.annotations.ApiModelProperty
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "model", schema = "public")
class Model(
    @ApiModelProperty(value = "Идентификатор модели")
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000"),

    @ApiModelProperty(value = "Название модели")
    @Column(name = "name", nullable = false)
    var name: String,

    @ApiModelProperty(value = "Идентификатор производителя")
    @Column(name = "brand_id")
    var brandId: UUID
)