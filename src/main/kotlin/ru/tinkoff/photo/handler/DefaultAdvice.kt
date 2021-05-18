package ru.tinkoff.photo.handler

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import ru.tinkoff.photo.exception.AlbumNotFoundException
import ru.tinkoff.photo.exception.AlbumWithPhotoConflictException
import ru.tinkoff.photo.exception.EmptyFilenameException
import ru.tinkoff.photo.exception.PhotoNotFoundException
import ru.tinkoff.photo.exception.EmptyContentTypeException
import ru.tinkoff.photo.exception.UserNotFoundException
import java.lang.Exception

@ControllerAdvice
class DefaultAdvice {

    @ExceptionHandler(
        value = [
            (AlbumNotFoundException::class),
            (UserNotFoundException::class),
            (PhotoNotFoundException::class)]
    )
    fun handleNotFoundException(e: Exception): ResponseEntity<ErrorResponse> {
        return ResponseEntity(ErrorResponse(e.message), HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(
        value = [(AlbumWithPhotoConflictException::class)]
    )
    fun handleConflictException(e: Exception): ResponseEntity<ErrorResponse> {
        return ResponseEntity(ErrorResponse(e.message), HttpStatus.CONFLICT)
    }

    @ExceptionHandler(
        value = [(EmptyFilenameException::class),
            (EmptyContentTypeException::class)]
    )
    fun handleEmptyArgumentException(e: Exception): ResponseEntity<ErrorResponse> {
        return ResponseEntity(ErrorResponse(e.message), HttpStatus.BAD_REQUEST)
    }
}

@ApiModel("Ошибка")
class ErrorResponse(
    @ApiModelProperty("Информация об ошибке")
    val message: String?
)