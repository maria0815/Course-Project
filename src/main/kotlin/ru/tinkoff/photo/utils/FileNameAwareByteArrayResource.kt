package ru.tinkoff.photo.utils

import org.springframework.core.io.ByteArrayResource


class FileNameAwareByteArrayResource(byteArray: ByteArray, private val fileName: String) :
    ByteArrayResource(byteArray) {

    override fun getFilename(): String {
        return fileName
    }
}