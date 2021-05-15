package photo.model

import photo.manufacturer.Manufacturer
import photo.photo.Photo
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
    val id: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000"),

    @Column(name = "name", nullable = false)
    val name: String,

    @ManyToOne
    @JoinColumn(name = "manufacturer_id", referencedColumnName = "id")
    val manufacturer: Manufacturer,

    @OneToMany(mappedBy = "model")
    val photos: List<Photo> = mutableListOf()
)