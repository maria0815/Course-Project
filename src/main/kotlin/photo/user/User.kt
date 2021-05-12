package photo.user

import io.swagger.annotations.ApiModelProperty
import java.util.*
import javax.persistence.*

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
    val name: String
)