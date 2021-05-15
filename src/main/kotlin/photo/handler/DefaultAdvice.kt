package photo.handler

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import photo.album.AlbumNotFoundException
import photo.album.withPhoto.AlbumWithPhotoConflictException
import photo.photo.PhotoNotFoundException
import photo.user.UserNotFoundException
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
}

@ApiModel("Ошибка")
class ErrorResponse(
    @ApiModelProperty("Информация об ошибке")
    val message: String?
)