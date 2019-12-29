package pl.pchorosc.training.platform.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.web.bind.annotation.*
import pl.pchorosc.training.platform.data.dto.OpinionDTO
import pl.pchorosc.training.platform.data.dto.TraineeDTO
import pl.pchorosc.training.platform.data.dto.TrainingDTO
import pl.pchorosc.training.platform.data.response.TraineeResponse
import pl.pchorosc.training.platform.service.OpinionService
import pl.pchorosc.training.platform.service.TraineeService

@RestController
@RequestMapping("/trainees")
@EnableAutoConfiguration
class TraineeController {

    @Autowired
    private lateinit var traineeService: TraineeService

    @Autowired
    private lateinit var opinionService: OpinionService

    @GetMapping(value = ["/{id}"])
    fun getTraineeById(@PathVariable id: Long): TraineeResponse = traineeService.getTrainee(id)

    @PostMapping
    fun addTrainee(@RequestBody traineeDTO: TraineeDTO) = traineeService.insertTrainee(traineeDTO)

    @PostMapping(value = ["/{id}/trainings"])
    fun addTraining(@PathVariable id: Long, @RequestBody trainingDTO: TrainingDTO) =
            traineeService.insertTraineeTraining(id, trainingDTO)

    @GetMapping(value = ["/{id}/trainings"])
    fun getTrainings(@PathVariable id: Long) = traineeService.getTrainings(id)

    @GetMapping(value = ["/{id}/opinions"])
    fun getTraineeOpinions(@PathVariable id: Long) = opinionService.getOpinionsAboutTrainee(id)

    @PostMapping(value = ["/{id}/opinions"])
    fun addTraineeOpinions(@RequestBody opinionDTO: OpinionDTO) = opinionService.insertOpinionAboutTrainee(opinionDTO)

}

