package br.com.messageproducer.config.testContainers

import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ConfigurableApplicationContext
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.utility.DockerImageName

object PostgresContainer {
    private val instance by lazy { start() }

    fun applyTo(applicationContext: ConfigurableApplicationContext) {
        TestPropertyValues.of(
            "spring.datasource.url=${instance.jdbcUrl}",
            "spring.datasource.username=${instance.username}",
            "spring.datasource.password=${instance.password}",
        ).applyTo(applicationContext)
    }

    private fun start() = PostgreSQLContainer<Nothing>(DockerImageName.parse("postgres:13-alpine")).apply {
        withDatabaseName("demo")
        withUsername("demo")
        withPassword("demo")
        withCommand("postgres", "-c", "fsync=off", "-c", "log_statement=all")
        start()
    }
}