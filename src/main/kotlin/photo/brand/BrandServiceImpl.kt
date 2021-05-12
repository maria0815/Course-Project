package photo.brand

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class BrandServiceImpl(private val brandRepository: BrandRepository) : BrandService {
    override fun createBrand(name: String): UUID {
        val brand = brandRepository.save(Brand(name = name))
        return brand.id
    }

    override fun deleteBrand(id: UUID) {
        brandRepository.deleteById(id)
    }

    override fun getBrandById(id: UUID): Brand? {
        return brandRepository.findByIdOrNull(id)
    }

}