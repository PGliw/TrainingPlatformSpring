package pl.pchorosc.training.platform.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import pl.pchorosc.training.platform.repository.TraineeRepository
import pl.pchorosc.training.platform.repository.Trainer2Repository

@Service
class MyUserDetailsService : UserDetailsService {

    @Autowired
    private lateinit var trainer2Repository: Trainer2Repository

    @Autowired
    private lateinit var traineeRepository: TraineeRepository

    override fun loadUserByUsername(username: String?): UserDetails {
        username ?: throw Exception("MyUserDetailsService: username = null")
        val trainer = trainer2Repository.findFirstByEmail(username)
        val trainee = traineeRepository.findFirstByEmail(username)
        return when {
            trainer != null -> org.springframework.security.core.userdetails.User(
                    trainer.email,
                    trainer.password,
                    AuthorityUtils.createAuthorityList("TRAINER")
            )

            trainee != null -> org.springframework.security.core.userdetails.User(
                    trainee.email,
                    trainee.password,
                    AuthorityUtils.createAuthorityList("TRAINEE")
            )
            else -> throw UsernameNotFoundException("User $username not found")
        }
    }
}