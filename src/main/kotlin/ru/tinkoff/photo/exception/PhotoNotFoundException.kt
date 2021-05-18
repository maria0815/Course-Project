package ru.tinkoff.photo.exception

import java.util.*

class PhotoNotFoundException(id: UUID) : Exception(("Фотография с идентификатором $id не найдена"))