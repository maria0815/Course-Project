package photo.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "spring.datasource")
class DataSourceConfigProperties(
    var type: String? = null,
    @Value("driver-class-name")
    var driverClassName: String? = null,
    var username: String? = null,
    var password: String? = null,
    var url: String? = null
)