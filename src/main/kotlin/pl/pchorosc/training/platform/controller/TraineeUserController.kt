package pl.pchorosc.training.platform.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.web.bind.annotation.*
import pl.pchorosc.training.platform.data.dto.TrainingDTO
import pl.pchorosc.training.platform.data.response.OpinionResponse
import pl.pchorosc.training.platform.data.response.TraineeResponse
import pl.pchorosc.training.platform.data.response.TraineeTrainingShortSummary
import pl.pchorosc.training.platform.data.response.TrainingSummaryResponse
import pl.pchorosc.training.platform.security.IIdentityManager
import pl.pchorosc.training.platform.service.OpinionService
import pl.pchorosc.training.platform.service.TraineeService

// TODO protect - limit  access to this endpoint int OAuth2 config only for users with role = TRAINEE
@RestController
@RequestMapping("/trainee")
@EnableAutoConfiguration
class TraineeUserController {

    @Autowired
    private lateinit var traineeService: TraineeService

    @Autowired
    private lateinit var opinionService: OpinionService

    @Autowired
    private lateinit var identityManager: IIdentityManager

    @GetMapping
    fun getProfile(): TraineeResponse {
        val traineeID = identityManager.currentUser.id
        return traineeService.getTrainee(traineeID)
    }

    @PostMapping(value = ["/trainings"])
    fun addTraining(@RequestBody trainingDTO: TrainingDTO): TrainingSummaryResponse {
        val traineeID = identityManager.currentUser.id
        return traineeService.insertTraineeTraining(traineeID, trainingDTO)
    }

    @GetMapping(value = ["/trainings"])
    fun getTrainings(): Iterable<TrainingSummaryResponse> {
        val traineeID = identityManager.currentUser.id
        return traineeService.getTrainings(traineeID)
    }

    @GetMapping(value = ["/trainings/summaries"])
    fun getTrainingsShortSummaries(): Iterable<TraineeTrainingShortSummary> {
        val traineeID = identityManager.currentUser.id
        return traineeService.getTrainingsSummaries(traineeID)
    }

    @GetMapping(value = ["/opinions/received"])
    fun getReceivedOpinions(): Iterable<OpinionResponse> {
        val traineeID = identityManager.currentUser.id
        return opinionService.getOpinionsAboutTrainee(traineeID)
    }

    @GetMapping(value = ["/opinions/given"])
    fun getGivenOpinions(): Iterable<OpinionResponse> {
        val traineeID = identityManager.currentUser.id
        return opinionService.getOpinionsGivenByTrainee(traineeID)
    }
}