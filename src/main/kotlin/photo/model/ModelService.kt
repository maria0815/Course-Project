package photo.model

import java.util.*

interface ModelService {
    fun createModel(name: String, brandId: UUID): UUID
    fun deleteModel(id: UUID)
}