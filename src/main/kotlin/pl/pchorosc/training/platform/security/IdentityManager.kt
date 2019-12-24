package pl.pchorosc.training.platform.security

import User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import pl.pchorosc.training.platform.data.Trainee
import pl.pchorosc.training.platform.data.Trainer2
import pl.pchorosc.training.platform.exceptions.UserNotFoundException
import pl.pchorosc.training.platform.repository.UserRepository

interface IIdentityManager{
    val currentUser : User
    fun User.isTrainer(): Boolean
    fun User.isTrainee(): Boolean
}

@Component
class IdentityManager() : IIdentityManager{

    @Autowired
    private lateinit var userRepository: UserRepository

    override val currentUser: User
    get(){
        val principal = SecurityContextHolder.getContext().authentication.principal
        val username = if(principal is UserDetails) principal.username else principal.toString()
        return userRepository.findByEmail(username) ?: throw UserNotFoundException()
    }

    override fun User.isTrainer(): Boolean = this is Trainer2

    override fun User.isTrainee(): Boolean = this is Trainee
}