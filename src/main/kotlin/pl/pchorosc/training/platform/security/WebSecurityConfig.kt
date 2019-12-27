package pl.pchorosc.training.platform.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import pl.pchorosc.training.platform.service.MyUserDetailsService

/**
 * Security Configuration class for whole app.
 * It gets called after OAuth configuration classes.
 */
@Configuration
@EnableWebSecurity
class WebSecurityConfig : WebSecurityConfigurerAdapter() {

    /**
     * Service that provides user data to authentication mechanism
     */
    @Autowired
    lateinit var myUserDetailsService: MyUserDetailsService

    /**
     * Authentication - configure the authentication mechanism
     */
    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(myUserDetailsService)
    }

    /**
     * This bean is required for authentication server.
     * (It gets autowired (injected) into OAuth2ServerConfig class).
     */
    @Bean
    override fun authenticationManager(): AuthenticationManager {
        return super.authenticationManager()
    }
}