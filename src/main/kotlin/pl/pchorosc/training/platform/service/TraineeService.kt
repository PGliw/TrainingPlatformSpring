package pl.pchorosc.training.platform.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pl.pchorosc.training.platform.data.TraineeTraining
import pl.pchorosc.training.platform.data.dto.TraineeDTO
import pl.pchorosc.training.platform.data.dto.TrainingDTO
import pl.pchorosc.training.platform.data.response.TraineeResponse
import pl.pchorosc.training.platform.data.response.TraineeTrainingShortSummary
import pl.pchorosc.training.platform.data.response.TrainingSummaryResponse
import pl.pchorosc.training.platform.exceptions.CentreNotFoundException
import pl.pchorosc.training.platform.exceptions.SportNotFoundException
import pl.pchorosc.training.platform.exceptions.TraineeNotFoundException
import pl.pchorosc.training.platform.exceptions.TrainerNotFoundException
import pl.pchorosc.training.platform.repository.*
import pl.pchorosc.training.platform.utils.*

@Service("Trainee.kt service")
class TraineeService {

    @Autowired
    lateinit var traineeRepository: TraineeRepository

    @Autowired
    lateinit var trainingRepository: TrainingRepository

    @Autowired
    lateinit var trainerRepository: Trainer2Repository

    @Autowired
    lateinit var centreRepository: CentreRepository

    @Autowired
    lateinit var traineeTrainingRepository: TraineeTrainingRepository

    @Autowired
    lateinit var sportRepository: SportRepository

    fun getTrainee(id: Long): TraineeResponse =
            traineeRepository.findByIdOrNull(id)?.toTraineeResponse() ?: throw TraineeNotFoundException()

    fun insertTrainee(traineeDTO: TraineeDTO) = traineeRepository.save(traineeDTO.toTrainee()).toTraineeResponse()

    @Transactional
    fun insertTraineeTraining(traineeID: Long, trainingDTO: TrainingDTO): TrainingSummaryResponse {
        val training = trainingDTO.toTraining()
        val trainer = trainerRepository.findByIdOrNull(trainingDTO.trainerID) ?: throw TrainerNotFoundException()
        val trainee = traineeRepository.findByIdOrNull(traineeID) ?: throw TraineeNotFoundException()
        val centre = centreRepository.findByIdOrNull(trainingDTO.centreID) ?: throw CentreNotFoundException()
        val sport = sportRepository.findByIdOrNull(trainingDTO.sportID) ?: throw SportNotFoundException()
        training.trainer = trainer
        training.sport = sport
        training.centre = centre
        val savedTraining = trainingRepository.save(training)
        val traineeTraining = TraineeTraining()
        traineeTraining.trainee = trainee
        traineeTraining.training = savedTraining
        traineeTrainingRepository.save(traineeTraining)
        return training.toTrainingSummary()
    }

    fun getTrainings(traineeID: Long): Iterable<TrainingSummaryResponse> =
            getTraineeTrainings(traineeID).map { it.training.toTrainingSummary() }

    fun getTrainingsSummaries(traineeID: Long): Iterable<TraineeTrainingShortSummary> =
            getTraineeTrainings(traineeID).map { it.toShortSummary() }

    private fun getTraineeTrainings(traineeID: Long): Iterable<TraineeTraining> {
        val trainee = traineeRepository.findByIdOrNull(traineeID) ?: throw TraineeNotFoundException()
        return trainee.traineeTrainings
    }
}