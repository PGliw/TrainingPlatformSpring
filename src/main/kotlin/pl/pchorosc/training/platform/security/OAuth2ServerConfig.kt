package pl.pchorosc.training.platform.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore
import javax.sql.DataSource

@Configuration
@EnableAuthorizationServer
class OAuth2ServerConfig : AuthorizationServerConfigurerAdapter() {

    @Autowired
    lateinit var dataSource: DataSource

    @Autowired
    lateinit var authenticationManager: AuthenticationManager

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    lateinit var tokenStore: TokenStore

    /**
     * Spring security oauth exposes two endpoints for checking tokens
     * (/oauth/check_token and /oauth/token_key)
     * which are by default protected behind denyAll().
     * tokenKeyAccess() and checkTokenAccess() methods open these endpoints for use.
     */
    override fun configure(oauthServer: AuthorizationServerSecurityConfigurer?) {
        oauthServer
                ?.tokenKeyAccess("permitAll()")
                ?.checkTokenAccess("permitAll()")

                ?: throw Exception("AuthorizationServerConfig: oAuthServer = null")
    }

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer?) {
        endpoints
                ?.tokenStore(tokenStore)
                ?.authenticationManager(authenticationManager)

                ?: throw Exception("AuthorizationServerConfig: endpoints = null")
    }

    /**
     * Configures API client(s)
     * ClientDetailsServiceConfigurer is used to define an in-memory or JDBC implementation of the client details service.
     * We have used in-memory implementation. It has following important attribute:
     * * clientId – (required) the client id.
     * * secret – (required for trusted clients) the client secret, if any.
     * * scope – The scope to which the client is limited. If scope is undefined or empty (the default) the client is not limited by scope.
     * * authorizedGrantTypes – Grant types that are authorized for the client to use. Default value is empty.
     * * authorities – Authorities that are granted to the client (regular Spring Security authorities).
     * * redirectUris – redirects the user-agent to the client’s redirection endpoint. It must be an absolute URL.
     */
    override fun configure(clients: ClientDetailsServiceConfigurer?) {
        clients
                ?.inMemory()
                ?.withClient("frontendClientId")
                ?.secret("frontendClientSecret")
                ?.authorizedGrantTypes("password","authorization_code", "refresh_token")
                ?.accessTokenValiditySeconds(3600)
                ?.refreshTokenValiditySeconds(28 * 24 * 3600)
                ?.scopes("all")

                ?: throw Exception("AuthorizationServerConfig: clients = null")
    }

    @Bean
    fun tokenStore() : TokenStore = InMemoryTokenStore()

    @Bean
    fun passwordEncoder() : PasswordEncoder = NoOpPasswordEncoder.getInstance() //BCryptPasswordEncoder()
}

@Configuration
@EnableResourceServer
class ResourceServerConfig: ResourceServerConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {

        http?.authorizeRequests()
                ?.antMatchers("/")?.permitAll()
                ?.antMatchers(HttpMethod.POST, "/trainers", "/trainees")?.permitAll()
                ?.antMatchers("/oauth/check_token")?.permitAll()
                ?.antMatchers("/oauth/token_key")?.permitAll()
        //?.anyRequest()?.authenticated()

    }
}