package photo.album

import java.util.*

class AlbumNotFoundException(id: UUID) : Exception(("Альбом с идентификатором $id не найден"))