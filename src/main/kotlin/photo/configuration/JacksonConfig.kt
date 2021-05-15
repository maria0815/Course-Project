package photo.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.n52.jackson.datatype.jts.JtsModule

@Configuration
class JacksonConfig {
    @Bean
    fun jtsModule(): JtsModule {
        return JtsModule()
    }
}