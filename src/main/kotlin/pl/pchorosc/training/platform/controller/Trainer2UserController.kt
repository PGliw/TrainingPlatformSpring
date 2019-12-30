package pl.pchorosc.training.platform.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.web.bind.annotation.*
import pl.pchorosc.training.platform.data.response.OpinionResponse
import pl.pchorosc.training.platform.data.response.Trainer2Response
import pl.pchorosc.training.platform.data.response.TrainingSummaryResponse
import pl.pchorosc.training.platform.security.IIdentityManager
import pl.pchorosc.training.platform.service.OpinionService
import pl.pchorosc.training.platform.service.Trainer2Service

// TODO protect - limit  access to this endpoint int OAuth2 config only for users with role = TRAINEE
@RestController
@RequestMapping("/trainer")
@EnableAutoConfiguration
class Trainer2UserController {

    @Autowired
    private lateinit var trainerService: Trainer2Service

    @Autowired
    private lateinit var opinionService: OpinionService

    @Autowired
    private lateinit var identityManager: IIdentityManager

    @GetMapping
    fun getProfile(): Trainer2Response {
        val trainerID = identityManager.currentUser.id
        return trainerService.getTrainer(trainerID)
    }


    @GetMapping(value = ["/trainings"])
    fun getTrainings(
            @RequestParam status: String?
    ): Iterable<TrainingSummaryResponse> {
        val trainerID = identityManager.currentUser.id
        return trainerService.getTrainingsSummaries(trainerID, status)
    }

    // TODO short summaries?
    @GetMapping(value = ["/trainings/summaries"])
    fun getTrainingsShortSummaries(): Iterable<TrainingSummaryResponse> {
        val trainerID = identityManager.currentUser.id
        return trainerService.getTrainingsSummaries(trainerID)
    }

    @GetMapping(value = ["/opinions/received"])
    fun getReceivedOpinions(): Iterable<OpinionResponse> {
        val trainerID = identityManager.currentUser.id
        return opinionService.getOpinionsAboutTrainer(trainerID)
    }

    @GetMapping(value = ["/opinions/given"])
    fun getGivenOpinions(): Iterable<OpinionResponse> {
        val trainerID = identityManager.currentUser.id
        return opinionService.getOpinionsGivenByTrainer(trainerID)
    }

    @PatchMapping(value = ["/trainings/{id}"])
    fun updateTrainingStatus(
            @PathVariable id: Long,
            @RequestBody status: String // maybe it's better to send in object instead of plain string
    ): TrainingSummaryResponse {
        val trainerID = identityManager.currentUser.id
        return trainerService.updateTrainingStatus(trainerID = trainerID, trainingID = id, newStatusString = status)
    }
}