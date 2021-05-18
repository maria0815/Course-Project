package ru.tinkoff.photo.database.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "file", schema = "public")
class File(
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000"),

    @Column(name = "data", nullable = false)
    val data: ByteArray,

    @Column(name = "name", nullable = false)
    val name: String,

    @OneToOne(mappedBy = "file")
    val photo: Photo? = null
)