package photo.model

import java.util.*
import javax.persistence.*

@Entity
@Table(
    name = "model",
    schema = "public",
    uniqueConstraints = [UniqueConstraint(columnNames = ["name", "manufacturer_id"])]
)
class Model(
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000"),

    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "manufacturer_id", nullable = false)
    var manufacturerId: UUID,
)