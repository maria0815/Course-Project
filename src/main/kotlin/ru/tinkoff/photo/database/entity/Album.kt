package ru.tinkoff.photo.database.entity

import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.time.LocalDate
import java.time.LocalTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "album", schema = "public")
class Album(
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000"),

    @Column(name = "album_name", nullable = false)
    val name: String,

    @Column(name = "creation_date", nullable = false)
    val creationDate: LocalDate,

    @Column(name = "creation_time", nullable = false)
    val creationTime: LocalTime,

    @Column(name = "description", nullable = true)
    val description: String?,

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    val user: User,

    @OneToMany(mappedBy = "album")
    @OnDelete(action = OnDeleteAction.CASCADE)
    val albumsWithPhoto: List<AlbumWithPhoto> = mutableListOf()
)