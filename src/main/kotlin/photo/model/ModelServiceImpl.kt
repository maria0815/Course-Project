package photo.model

import org.springframework.stereotype.Service
import java.util.*

@Service
class ModelServiceImpl(private val modelRepository: ModelRepository) : ModelService {
    override fun createModel(name: String, brandId: UUID): UUID {
        val model = modelRepository.save(Model(name = name, brandId = brandId))
        return model.id
    }

    override fun deleteModel(id: UUID) {
        modelRepository.deleteById(id)
    }
}