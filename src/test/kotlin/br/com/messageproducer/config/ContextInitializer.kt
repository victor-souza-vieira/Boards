package br.com.messageproducer.config

import br.com.messageproducer.config.testContainers.PostgresContainer
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext

class ContextInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
    override fun initialize(applicationContext: ConfigurableApplicationContext) {
        PostgresContainer.applyTo(applicationContext)
    }
}
