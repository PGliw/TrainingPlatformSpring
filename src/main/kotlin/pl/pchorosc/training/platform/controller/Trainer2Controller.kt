package pl.pchorosc.training.platform.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.web.bind.annotation.*
import pl.pchorosc.training.platform.data.dto.Trainer2DTO
import pl.pchorosc.training.platform.service.Trainer2Service

@RestController
@RequestMapping("/trainers2")
@EnableAutoConfiguration
class Trainer2Controller {

    @Autowired
    private lateinit var trainer2Service: Trainer2Service

//    @Autowired
//    private lateinit var modelMapper: ModelMapper

    @GetMapping
    fun getTrainers(): List<Trainer2DTO> =
            trainer2Service.getTrainers().map { Trainer2DTO(it) }

    @PostMapping
    fun addTrainer(@RequestBody trainer2DTO: Trainer2DTO) =
            trainer2Service.insertTrainer(trainer2DTO)

}