package pl.pchorosc.training.platform.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import pl.pchorosc.training.platform.data.dto.Trainer2DTO
import pl.pchorosc.training.platform.data.response.OpinionResponse
import pl.pchorosc.training.platform.data.response.Trainer2Response
import pl.pchorosc.training.platform.exceptions.TrainerNotFoundException
import pl.pchorosc.training.platform.repository.Trainer2Repository
import pl.pchorosc.training.platform.utils.toTrainer2
import pl.pchorosc.training.platform.utils.toTrainer2Response

@Service("Trainer2.kt service")
class Trainer2Service {

    @Autowired
    lateinit var repository: Trainer2Repository

    fun getTrainers(): Iterable<Trainer2Response> = repository.findAll().map { it.toTrainer2Response() }

    fun insertTrainer(trainer2DTO: Trainer2DTO): Trainer2Response = repository.save(
            trainer2DTO.toTrainer2()
    ).toTrainer2Response()

    fun getTrainer(id: Long): Trainer2Response =
            repository.findByIdOrNull(id)?.toTrainer2Response() ?: throw TrainerNotFoundException()

}