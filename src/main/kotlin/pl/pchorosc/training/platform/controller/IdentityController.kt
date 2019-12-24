package pl.pchorosc.training.platform.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.pchorosc.training.platform.security.IdentityManager
import pl.pchorosc.training.platform.utils.toUserResponse

@RestController
@RequestMapping("/profile")
@EnableAutoConfiguration
class IdentityController {

    @Autowired
    private lateinit var identityManager: IdentityManager

    @GetMapping
    fun getProfile() = identityManager.currentUser.toUserResponse()
}