package pl.pchorosc.training.platform.repository

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import pl.pchorosc.training.platform.data.Trainer

interface TrainerRepository: CrudRepository<Trainer, String>{

    @Query("from Trainer t where t.created > ?1")
    fun findRegisteredLaterThan(date: Long) : Iterable<Trainer>

    fun findByLastName(lastName: String): Iterable<Trainer>
}