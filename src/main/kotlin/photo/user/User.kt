package photo.user

import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import photo.album.Album
import photo.photo.Photo
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "users", schema = "public")
class User(
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000"),

    @Column(name = "name", nullable = false)
    val name: String,

    @OneToMany(mappedBy = "user")
    @OnDelete(action = OnDeleteAction.CASCADE)
    val photos: MutableList<Photo> = mutableListOf(),

    @OneToMany(mappedBy = "user")
    @OnDelete(action = OnDeleteAction.CASCADE)
    val albums: MutableList<Album> = mutableListOf()
)