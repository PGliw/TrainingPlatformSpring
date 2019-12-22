package pl.pchorosc.training.platform.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.web.bind.annotation.*
import pl.pchorosc.training.platform.data.dto.Trainer2DTO
import pl.pchorosc.training.platform.data.response.Trainer2Response
import pl.pchorosc.training.platform.service.Trainer2Service

@RestController
@RequestMapping("/trainers2")
@EnableAutoConfiguration
class Trainer2Controller {

    @Autowired
    private lateinit var trainer2Service: Trainer2Service

    @GetMapping
    fun getTrainers(): Iterable<Trainer2Response> = trainer2Service.getTrainers()

    @GetMapping(value = ["/{id}"])
    fun getTrainerById(@PathVariable id: Long) = trainer2Service.getTrainer(id)

    @PostMapping
    fun addTrainer(@RequestBody trainer2DTO: Trainer2DTO) = trainer2Service.insertTrainer(trainer2DTO)

}