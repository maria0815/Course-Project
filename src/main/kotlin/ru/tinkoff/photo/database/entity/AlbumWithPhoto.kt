package ru.tinkoff.photo.database.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(
    name = "album_with_photos",
    schema = "public",
    uniqueConstraints = [UniqueConstraint(columnNames = ["album_id", "photo_id"])]
)
class AlbumWithPhoto(
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000"),

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "album_id", referencedColumnName = "id")
    val album: Album,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "photo_id", referencedColumnName = "id")
    val photo: Photo
)