package pl.pchorosc.training.platform.security

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter

/**
 * Configuration class for embedded (in-application) Resource server
 * * it handles the "real" resources - the api defined in controller package
 */
@Configuration
@EnableResourceServer
class ResourceServerConfig : ResourceServerConfigurerAdapter() {

    /**
     * Defines which endpoints should be protected by authentication mechanism
     */
    override fun configure(http: HttpSecurity?) {


        http?.authorizeRequests()
                ?.antMatchers("/")?.permitAll()
                ?.antMatchers(HttpMethod.POST, "/trainers2")?.permitAll()
                ?.antMatchers(HttpMethod.POST, "/trainees")?.permitAll()
                ?.antMatchers(HttpMethod.POST, "/centres")?.permitAll()
                ?.anyRequest()?.authenticated()

                ?: throw Exception("ResourceServerConfig: http = null")

    }
}