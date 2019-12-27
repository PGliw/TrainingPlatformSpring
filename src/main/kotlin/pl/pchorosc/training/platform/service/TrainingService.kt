package pl.pchorosc.training.platform.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pl.pchorosc.training.platform.data.Training
import pl.pchorosc.training.platform.repository.TrainingRepository

@Service("Training.kt service")
class TrainingService {

    @Autowired
    private lateinit var trainingRepository: TrainingRepository

    fun getTrainings(): Iterable<Training> = trainingRepository.findAll()

//    fun getTrainingSummaries(): Iterable<Summary> = trainingRepository.findAll().map { it }
//
//    fun insertTraining(trainingDTO: TrainingDTO): TrainingResponse = trainingRepository.save(
//            trainingDTO
//    ).toCentreResponse()

}