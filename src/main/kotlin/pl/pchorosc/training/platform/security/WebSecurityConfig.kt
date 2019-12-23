package pl.pchorosc.training.platform.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import pl.pchorosc.training.platform.service.MyUserDetailsService

@Configuration
@EnableWebSecurity
class WebSecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var myUserDetailsService: MyUserDetailsService

    /**
     * Authentication - configure the authentication mechanism
     */
    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(myUserDetailsService)
    }

    @Bean
    override fun authenticationManager(): AuthenticationManager {
        return super.authenticationManager()
    }
}