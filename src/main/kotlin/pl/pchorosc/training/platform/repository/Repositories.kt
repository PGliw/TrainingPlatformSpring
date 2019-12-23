package pl.pchorosc.training.platform.repository

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import pl.pchorosc.training.platform.data.*

interface TrainerRepository : CrudRepository<Trainer, String> {

    @Query("from Trainer t where t.created > ?1")
    fun findRegisteredLaterThan(date: Long): Iterable<Trainer>

    fun findByLastName(lastName: String): Iterable<Trainer>
}

interface Trainer2Repository : CrudRepository<Trainer2, Long>{
    fun findFirstByEmail(email: String) : Trainer2?
}

interface TraineeRepository : CrudRepository<Trainee, Long>{
    fun findFirstByEmail(email: String): Trainee?
}

interface CentreRepository : CrudRepository<Centre, Long>

interface OfferRepository : CrudRepository<Offer, Long>

interface SportRepository : CrudRepository<Sport, Long>

interface TrainerOpinionRepository : CrudRepository<OpinionTraineeAboutTrainer, Long>{
    fun findBySubjectId(subjectId: Long) : Iterable<OpinionTraineeAboutTrainer>
}

interface TraineeOpinionRepository : CrudRepository<OpinionTrainerAboutTrainee, Long>{
    fun findBySubjectId(subjectId: Long) : Iterable<OpinionTrainerAboutTrainee>
}

interface PassRepository : CrudRepository<Pass, Long>

interface ImageRepository : CrudRepository<Image, Long>

interface TrainingRepository : CrudRepository<Training, Long>

