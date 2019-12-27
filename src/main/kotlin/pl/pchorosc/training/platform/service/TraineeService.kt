package pl.pchorosc.training.platform.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import pl.pchorosc.training.platform.data.dto.TraineeDTO
import pl.pchorosc.training.platform.data.response.TraineeResponse
import pl.pchorosc.training.platform.exceptions.TraineeNotFoundException
import pl.pchorosc.training.platform.repository.TraineeRepository
import pl.pchorosc.training.platform.utils.toTrainee
import pl.pchorosc.training.platform.utils.toTraineeResponse

@Service("Trainee.kt service")
class TraineeService {

    @Autowired
    lateinit var traineeRepository: TraineeRepository

    fun getTrainee(id: Long): TraineeResponse =
            traineeRepository.findByIdOrNull(id)?.toTraineeResponse() ?: throw TraineeNotFoundException()

    fun insertTrainee(traineeDTO: TraineeDTO) = traineeRepository.save(traineeDTO.toTrainee()).toTraineeResponse()
}