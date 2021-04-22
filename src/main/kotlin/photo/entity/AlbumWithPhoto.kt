package photo.entity

import java.util.*

data class AlbumWithPhoto(
    val id: UUID,
    val album_id: UUID,
    val photo_id: UUID
)