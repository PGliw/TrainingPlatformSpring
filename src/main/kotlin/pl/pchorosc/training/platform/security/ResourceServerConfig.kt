package pl.pchorosc.training.platform.security

import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter

/**
 * Configuration class for embedded (in-application) Resource server
 * * it handles the "real" resources - the api defined in controller package
 */
@Configuration
@EnableResourceServer
@Order(1) // this is important to run this before Spring OAuth2
class ResourceServerConfig : ResourceServerConfigurerAdapter() {

    /**
     * Defines which endpoints should be protected by authentication mechanism
     */
    override fun configure(http: HttpSecurity?) {

        // TODO fix to access login/registration without authentication
        http?.authorizeRequests()
                ?.antMatchers("/trainers2", "/centres")?.permitAll()
                ?.anyRequest()?.authenticated()

    }
}