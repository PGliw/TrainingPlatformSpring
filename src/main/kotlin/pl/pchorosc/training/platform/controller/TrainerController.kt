package pl.pchorosc.training.platform.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import pl.pchorosc.training.platform.data.Trainer
import pl.pchorosc.training.platform.data.TrainerDTO
import pl.pchorosc.training.platform.service.TrainerService
import org.springframework.web.server.ResponseStatusException
import org.springframework.http.HttpStatus

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

   @GetMapping(
            value = ["/{id}"],
            produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getTrainer(@PathVariable id : String) : TrainerDTO =
        try {
                service.getTrainer(id)
        }
        catch (e: NoSuchElementException) {
                throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }

    @PostMapping(
            produces = [MediaType.APPLICATION_JSON_VALUE],
            consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun insertTrainer(
            @RequestBody trainerDto: TrainerDTO
    ) : Trainer = service.insertTrainer(trainerDto)

    @PutMapping(
            produces = [MediaType.APPLICATION_JSON_VALUE],
            consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun updateTrainer(@RequestBody trainerDto: TrainerDTO) =
        try {
                service.updateTrainer(trainerDto)
        }
        catch (e: NoSuchElementException) {
                throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }

    fun getTrainersRegisteredLaterThan(
            @RequestBody payload: TrainerRegisteredLaterThanRequest
        ): Iterable<TrainerDTO> =
        try {
                service.getRegisteredLaterThan(payload.date)
        }
        catch (e: NoSuchElementException) {
                throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }

    @GetMapping(
            value = ["/by_last_name"],
            produces = [MediaType.APPLICATION_JSON_VALUE],
            consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getTrainersWithNameLike(@RequestBody payload: TrainerFindByLastNameRequest) : Iterable<TrainerDTO> =
        try {
                service.findByLastName(payload.name)
        }
        catch (e: NoSuchElementException) {
                throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }

    @DeleteMapping(
            value = ["/{id}"],
            produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun deleteTrainerById(@PathVariable id: String) : TrainerDTO {
        try {
                return service.deleteTrainer(id)
        }
        catch(e : NoSuchElementException){
                throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }
    }

}