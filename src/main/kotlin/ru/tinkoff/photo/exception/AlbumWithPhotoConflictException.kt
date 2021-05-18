package ru.tinkoff.photo.exception

import java.util.*

class AlbumWithPhotoConflictException(albumId: UUID, photoId: UUID) :
    Exception("В альбоме $albumId уже есть фотография $photoId")