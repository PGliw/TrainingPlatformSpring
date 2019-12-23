package pl.pchorosc.training.platform.security

import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.provisioning.UserDetailsManager

import pl.pchorosc.training.platform.exceptions.UnauthorizedException

@EnableWebSecurity
class SecurityConfiguration : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {
        http?.authorizeRequests()
                ?.antMatchers("/trainers2")?.permitAll()
                ?.anyRequest()?.hasRole("admin")
                ?.and()
                ?.formLogin()?.permitAll()

                ?: throw UnauthorizedException()
    }

    @get:Bean
    val userDetailsManager: UserDetailsManager
    get(){
        val userDetails: UserDetails = User
                .withDefaultPasswordEncoder()
                .password("user")
                .roles("USER")
                .build()

        val adminDetails: UserDetails = User
                .withDefaultPasswordEncoder()
                .password("admin")
                .roles("ADMIN")
                .build()

        return InMemoryUserDetailsManager(userDetails, adminDetails)
    }

    // TODO change encoder
    @get:Bean
    val passwordEncoder: PasswordEncoder
        get() = NoOpPasswordEncoder.getInstance()
}