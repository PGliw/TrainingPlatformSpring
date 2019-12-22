package pl.pchorosc.training.platform.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.web.bind.annotation.*
import pl.pchorosc.training.platform.data.dto.TraineeDTO
import pl.pchorosc.training.platform.data.response.TraineeResponse
import pl.pchorosc.training.platform.service.TraineeService

@RestController
@RequestMapping("/trainees")
@EnableAutoConfiguration
class TraineeController {

    @Autowired
    private lateinit var traineeService: TraineeService

    @GetMapping(value = ["/{id}"])
    fun getTraineeById(@PathVariable id: Long): TraineeResponse = traineeService.getTrainee(id)

    @PostMapping
    fun addTrainee(@RequestBody traineeDTO: TraineeDTO) = traineeService.insertTrainee(traineeDTO)
}