package ru.tinkoff.photo.exception

import java.util.*

class AlbumNotFoundException(id: UUID) : Exception(("Альбом с идентификатором $id не найден"))