package photo.configuration

import photo.dao.album.AlbumDaoImpl
import photo.dao.album.AlbumDao
import photo.dao.photo.PhotoDao
import photo.dao.photo.PhotoDaoImpl
import photo.dao.user.UserDao
import photo.dao.user.UserDaoImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ComponentConfiguration {

    @Bean
    fun iAlbumService(): AlbumDao {
        return AlbumDaoImpl()
    }

    @Bean
    fun iPhotoService(): PhotoDao {
        return PhotoDaoImpl()
    }

    @Bean
    fun userDao(): UserDao {
        return UserDaoImpl()
    }
}