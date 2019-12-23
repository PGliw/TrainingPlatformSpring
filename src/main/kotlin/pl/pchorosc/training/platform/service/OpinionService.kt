package pl.pchorosc.training.platform.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pl.pchorosc.training.platform.data.response.OpinionResponse
import pl.pchorosc.training.platform.repository.TraineeOpinionRepository
import pl.pchorosc.training.platform.repository.TrainerOpinionRepository
import pl.pchorosc.training.platform.utils.toOpinionResponse

@Service("Opinion.kt service")
class OpinionService {

    @Autowired
    lateinit var trainerOpinionRepository: TrainerOpinionRepository

    @Autowired
    lateinit var traineeOpinionRepository: TraineeOpinionRepository

    fun getOpinionsAboutTrainer(trainerID: Long) : Iterable<OpinionResponse> =
            trainerOpinionRepository.findBySubjectId(trainerID).map { it.toOpinionResponse() }

//    fun insertOpinionAboutTrainer(trainerID: Long) : OpinionResponse {
//            val opinion = trainerOpinionRepository.findByTrainerID(trainerID).map { it.toOpinionResponse() }
//    }

    fun getOpinionAboutTrainee(traineeID: Long) : Iterable<OpinionResponse> =
            traineeOpinionRepository.findBySubjectId(traineeID).map { it.toOpinionResponse() }
}