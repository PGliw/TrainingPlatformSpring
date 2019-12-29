package pl.pchorosc.training.platform.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import pl.pchorosc.training.platform.data.OpinionTraineeAboutTrainer
import pl.pchorosc.training.platform.data.dto.OpinionDTO
import pl.pchorosc.training.platform.data.response.OpinionResponse
import pl.pchorosc.training.platform.exceptions.TraineeNotFoundException
import pl.pchorosc.training.platform.exceptions.TrainerNotFoundException
import pl.pchorosc.training.platform.repository.*
import pl.pchorosc.training.platform.security.IIdentityManager
import pl.pchorosc.training.platform.security.IdentityManager
import pl.pchorosc.training.platform.utils.toOpinionResponse
import pl.pchorosc.training.platform.utils.toOpinionTraineeAboutTrainer
import pl.pchorosc.training.platform.utils.toOpinionTrainerAboutTrainee

@Service("Opinion.kt service")
class OpinionService {

    @Autowired
    private lateinit var trainerOpinionRepository: TrainerOpinionRepository

    @Autowired
    private lateinit var traineeOpinionRepository: TraineeOpinionRepository

    @Autowired
    private lateinit var trainer2Repository: Trainer2Repository

    @Autowired
    private lateinit var traineeRepository: TraineeRepository

    @Autowired
    lateinit var identityManager: IIdentityManager

    fun getOpinionsAboutTrainer(trainerID: Long): Iterable<OpinionResponse> =
            trainerOpinionRepository.findBySubjectId(trainerID).map { it.toOpinionResponse() }

    fun getOpinionsGivenByTrainer(trainerID: Long): Iterable<OpinionResponse> =
            traineeOpinionRepository.findByAuthorId(trainerID).map { it.toOpinionResponse() }

    fun insertOpinionAboutTrainer(opinionDTO: OpinionDTO): OpinionResponse {
        // TODO check if trainee have had a training with this trainer
        // TODO find and delete old opinion before inserting new one
        val opinion = opinionDTO.toOpinionTraineeAboutTrainer()
        val author = traineeRepository.findByIdOrNull(opinionDTO.authorID) ?: throw TraineeNotFoundException()
        val subject = trainer2Repository.findByIdOrNull(opinionDTO.subjectID) ?: throw TrainerNotFoundException()
        opinion.author = author
        opinion.subject = subject
        return trainerOpinionRepository.save(opinion).toOpinionResponse()
    }

    fun getOpinionsAboutTrainee(traineeID: Long): Iterable<OpinionResponse> =
            traineeOpinionRepository.findBySubjectId(traineeID).map { it.toOpinionResponse() }

    fun getOpinionsGivenByTrainee(traineeID: Long): Iterable<OpinionResponse> =
            trainerOpinionRepository.findByAuthorId(traineeID).map { it.toOpinionResponse() }

    fun insertOpinionAboutTrainee(opinionDTO: OpinionDTO): OpinionResponse {
        // TODO check if author is trainer and if they have had a training with the trainee
        // TODO find and delete old opinion before inserting new one
        val opinion = opinionDTO.toOpinionTrainerAboutTrainee()
        val subject = traineeRepository.findByIdOrNull(opinionDTO.authorID) ?: throw TraineeNotFoundException()
        val author = trainer2Repository.findByIdOrNull(opinionDTO.subjectID) ?: throw TrainerNotFoundException()
        opinion.author = author
        opinion.subject = subject
        return traineeOpinionRepository.save(opinion).toOpinionResponse()
    }
}