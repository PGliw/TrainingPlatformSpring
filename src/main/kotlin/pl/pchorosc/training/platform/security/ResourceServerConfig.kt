package pl.pchorosc.training.platform.security

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter

@Configuration
@EnableResourceServer
class ResourceServerConfig : ResourceServerConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {

        http?.authorizeRequests()
                ?.antMatchers("/")?.permitAll()
                ?.antMatchers(HttpMethod.POST, "/trainers", "/trainees")?.permitAll()
                ?.antMatchers("/oauth/check_token")?.permitAll()
                ?.antMatchers("/oauth/token_key")?.permitAll()
                ?.anyRequest()?.authenticated()

    }
}