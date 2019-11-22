package pl.pchorosc.training.platform.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pl.pchorosc.training.platform.data.Trainer
import pl.pchorosc.training.platform.data.TrainerDTO
import pl.pchorosc.training.platform.repository.TrainerRepository
import java.util.*

@Service("Trainer.kt service")
class TrainerService {

    @Autowired
    lateinit var repository: TrainerRepository

    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    fun getTrainers(): Iterable<TrainerDTO> = repository.findAll().map { trainer -> TrainerDTO(trainer) }

    /**
     * Saves a given entity. Use the returned instance for further operations as
     * the save operation might have changed the entity instance completely.
     *
     * @param entity must not be {@literal null}.
     * @return the saved entity will never be {@literal null}.
     */
    fun insertTrainer(trainerDto: TrainerDTO): Trainer = repository.save(
            Trainer(
                    firstName = trainerDto.firstName,
                    lastName = trainerDto.lastName,
                    photoUrl = trainerDto.photoUrl,
                    birthDate = trainerDto.birthDate,
                    pricePerHour = trainerDto.pricePerHour,
                    description = trainerDto.description
            )
    )

    /**
     * Saves a given entity. Use the returned instance for further operations as
     * the save operation might have changed the entity instance completely.
     *
     * @param entity must not be {@literal null}.
     * @return the saved entity will never be {@literal null}.
     */
    fun updateTrainer(trainerDto: TrainerDTO): TrainerDTO {
        val trainer = repository.findById(trainerDto.id).get() // TODO empty id ???
        trainer.firstName = trainerDto.firstName
        trainer.lastName = trainerDto.lastName
        trainer.birthDate = trainerDto.birthDate
        trainer.description = trainerDto.description
        trainer.photoUrl = trainerDto.photoUrl
        trainer.pricePerHour = trainerDto.pricePerHour
        trainer.modified = Date()
        val updatedTrainer = repository.save(trainer)
        return TrainerDTO(updatedTrainer)
    }

    fun getRegisteredLaterThan(date: Date): Iterable<TrainerDTO> = repository.findRegisteredLaterThan(date.time).map { TrainerDTO(it) }

    fun findByLastName(lastName: String) : Iterable<TrainerDTO> = repository.findByLastName(lastName).map { TrainerDTO(it) }
}