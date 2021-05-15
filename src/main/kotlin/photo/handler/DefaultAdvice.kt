package photo.handler

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import photo.album.AlbumNotFoundException
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
    fun handleException(e: Exception): ResponseEntity<NotFoundResponse> {
        return ResponseEntity(NotFoundResponse(e.message), HttpStatus.NOT_FOUND)
    }
}

@ApiModel("Ошибка")
class NotFoundResponse(
    @ApiModelProperty("Информация об ошибке")
    val message: String?
)