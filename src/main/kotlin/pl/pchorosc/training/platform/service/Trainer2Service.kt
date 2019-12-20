package pl.pchorosc.training.platform.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pl.pchorosc.training.platform.data.Trainer2
import pl.pchorosc.training.platform.data.dto.Trainer2DTO
import pl.pchorosc.training.platform.repository.Trainer2Repository
import java.time.LocalDate

@Service("Trainer2.kt service")
class Trainer2Service {

    @Autowired
    lateinit var repository: Trainer2Repository

    fun getTrainers(): Iterable<Trainer2> = repository.findAll()
    fun insertTrainer(trainer2DTO: Trainer2DTO) = repository.save(
            Trainer2(
                    email = trainer2DTO.email,
                    firstName = trainer2DTO.firstName,
                    lastName = trainer2DTO.lastName,
                    photoUrl = trainer2DTO.photoUrl,
                    phone = trainer2DTO.phone,
                    birthday = LocalDate.parse(trainer2DTO.birthday),
                    // TODO fix password
                    password = "",
                    description = trainer2DTO.description
            )
    )

    fun getTrainer(id: Long) = repository.findById(id).get()
}