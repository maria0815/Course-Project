package photo.brand

import java.util.*

interface BrandService {
    fun createBrand(name: String): UUID
    fun deleteBrand(id: UUID)
    fun getBrandById(id: UUID): Brand?
}