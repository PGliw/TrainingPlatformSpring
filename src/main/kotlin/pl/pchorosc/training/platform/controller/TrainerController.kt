package pl.pchorosc.training.platform.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import pl.pchorosc.training.platform.Trainer
import pl.pchorosc.training.platform.service.TrainerService
import java.sql.Date
import java.util.UUID

@RestController
@RequestMapping("/trainers")
@EnableAutoConfiguration
class TrainerController {

    @Autowired
    private lateinit var service: TrainerService

    @GetMapping(
            produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getTraineres() : Iterable<Trainer> = service.getTrainers()

    @PutMapping(
            produces = [MediaType.APPLICATION_JSON_VALUE],
            consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun insertTrainer(
            @RequestBody trainer: Trainer
    ) : Trainer = service.insertTrainer(trainer)

    @PostMapping(
            produces = [MediaType.APPLICATION_JSON_VALUE],
            consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun updateTrainer(
            @RequestBody trainer: Trainer
    ) = service.updateTrainer(trainer)

}