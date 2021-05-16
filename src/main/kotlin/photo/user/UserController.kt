package photo.user

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) : UserApi {

    private val logger = LoggerFactory.getLogger(UserController::class.java)

    override fun createUser(createUserRequest: CreateUserRequest): ResponseEntity<CreateUserResponse> {
        val uuid = userService.createUser(createUserRequest.name)
        return ResponseEntity(CreateUserResponse(uuid), HttpStatus.CREATED)
    }

    override fun updateUser(id: UUID, updateUserRequest: UpdateUserRequest): ResponseEntity<Unit> {
        userService.updateUser(id, updateUserRequest.name)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    override fun deleteUser(id: UUID): ResponseEntity<Unit> {
        userService.deleteUser(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    override fun getUsers(): ResponseEntity<Iterable<UserDto>> {
        val listOfUsers = userService.getAllUsers()
        return ResponseEntity.ok(listOfUsers)
    }

    override fun getUserById(id: UUID): ResponseEntity<UserDto> {
        val user = userService.getUserById(id)
        return ResponseEntity(user, HttpStatus.OK)
    }
}