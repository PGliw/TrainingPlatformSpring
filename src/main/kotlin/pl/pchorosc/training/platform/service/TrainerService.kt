package pl.pchorosc.training.platform.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pl.pchorosc.training.platform.Trainer
import pl.pchorosc.training.platform.repository.TrainerRepository
import java.sql.Date
import java.util.*

@Service("Trainer service")
class TrainerService {

    @Autowired
    lateinit var repository: TrainerRepository

    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    fun getTrainers(): Iterable<Trainer> = repository.findAll()

    /**
     * Saves a given entity. Use the returned instance for further operations as
     * the save operation might have changed the entity instance completely.
     *
     * @param entity must not be {@literal null}.
     * @return the saved entity will never be {@literal null}.
     */
    fun insertTrainer(trainer: Trainer): Trainer = repository.save(trainer)

    /**
     * Saves a given entity. Use the returned instance for further operations as
     * the save operation might have changed the entity instance completely.
     *
     * @param entity must not be {@literal null}.
     * @return the saved entity will never be {@literal null}.
     */
    fun updateTrainer(trainer: Trainer): Trainer = repository.save(trainer)
}