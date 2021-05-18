package ru.tinkoff.photo.configuration

import org.slf4j.LoggerFactory
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.net.URI
import java.sql.DriverManager
import javax.sql.DataSource


@Configuration
class DataSourceConfig(private val properties: DataSourceConfigProperties) {
    private val logger = LoggerFactory.getLogger(DataSourceConfig::class.java)

    @Bean
    fun getDataSource(): DataSource {
        val dataSourceBuilder = DataSourceBuilder.create()
        val dataSourceType = Class.forName(properties.type) as Class<out DataSource?>
        dataSourceBuilder.type(dataSourceType)
        dataSourceBuilder.driverClassName(properties.driverClassName)

        val databaseUrl = System.getenv("DATABASE_URL")
        if (databaseUrl != null) {
            logger.info("Found env variable DATABASE_URL $databaseUrl")
            val dbUri = URI(databaseUrl)

            val username: String = dbUri.userInfo.split(":")[0]
            val password: String = dbUri.userInfo.split(":")[1]
            val dbUrl = "jdbc:postgresql://" + dbUri.host + ':' + dbUri.port + dbUri.path

            dataSourceBuilder.url(dbUrl)
            dataSourceBuilder.username(username)
            dataSourceBuilder.password(password)
        } else {
            logger.info("Setting default database properties")
            dataSourceBuilder.url(properties.url)
            dataSourceBuilder.username(properties.username)
            dataSourceBuilder.password(properties.password)
        }

        return dataSourceBuilder.build()
    }
}