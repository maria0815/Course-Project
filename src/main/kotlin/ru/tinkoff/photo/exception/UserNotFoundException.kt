package ru.tinkoff.photo.exception

import java.util.*

class UserNotFoundException(id: UUID) : Exception(("Пользователь с идентификатором $id не найден"))