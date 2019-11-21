package pl.pchorosc.training.platform.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import pl.pchorosc.training.platform.data.Trainer
import pl.pchorosc.training.platform.data.TrainerDTO
import pl.pchorosc.training.platform.service.TrainerService

@RestController
@RequestMapping("/trainers")
@EnableAutoConfiguration
class TrainerController {

    @Autowired
    private lateinit var service: TrainerService

    @GetMapping(
            produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getTraineres() : Iterable<TrainerDTO> = service.getTrainers()

    @PutMapping(
            produces = [MediaType.APPLICATION_JSON_VALUE],
            consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun insertTrainer(
            @RequestBody trainerDto: TrainerDTO
    ) : Trainer = service.insertTrainer(trainerDto)

    @PostMapping(
            produces = [MediaType.APPLICATION_JSON_VALUE],
            consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun updateTrainer(
            @RequestBody trainerDto: TrainerDTO
    ) = service.updateTrainer(trainerDto)

}