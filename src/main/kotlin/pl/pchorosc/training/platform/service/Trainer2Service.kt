package pl.pchorosc.training.platform.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pl.pchorosc.training.platform.data.Trainer2
import pl.pchorosc.training.platform.data.dto.Trainer2DTO
import pl.pchorosc.training.platform.data.response.Trainer2Response
import pl.pchorosc.training.platform.repository.Trainer2Repository
import pl.pchorosc.training.platform.utils.toTrainer2
import pl.pchorosc.training.platform.utils.toTrainer2Response
import java.time.LocalDate

@Service("Trainer2.kt service")
class Trainer2Service {

    @Autowired
    lateinit var repository: Trainer2Repository

    fun getTrainers(): Iterable<Trainer2Response> = repository.findAll().map { it.toTrainer2Response() }

    fun insertTrainer(trainer2DTO: Trainer2DTO): Trainer2Response = repository.save(
            trainer2DTO.toTrainer2()
    ).toTrainer2Response()

    fun getTrainer(id: Long) = repository.findById(id).get()
}