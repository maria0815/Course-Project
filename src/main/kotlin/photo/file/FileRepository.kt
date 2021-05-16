package photo.file

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface FileRepository : CrudRepository<File, UUID>