package photo.manufacturer

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "manufacturer", schema = "public")
class Manufacturer(
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000"),

    @Column(name = "name", nullable = false, unique = true)
    var name: String
)